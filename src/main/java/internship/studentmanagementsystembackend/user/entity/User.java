package internship.studentmanagementsystembackend.user.entity;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.util.Random;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "users")
public class User extends BaseEntity {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomUser userCredentials;

    private String name;
    private String surname;
    private String school;
    private String email;

    public void updateUser(User newUser) {
        this.name = newUser.getName() != null ? newUser.getName() : this.name;
        this.surname = newUser.getSurname() != null ? newUser.getSurname() : this.surname;
        this.school = newUser.getSchool() != null ? newUser.getSchool() : this.school;
        this.email = newUser.getEmail() != null ? newUser.getEmail() : this.email;
    }

    public User(String name, String surname, String school, String email) {
        this.name = name;
        this.surname = surname;
        this.school = school;
        this.email = email;
    }
}
