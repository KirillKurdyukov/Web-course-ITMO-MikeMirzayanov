package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class TalksPage extends Page {

    @Override
    protected void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            throw new RedirectException("/index");
        }
        String targetUserId = (String) request.getSession().getAttribute("targetUserId");
        view.put("users", userService.findAll());
        if (targetUserId != null) {
            view.put("targetUserId", targetUserId);
            view.put("talks", talkService.getMessages(getUser().getId(), Long.parseLong(targetUserId)));
        }
    }

    protected void setTarget(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("targetUserId", request.getParameter("targetUserId"));
        throw new RedirectException("/talks");
    }

    protected void sendMessage(HttpServletRequest request, Map<String, Object> view) {
        talkService.saveMessage(getUser().getId(), Long.parseLong((String) request.getSession().getAttribute("targetUserId")),
                request.getParameter("message"));
        throw new RedirectException("/talks");
    }
}
