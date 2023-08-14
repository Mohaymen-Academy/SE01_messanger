package com.codestar.HAMI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profiles")
@Setter
@Getter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    @Size(min = 3, max = 40)
    private String username;

    @Column(length = 60, nullable = false)
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;

    @Column(length = 60)
    private String lastName;

    @Column(length = 100)
    private String bio;

    private byte[] picture;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToMany
    private Set<Subscription> subscriptions = new HashSet<>();

    @OneToMany
    private Set<Message> messages = new HashSet<>();
}
