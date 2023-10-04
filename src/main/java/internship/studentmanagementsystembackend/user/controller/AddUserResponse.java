package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.validation.constraints.NotEmpty;

public record AddUserResponse(
        @NotEmpty
        String username,
        @NotEmpty
        String password
) {
    public static AddUserResponse fromEntity(User user) {
        return new AddUserResponse(user.getUserCredentials().getUsername(), user.getUserCredentials().getPassword());
    }
}
