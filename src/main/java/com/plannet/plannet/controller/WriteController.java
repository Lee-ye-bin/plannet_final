package com.plannet.plannet.controller;

import com.plannet.plannet.service.WriteService;
import com.plannet.plannet.vo.WriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class WriteController {
    private WriteService writeService;
    public WriteController(WriteService writeService) {
        this.writeService = writeService;
    }

    // 일정 저장
    @PostMapping("/write_save")
    public ResponseEntity<Boolean> writeSave(@RequestBody WriteDTO writeDTO) {
        String userId = writeDTO.getUserId();
        LocalDateTime date = writeDTO.getDate();
        List<Map<String, Object>> plan = writeDTO.getPlan();
        String diary= writeDTO.getDiary();

        boolean result = writeService.writeSave(userId, date, plan, diary);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
}