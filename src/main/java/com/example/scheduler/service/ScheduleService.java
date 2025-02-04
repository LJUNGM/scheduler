package com.example.scheduler.service;


import com.example.scheduler.dto.ScheduleDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    public void createSchedule(Schedule schedule) {

        LocalDateTime now = LocalDateTime.now();

        schedule.setCreateDate(now);
        schedule.setUpdateDate(now);

        scheduleRepository.createSchedule(schedule);
    }

    public List<Schedule> getAllSchedule(String author, String updateDate){
        return scheduleRepository.getAllSchedule(author, updateDate);
    }

    public Schedule getScheduleById(Long id){
        return scheduleRepository.getScheduleById(id);
    }
    public void updateSchedule(Long id, ScheduleDto scheduleDto){

        LocalDateTime now = LocalDateTime.now();
        Schedule existingSchedule = null;

        try {
            existingSchedule = scheduleRepository.getScheduleById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 일정을 찾을 수 없습니다.", e);
        }

        if(!existingSchedule.getPassword().equals(scheduleDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        existingSchedule.setTask(scheduleDto.getTask());
        existingSchedule.setUserid(scheduleDto.getUserid());
        existingSchedule.setUpdateDate(now);

        scheduleRepository.updateSchedule(existingSchedule);
    }

    public void deleteSchedule(Long id, String password){
        Schedule existingSchedule = null;

        try{
            existingSchedule = scheduleRepository.getScheduleById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 일정을 찾을 수 없습니다.", e);
        }

        if(!existingSchedule.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.deleteSchedule(id);
    }
}
