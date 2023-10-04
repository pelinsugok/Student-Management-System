package internship.studentmanagementsystembackend.authorization.repository;


import internship.studentmanagementsystembackend.authentication.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);
}
