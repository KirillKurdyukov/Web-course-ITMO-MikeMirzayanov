package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.EventRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventRepositoryImpl implements EventRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(Event event) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `Event` (`userId`, `type`, `creationTime` ) VALUES (?, ?, NOW())")) {
                statement.setString(1, Long.toString(user.getId()));
                statement.setString(2, event.type());
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save Event.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Event.", e);
        }
    }
}
