package internship.studentmanagementsystembackend.authorization.controller;

import internship.studentmanagementsystembackend.authentication.entity.Authority;
import internship.studentmanagementsystembackend.authentication.entity.CustomUser;

import java.util.List;
import java.util.stream.Collectors;

public record UserLoginResponse(
        String username,
        List<String> authorities
) {
    public static UserLoginResponse fromEntity(CustomUser user) {
        return new UserLoginResponse(
                user.getUsername(),
                user.getAuthorities()
                        .stream()
                        .map(Authority::getAuthority)
                        .collect(Collectors.toList()));
    }
}
