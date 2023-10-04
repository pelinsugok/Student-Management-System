package internship.studentmanagementsystembackend.authorization.service;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.authentication.repository.CustomUserRepository;
import internship.studentmanagementsystembackend.authorization.repository.AuthRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final CustomUserRepository customUserRepository;
    private final AuthRepository authRepository;

    public CustomUser getUserLoginByUser(Long id) {
        return customUserRepository.findCustomUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserLogin not found".formatted(id)));
    }
    public List<CustomUser> getAllUserLogins() {
        return customUserRepository.findAll();
    }


}
