package com.example.fileuploader_attacker.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class LogTestController {

    @GetMapping("/log")
    public void test() {
        log.info("[INFO] 안내");
        log.warn("[WARN] 위험");
        log.error("[ERROR] 에러");
    }
}
