package com.plannet.plannet.controller;

import com.plannet.plannet.service.WriteService;
import com.plannet.plannet.vo.MemberDTO;
import com.plannet.plannet.vo.WriteDTO;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/write")
public class WriteController {
    private WriteService writeService;
    public WriteController(WriteService writeService) {
        this.writeService = writeService;
    }

    // 일정 저장
    @PostMapping("/save")
    public ResponseEntity<Boolean> writeSave(@RequestBody Map<String, Object> wrSave) {
        String userId = (String)wrSave.get("id");
        LocalDate date = LocalDate.parse((String)wrSave.get("date"));
        List<Map<String, Object>> plan = (List<Map<String, Object>>)wrSave.get("plan");
        String diary= (String)wrSave.get("diary");

        boolean result = writeService.writeSave(userId, date, plan, diary);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(false, HttpStatus.OK);
        }
    }
    //일정 불러오기
    @PostMapping("/load")
    public ResponseEntity<List<Object>> writeLoad(@RequestBody Map<String, Object> wrLoad) {
        String userId = (String)wrLoad.get("id");
        LocalDate date = LocalDate.parse((String)wrLoad.get("date"));
        WriteDTO planAndDiary = writeService.writeLoad(userId, date);
        List<Object> writeData = new ArrayList<>();
        writeData.add(planAndDiary.getPlanList());
        writeData.add(planAndDiary.getDiary());

        if(planAndDiary.isOk()) {
            return new ResponseEntity(writeData, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

}