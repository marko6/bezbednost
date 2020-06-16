package com.main.app.service;

import com.main.app.domain.dto.LogDTO;
import com.main.app.domain.model.Log;
import com.main.app.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public Page<Log> getLogs(Pageable page) {
        return logRepository.findAll(page);
    }

    @Override
    public Log add(LogDTO logDTO) {

        Log log = new Log(logDTO);

        return logRepository.save(log);
    }
}
