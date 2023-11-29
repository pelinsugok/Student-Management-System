package internship.studentmanagementsystembackend.course.controller;

import internship.studentmanagementsystembackend.classroom.repository.ClassroomRepository;
import internship.studentmanagementsystembackend.common.response.MessageResponse;
import internship.studentmanagementsystembackend.course.repository.CourseRepository;
import internship.studentmanagementsystembackend.course.service.CourseService;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseCommandController {

    private final CourseService courseService;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;
    private final CourseRepository courseRepository;

    @PostMapping
    public MessageResponse addCourse(@RequestBody @Valid AddCourseRequest request) {
        return courseService.addCourse(request.toEntity(userRepository, classroomRepository));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR', 'ASSISTANT')")
    public MessageResponse updateCourse(@PathVariable Long id, @RequestBody @Valid UpdateCourseRequest request) {
        return courseService.updateCourse(id, request.toEntity(courseRepository.findById(id).get(), userRepository, classroomRepository));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping
    private MessageResponse deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

}
