



package com.rememberme.rememberMe.domain;

import com.rememberme.rememberMe.dtos.AuthRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Class {@code domain/User} represent the entity of user table.
 * <p>
 *  It contain entity`s attributes and your respective types.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@Table(name = "tb_user")
@Entity
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Folder> folders;

    public User(String name, String email, String password, BigDecimal balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public User() {
    }

    public boolean isAuthCorrect(AuthRequestDTO authRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(authRequest.password(), this.password);
    }
}
