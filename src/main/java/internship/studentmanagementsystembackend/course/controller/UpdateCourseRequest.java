package internship.studentmanagementsystembackend.course.controller;

import internship.studentmanagementsystembackend.classroom.repository.ClassroomRepository;
import internship.studentmanagementsystembackend.course.entity.Course;
import internship.studentmanagementsystembackend.user.entity.Days;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateCourseRequest(
        @Nullable String description,
        @Nullable Days days,
        @Nullable LocalDateTime startTime,
        @Nullable LocalDateTime endTime,
        @Nullable Long instructor,
        @Nullable Long classroom,
        @Nullable List<Long> assistant
) {
    public Course toEntity(Course existingCourse, UserRepository userRepository, ClassroomRepository classroomRepository) {
        Course updatedCourse = new Course(
                existingCourse.getId(),
                existingCourse.getName(),
                description != null ? description : existingCourse.getDescription(),
                existingCourse.getType(),
                existingCourse.getCode(),
                days != null ? days : existingCourse.getDay(),
                startTime != null ? startTime : existingCourse.getStartTime(),
                endTime != null ? endTime : existingCourse.getEndTime(),
                instructor != null ? userRepository.findById(instructor)
                        .orElseThrow(() -> new IllegalArgumentException("Instructor with ID " + instructor + " not found")) : existingCourse.getInstructor(),
                classroom != null ? classroomRepository.findById(classroom)
                        .orElseThrow(() -> new IllegalArgumentException("Classroom with ID " + classroom + " not found")) : existingCourse.getClassroom(),
                assistant != null ? userRepository.findAllById(assistant) : existingCourse.getAssistants()
        );

        updatedCourse.setTimes(updatedCourse.getStartTime(), updatedCourse.getEndTime());

        return updatedCourse;
    }
}
