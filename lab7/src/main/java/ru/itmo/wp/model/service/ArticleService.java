package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository= new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public void save(Article article) {
        articleRepository.save(article);
    }

    public void validateArticle(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("The title cannot be empty");
        }

        if (userRepository.find(article.getUserId()) == null) {
            throw new ValidationException("No such user");
        }

    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
