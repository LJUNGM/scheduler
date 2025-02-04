package com.example.scheduler.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Schedule {

    private Long id;

    private String userid;
    private String task;
    private String password;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}