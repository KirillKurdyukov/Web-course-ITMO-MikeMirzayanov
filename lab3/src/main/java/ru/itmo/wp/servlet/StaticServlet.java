package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String [] parseUri = request.getRequestURI().split("\\+");
        response.setContentType(getContentTypeFromName(parseUri[0]));
        String pathName = "/home/kirilloid/IdeaProjects/Web/lab3/src/main/webapp/static";
        OutputStream outputStream = response.getOutputStream();
        for (String uri : parseUri) {
            if (parseUri.length > 1)
                uri = '/' + uri;
            File file = new File(pathName + uri);
            if (!file.isFile()) {
                file = new File(getServletContext().getRealPath("/static" + uri));
            }
            if (file.isFile()) {
                Files.copy(file.toPath(), outputStream);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        outputStream.flush();
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
