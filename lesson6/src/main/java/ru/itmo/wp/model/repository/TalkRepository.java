package ru.itmo.wp.model.repository;

public interface TalkRepository {
    void save(Long idSource, Long idTarget, String message);

}
