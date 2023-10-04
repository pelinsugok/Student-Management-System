package internship.studentmanagementsystembackend.authentication.service;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getCurrentUserAuthentication();

    CustomUser getCurrentUserCredentials();

    Long getCurrentUserId();
}
