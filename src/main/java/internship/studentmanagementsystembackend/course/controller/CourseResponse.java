package internship.studentmanagementsystembackend.course.controller;


import internship.studentmanagementsystembackend.course.entity.Course;
import internship.studentmanagementsystembackend.user.entity.Days;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record CourseResponse(
        String name,
        String description,
        String type,
        String code,
        Days days,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String instructor,
        String classroom,
        List<String> assistant
) {
    public static CourseResponse fromEntity(Course course) {
        List<String> assistantNames = course.getAssistants().stream()
                .map(assistant -> assistant.getName() + " " + assistant.getSurname())
                .collect(Collectors.toList());
        return new CourseResponse(course.getName(), course.getDescription(),
                course.getType(), course.getCode(), course.getDay(), course.getStartTime(),
                course.getEndTime(), (course.getInstructor().getName() + course.getInstructor().getSurname()), course.getClassroom().getName(),
                assistantNames);
    }
}
