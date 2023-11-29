package internship.studentmanagementsystembackend.course.repository;

import internship.studentmanagementsystembackend.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
