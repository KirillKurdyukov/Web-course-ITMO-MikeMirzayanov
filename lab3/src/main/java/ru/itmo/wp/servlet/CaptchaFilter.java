package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {

    private Random random;
    private int intCaptcha;

    @Override
    public void init() throws ServletException {
        super.init();
        random = new Random();
    }

    private void doCaptcha(HttpServletResponse response, String uri, HttpSession session) throws IOException {
        OutputStream outputStream = response.getOutputStream();
        if (uri.equals("/get-captcha")) {
            intCaptcha = Math.abs(random.nextInt()) % 900 + 100;
            byte[] byteCodeCaptcha = ImageUtils.toPng(Integer.toString(intCaptcha));
            response.setContentType("/image/png");
            outputStream.write(byteCodeCaptcha);
            session.setAttribute("captcha", intCaptcha);
            session.setAttribute("setCaptcha", true);
        } else {
            response.setContentType("text/html");
            File file = new File(getServletContext().getRealPath("/other/captcha.html"));
            if (session.getAttribute("URI") == null)
                session.setAttribute("URI", uri);
            if (file.isFile()) {
                Files.copy(file.toPath(), outputStream);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        outputStream.flush();
    }
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        session.setAttribute("setCaptcha", false);
        if (session.getAttribute("access") != null && session.getAttribute("access").equals("OK")) {
            super.doFilter(request, response, chain);
            return;
        }
        if ("GET".equals(request.getMethod()) &&
                !((boolean) session.getAttribute("setCaptcha"))) {
            doCaptcha(response, uri, session);
            session.setAttribute("random", Integer.toString(intCaptcha));
            return;
        }
        if ("POST".equals(request.getMethod())) {
            if (request.getParameter("captcha-val") != null && session.getAttribute("random") != null &&
                    request.getParameter("captcha-val").equals(session.getAttribute("random"))) {
                session.setAttribute("access", "OK");
                response.setStatus(302);
                response.setHeader("Location", (String) session.getAttribute("URI"));
            } else {
                doCaptcha(response, uri, session);
                session.setAttribute("setCaptcha", false);
                session.setAttribute("random", Integer.toString(intCaptcha));
            }
            return;
        }
        super.doFilter(request, response, chain);
    }
}
