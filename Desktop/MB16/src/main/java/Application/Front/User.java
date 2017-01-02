package Application.domain;

import org.springframework.data.annotation.Id;
import javax.persistence.*;

/**
 * Created by Tomasz on 30.12.2016.
 */
@Entity
@Table(name = "Application/domain")
public class User {

    @Id(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordhash;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Application.domain{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passwordhash='" + passwordhash + '\'' +
                ", role=" + role +
                '}';
    }
}
