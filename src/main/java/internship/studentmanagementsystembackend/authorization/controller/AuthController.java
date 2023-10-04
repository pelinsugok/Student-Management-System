package internship.studentmanagementsystembackend.authorization.controller;

import internship.studentmanagementsystembackend.authorization.service.AuthQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthQueryService authQueryService;
    @GetMapping
    public List<UserLoginResponse> getAllUserLogin(){
        return authQueryService.getAllUserLogins()
                .stream()
                .map(UserLoginResponse::fromEntity)
                .toList();
    }
    @GetMapping("/{id}")
    public UserLoginResponse getUserLogin(@PathVariable Long id){
        return UserLoginResponse.fromEntity(authQueryService.getUserLoginByUser(id));
    }

}

