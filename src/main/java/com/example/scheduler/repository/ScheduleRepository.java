package com.example.scheduler.repository;

import com.example.scheduler.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createSchedule(Schedule schedule) {
        String sql = "insert into calendar (userid, task, password, createDate, updateDate) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, schedule.getUserid(), schedule.getTask(), schedule.getPassword(), schedule.getCreateDate(), schedule.getUpdateDate());
    }

    public List<Schedule> getAllSchedule(String author, String updateDate) {
        StringBuilder sql = new StringBuilder("select * from calendar where 1=1");
        List<Object> params = new ArrayList<Object>();

        if(author != null && !author.isEmpty()){
            sql.append(" and author = ?");
            params.add(author);
        }

        if(updateDate != null && !updateDate.isEmpty()){
            sql.append(" and date(updateDate) = ?");
            params.add(Date.valueOf(updateDate));
        }

        sql.append(" order by updateDate desc");

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(Schedule.class));
    }

    public Schedule getScheduleById(Long id) {
        String sql = "select * from calendar where id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Schedule.class), id);
    }

    public void updateSchedule(Schedule schedule) {
        String sql = "update calendar set task = ?, userid = ?, updateDate = ? where id = ?";
        jdbcTemplate.update(sql, schedule.getTask(), schedule.getUserid(), schedule.getUpdateDate(), schedule.getId());
    }

    public int deleteSchedule(Long id) {
        String sql = "delete from calendar where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}