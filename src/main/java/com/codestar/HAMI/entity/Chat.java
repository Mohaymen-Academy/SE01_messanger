package com.codestar.HAMI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "chats")
@Setter
@Getter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @Size(max = 100)
    private String bio;

    @Column(nullable = false)
    //@NotEmpty
    @NotNull
    @Enumerated(EnumType.STRING)
    private ChatTypeEnum chatType;

    @Column(length = 200)
    @Size(max = 200)
    private String description;

    @Column(length = 10_000_000)
    @Size(max = 10_000_000)
    private byte[] photo;

    @OneToMany
    private Set<Subscription> subscriptions;

    @OneToMany
    private Set<Message> messages;
}