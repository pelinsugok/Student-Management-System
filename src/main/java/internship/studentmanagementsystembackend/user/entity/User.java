package internship.studentmanagementsystembackend.user.entity;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.common.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "USERS")
public class User extends BaseEntity {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomUser userCredentials;

    private String name;
    private String surname;
    private String school;
    private String email;

}
