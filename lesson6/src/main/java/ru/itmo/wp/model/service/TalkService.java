package ru.itmo.wp.model.service;

import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();


}
