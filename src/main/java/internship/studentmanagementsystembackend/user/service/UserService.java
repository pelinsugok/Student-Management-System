package internship.studentmanagementsystembackend.user.service;

import internship.studentmanagementsystembackend.common.response.MessageResponse;
import internship.studentmanagementsystembackend.common.response.MessageType;
import internship.studentmanagementsystembackend.user.controller.AddUserResponse;
import internship.studentmanagementsystembackend.user.entity.User;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public AddUserResponse addUser(User user) {
        AddUserResponse response = new AddUserResponse(user.getUserCredentials().getUsername(), user.getUserCredentials().getPassword());
        user.getUserCredentials().setPassword(passwordEncoder.encode(user.getUserCredentials().getPassword()));
        userRepository.save(user);
        return response;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID %d not found".formatted(id)));

    }

    public MessageResponse deleteUser(Long id) {
        userRepository.delete(findUserById(id));
        return new MessageResponse("User with ID %d has been deleted successfully!".formatted(id), MessageType.SUCCESS);
    }

    public MessageResponse updateUser(Long id, User user) {
        User existingUser = findUserById(id);
        if(existingUser.equals(user)) {
            return new MessageResponse("Please update users information!", MessageType.WARNING);
        } else {
            existingUser.updateUser(user);
            userRepository.save(existingUser);
            return new MessageResponse("User with ID %d has been updated successfully".formatted(id), MessageType.SUCCESS);
        }
    }
}
