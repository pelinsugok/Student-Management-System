package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.user.entity.User;

public record AddUserResponse(
        String username,
        String password
) {
    public static AddUserResponse fromEntity(User user) {
        return new AddUserResponse(user.getUserCredentials().getUsername(), user.getUserCredentials().getPassword());
    }
}
