package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.annotation.Nullable;

public record UpdateUserRequest(
        @Nullable
        String name,
        @Nullable
        String surname,
        @Nullable
        String school,
        @Nullable
        String email
) {
    public User toEntity() {
        return new User(name, surname, school, email);
    }
}
