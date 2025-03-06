package org.example.practicingspringjavacode.DateLocal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DateTineController {

    @GetMapping("/current_time")
    public ResponseEntity<CustomDateTime> getLocalTime() {
        return ResponseEntity.ok(new CustomDateTime(LocalDateTime.now()));
    }
}
