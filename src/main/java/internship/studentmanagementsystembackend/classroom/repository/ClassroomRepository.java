package internship.studentmanagementsystembackend.classroom.repository;

import internship.studentmanagementsystembackend.classroom.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
