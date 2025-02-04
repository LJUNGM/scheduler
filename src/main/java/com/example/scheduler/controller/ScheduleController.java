package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;
    public ScheduleController (ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }


    public void createSchedule(@RequestBody Schedule schedule){
        scheduleService.createSchedule(schedule);
    }

    public List<Schedule> getAllCalendars(@RequestParam(required = false) String author,
                                          @RequestParam(required = false) String updateDate){

        return scheduleService.getAllSchedule(author,updateDate);
    }

    public Schedule getCalendarById(@PathVariable Long id){
        return scheduleService.getScheduleById(id);
    }

    public String updateCalendar(@PathVariable Long id, @RequestBody ScheduleDto calendarDto){
        try {
            scheduleService.updateSchedule(id, calendarDto);
            return "수정되었습니다.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    // 삭제
    @DeleteMapping("/{id}")
    public String deleteCalendar(@PathVariable Long id, @RequestParam String password){
        try{
            scheduleService.deleteSchedule(id, password);
            return "삭제되었습니다.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}