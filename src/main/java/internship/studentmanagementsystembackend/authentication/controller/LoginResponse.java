package internship.studentmanagementsystembackend.authentication.controller;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public record LoginResponse(
        Long userId,
        String username,
        List<String> authorities
) {
    public static LoginResponse fromAuthentication(Authentication authentication) {
        return new LoginResponse(
                ((CustomUser) authentication.getPrincipal()).getId(),
                ((CustomUser) authentication.getPrincipal()).getUsername(),
                authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        );
    }
}
