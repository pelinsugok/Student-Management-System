package internship.studentmanagementsystembackend.user.service;

import internship.studentmanagementsystembackend.user.entity.User;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
