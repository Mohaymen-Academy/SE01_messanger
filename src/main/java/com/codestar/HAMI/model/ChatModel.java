package com.codestar.HAMI.model;

import com.codestar.HAMI.entity.ChatTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public class ChatModel {
    private long chatId;

    private String bio;

    @Enumerated(EnumType.STRING)
    private ChatTypeEnum chatType;

    private String description;

    private byte[] photo;

}

