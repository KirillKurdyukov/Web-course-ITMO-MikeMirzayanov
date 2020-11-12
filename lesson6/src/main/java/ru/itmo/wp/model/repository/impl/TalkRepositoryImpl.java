package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.TalkRepository;

import javax.sql.DataSource;
import java.sql.*;

public class TalkRepositoryImpl implements TalkRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(Long idSource, Long idTarget, String message) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `Talk` (``, ``, ``, ) VALUES (?, ?, ?, NOW())", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, Long.toString(idSource));
                statement.setString(2, Long.toString(idTarget));
                statement.setString(3, message);
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save User.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save User.", e);
        }
    }

}
