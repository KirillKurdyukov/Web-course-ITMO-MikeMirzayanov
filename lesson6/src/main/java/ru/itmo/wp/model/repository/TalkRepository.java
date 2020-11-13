package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
    void save(Talk talk);
    Talk find(Long id);
    List<Talk> getMessages(long sourceUserId, long targetUserId);
}
