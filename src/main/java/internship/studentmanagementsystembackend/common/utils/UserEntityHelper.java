package internship.studentmanagementsystembackend.common.utils;

import internship.studentmanagementsystembackend.authentication.service.AuthenticationFacade;
import internship.studentmanagementsystembackend.user.entity.User;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityHelper {
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    public boolean isAdmin(UserDetails currentUser){
        return currentUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
                .contains("ADMIN");
    }

    public boolean isCurrentUser(UserDetails currentUser, User existingUser){
        return existingUser.getUserCredentials().getUsername().equals(currentUser.getUsername());
    }

    public User getCurrentUserEntity() {
        Long userId = authenticationFacade.getCurrentUserId();
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID %d not found".formatted(userId)));
    }

    public boolean isCurrentUserOrAdmin(User currUser) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        return isCurrentUser(currentUser, currUser) || isAdmin(currentUser);
    }


}
