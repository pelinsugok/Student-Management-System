package internship.studentmanagementsystembackend.user.controller;

import internship.studentmanagementsystembackend.common.response.MessageResponse;
import internship.studentmanagementsystembackend.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserCommandController {
    private final UserService userService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public AddUserResponse addUser(@RequestBody @Valid AddUserRequest request) {
        return userService.addUser(request.toEntity());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public MessageResponse updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        return userService.updateUser(id, request.toEntity());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public MessageResponse deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
