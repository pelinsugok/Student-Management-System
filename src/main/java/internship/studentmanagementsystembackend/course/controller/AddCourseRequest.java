package internship.studentmanagementsystembackend.course.controller;

import internship.studentmanagementsystembackend.classroom.entity.Classroom;
import internship.studentmanagementsystembackend.classroom.repository.ClassroomRepository;
import internship.studentmanagementsystembackend.course.entity.Course;
import internship.studentmanagementsystembackend.user.entity.Days;
import internship.studentmanagementsystembackend.user.entity.User;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record AddCourseRequest(
        @NotEmpty String name,
        @NotEmpty String description,
        @NotEmpty String type,
        @NotEmpty String code,
        @NotEmpty Days day,
        LocalDateTime startTime,
        LocalDateTime endTime,
        @NotNull Long instructor,
        @NotNull Long classroom,
        List<Long> assistant
) {

    public Course toEntity(UserRepository userRepository, ClassroomRepository classroomRepository) {
        User instructorUser = userRepository.findById(instructor)
                .orElseThrow(() -> new IllegalArgumentException("Instructor with ID " + instructor + " not found"));

        List<User> assistantUsers = userRepository.findAllById(assistant);

        Classroom classrooms = classroomRepository.findById(classroom)
                .orElseThrow(() -> new IllegalArgumentException("Classroom with ID " + classroom + " not found"));

        return new Course(name, description, type, code, day,
                startTime, endTime, instructorUser, classrooms, assistantUsers);
    }
}
