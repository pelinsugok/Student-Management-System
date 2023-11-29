package internship.studentmanagementsystembackend.course.entity;

import internship.studentmanagementsystembackend.classroom.entity.Classroom;
import internship.studentmanagementsystembackend.user.entity.Days;
import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String type;
    private String code;

    @Enumerated(EnumType.STRING)
    private Days day;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private User instructor;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
            name = "course_assistant",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> assistants = new ArrayList<>();

    public Course(String name, String description, String type, String code, Days day, LocalDateTime startTime, LocalDateTime endTime, User instructor, Classroom classroom, List<User> assistant) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.code = code;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.classroom = classroom;
        this.assistants = assistant;
    }

    public void updateCourse(Course newCourse) {
        this.name = newCourse.getName() != null ? newCourse.getName() : this.name;
        this.description = newCourse.getDescription() != null ? newCourse.getDescription() : this.description;
        this.type = newCourse.getType() != null ? newCourse.getType() : this.type;
        this.code = newCourse.getCode() != null ? newCourse.getCode() : this.code;
        this.day = newCourse.getDay() != null ? newCourse.getDay() : this.day;
        this.startTime = newCourse.getStartTime() != null ? newCourse.getStartTime() : this.startTime;
        this.endTime = newCourse.getEndTime() != null ? newCourse.getEndTime() : this.endTime;
        this.instructor = newCourse.getInstructor() != null ? newCourse.getInstructor() : this.instructor;
        this.classroom = newCourse.getClassroom() != null ? newCourse.getClassroom() : this.classroom;
        this.assistants = newCourse.getAssistants() != null ? newCourse.getAssistants() : this.assistants;
    }

    public void setTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.toLocalTime().isBefore(LocalDateTime.of(1, 1, 1, 8, 40).toLocalTime())) {
            this.startTime = LocalDateTime.of(1, 1, 1, 8, 40);
        } else {
            this.startTime = startTime;
        }

        if (endTime.toLocalTime().isAfter(LocalDateTime.of(1, 1, 1, 17, 30).toLocalTime())) {
            this.endTime = LocalDateTime.of(1, 1, 1, 17, 30);
        } else {
            this.endTime = endTime;
        }

        LocalDateTime lunchStart = LocalDateTime.of(1, 1, 1, 12, 40);
        LocalDateTime lunchEnd = LocalDateTime.of(1, 1, 1, 13, 30);

        if (this.startTime.isBefore(lunchStart) && this.endTime.isAfter(lunchEnd)) {
            this.endTime = lunchStart;
        }
    }

    public void addAssistant(User user) {
        this.assistants.add(user);
    }
}
