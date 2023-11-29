package internship.studentmanagementsystembackend.course.service;

import internship.studentmanagementsystembackend.common.response.MessageResponse;
import internship.studentmanagementsystembackend.common.response.MessageType;
import internship.studentmanagementsystembackend.course.entity.Course;
import internship.studentmanagementsystembackend.course.repository.CourseRepository;
import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public MessageResponse addCourse(Course course) {
        courseRepository.save(course);
        return new MessageResponse("Course has been saved successfully!", MessageType.SUCCESS);
    }

    public Course findCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID %d not found".formatted(id)));
    }

    public MessageResponse deleteCourse(Long id) {
    courseRepository.delete(findCourseById(id));
    return new MessageResponse("Course with ID %d has been deleted successfully!".formatted(id), MessageType.SUCCESS);
    }

    public MessageResponse updateCourse(Long id, Course course) {
        Course existingCourse = findCourseById(id);
        if(existingCourse.equals(course)) {
            return new MessageResponse("Please update course information", MessageType.ERROR);
        } else {
            existingCourse.updateCourse(course);
            courseRepository.save(existingCourse);
            return new MessageResponse("Course with ID %d has been updated successfully!".formatted(id), MessageType.SUCCESS);
        }
    }

    public MessageResponse addAssistant(Long id, User user) {
        if(!courseRepository.existsById(id)) {
            return new MessageResponse("Course with ID %d not found!".formatted(id), MessageType.ERROR);
        } else {
            Course course = findCourseById(id);
            course.addAssistant(user);
            return new MessageResponse("Assistant with name %s has been added to course".formatted(user.getName() + " " + user.getSurname()), MessageType.SUCCESS);
        }
    }
}
