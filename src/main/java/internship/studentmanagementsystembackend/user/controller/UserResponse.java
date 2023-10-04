package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserResponse(
        @NotEmpty
        String name,
        @NotEmpty
        String surname,
        @NotEmpty
        String school,
        @Email
        String email,
        @NotEmpty
        String username
) {
    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getName(), user.getSurname(),
                user.getSchool(), user.getEmail(), user.getUserCredentials().getUsername());
    }
}
