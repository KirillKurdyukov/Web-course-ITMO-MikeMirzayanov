package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class UsersPage extends Page {

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

}
