package com.eztraining.userservice.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    private int id;

    //private int enrollmentCapacity;
    private Date startDate;
    private Date endDate;

    private TimeSlot timeSlot;

    @Data
    @AllArgsConstructor
    public static class TimeSlot {
        private LocalTime startTime;
        private LocalTime endTime;

    }

}
