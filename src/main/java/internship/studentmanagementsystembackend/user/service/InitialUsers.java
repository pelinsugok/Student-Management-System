package internship.studentmanagementsystembackend.user.service;

import internship.studentmanagementsystembackend.authentication.entity.CustomUser;
import internship.studentmanagementsystembackend.user.entity.User;
import internship.studentmanagementsystembackend.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitialUsers {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {

            CustomUser pelinLog = new CustomUser("admin", passwordEncoder.encode("admin"), "Admin");
            User pelin = new User(pelinLog, "Pelin", "Gök", "Çankaya", "pelinsugok@gmail.com");
            pelinLog.setUser(pelin);
            userRepository.save(pelin);

            CustomUser yagmurLog = new CustomUser("student", passwordEncoder.encode("student"),"Student");
            User yagmur = new User(yagmurLog, "Yağmur", "Gök", "Akdeniz", "yagmur@gmail.com");
            yagmurLog.setUser(yagmur);
            userRepository.save(yagmur);

            CustomUser busraLog = new CustomUser("assistant", passwordEncoder.encode("assistant"), "Assistant");
            User busra = new User(busraLog, "Büşra", "Sarı", "OMÜ", "busra@gmail.com");
            busraLog.setUser(busra);
            userRepository.save(busra);

            CustomUser orkunLog = new CustomUser("instructor", passwordEncoder.encode("instructor"), "Instructor");
            User orkun = new User(orkunLog, "Orkun", "Güneri", "Çankaya", "orkun@gmail.com");
            orkunLog.setUser(orkun);
            userRepository.save(orkun);
        }
    }
}
