package internship.studentmanagementsystembackend.authorization.service;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.authentication.repository.CustomUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthQueryService {
    private final CustomUserRepository customUserRepository;

    @Transactional
    public CustomUser getUserLoginByUser(Long id) {
        return customUserRepository.findCustomUserById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserLogin not found".formatted(id)));
    }
    public List<CustomUser> getAllUserLogins() {
        return customUserRepository.findAll();
    }
}
