package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class UserArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        view.put("articles", articleService.findUserId(user.getId()));
    }

    private void changeArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long id;
        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ValidationException("No id");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (articleService.find(id).getUserId() != user.getId())
            throw new ValidationException("This user can't change this article!");
        articleService.setArticleStatus(id, Boolean.parseBoolean(request.getParameter("status")));
    }
}
