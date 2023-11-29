package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.authentication.entity.Authority;
import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddUserRequest(
        @NotEmpty
        String authority,
        @NotEmpty
        String name,
        @NotEmpty
        String surname,
        @NotEmpty
        String school,
        @Email
        String email
) {


    public User toEntity() {
        CustomUser customUser = new CustomUser(CustomUser.createUsername(name, surname), CustomUser.generatePassword(), List.of(new Authority("ASSISTANT")));
        User user = new User(customUser, name, surname, school, email);
        customUser.setUser(user);
        return user;
    }
}
