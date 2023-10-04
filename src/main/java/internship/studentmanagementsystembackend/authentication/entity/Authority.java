package internship.studentmanagementsystembackend.authentication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<CustomUser> users;

    public Authority(String authority) {
        this.authority = authority;
    }
}
