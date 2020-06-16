package com.main.app.controller;

import com.main.app.domain.dto.LogDTO;
import com.main.app.domain.dto.Entities;
import com.main.app.domain.model.Log;
import com.main.app.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public ResponseEntity<Entities> getAlarms(Pageable page) {
        Entities result = new Entities();

        Page<Log> alarms = logService.getLogs(page);

        result.setEntities(alarms.getContent());
        result.setTotal(alarms.getTotalElements());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(consumes ="application/json",produces = "application/json")
    public ResponseEntity<LogDTO> add(@RequestBody LogDTO logDTO) {

        Log savedLog = logService.add(logDTO);
        return new ResponseEntity<>(new LogDTO(savedLog), HttpStatus.OK);
    }
}
