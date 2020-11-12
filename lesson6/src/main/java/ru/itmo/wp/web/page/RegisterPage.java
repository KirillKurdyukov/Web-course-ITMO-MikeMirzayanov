package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class RegisterPage extends Page {

    private void register(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        user.setEmail(request.getParameter("email"));
        userService.validateRegistration(user, password, passwordConfirmation);
        userService.register(user, password);
        userService.saveEvent(Event.ENTER, user);
        setMessage("You are successfully registered!");
        throw new RedirectException("/index");
    }
}
