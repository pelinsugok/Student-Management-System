package internship.studentmanagementsystembackend.authentication.entity;

import internship.studentmanagementsystembackend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public CustomUser(String username, String password, String authority) {
        this.username = username;
        this.password = password;
        this.authorities = generateAuthorityList(authority);
    }

    public static String createUsername(String name, String surname) {
        String username = (name + "." + surname).replaceAll("\\s+", " ").toLowerCase();
        String normalizedUsername = Normalizer.normalize(username, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizedUsername)
                .replaceAll("")
                .replace(" ", "");
    }

    public static String generatePassword() {
        String symbols = "!@#$%^&*_=+-/.?<>)";
        String allchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789" + symbols;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int randomIndex = random.nextInt(allchars.length());
            char randomChar = allchars.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> generateAuthorityList(String authority) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("STUDENT"));
        switch (authority) {
            case "Admin":
                authorities.add(new Authority("ADMIN"));
            case "Instructor":
                authorities.add(new Authority("INSTRUCTOR"));
            case "Assistant":
                authorities.add(new Authority("ASSISTANT"));
                break;
            default:
                break;
        }
        return authorities;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String updatePassword(String password) {
        this.password = password;
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
