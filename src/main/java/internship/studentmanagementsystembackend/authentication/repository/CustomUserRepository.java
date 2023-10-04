package internship.studentmanagementsystembackend.authentication.repository;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByUsername(String username);

    Optional<CustomUser> findCustomUserById(Long id);

}
