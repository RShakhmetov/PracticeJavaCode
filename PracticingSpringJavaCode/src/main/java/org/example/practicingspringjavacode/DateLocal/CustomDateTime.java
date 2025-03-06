package org.example.practicingspringjavacode.DateLocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomDateTime {

    @JsonProperty("date_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy:MM:dd'##'HH:mm:ss:SSS")
    private LocalDateTime dateTime;

    public CustomDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
