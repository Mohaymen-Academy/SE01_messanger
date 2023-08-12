package com.codestar.HAMI.service;

import com.codestar.HAMI.entity.Chat;
import com.codestar.HAMI.entity.Profile;
import com.codestar.HAMI.entity.Subscription;
import com.codestar.HAMI.model.ChatModel;
import com.codestar.HAMI.repository.ChatRepository;
import com.codestar.HAMI.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    @Autowired
    ChatRepository chatRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    SubscriptionService subscriptionService;


    public List<Chat> getAllChats(long profileId) {
        Profile profile = profileService.getProfileByProfileId(profileId);
        if(profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Profile Found with profile id.");
        }

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByProfileId(profile);

        List<Chat> chats = new ArrayList<>();
        for (Subscription subscription: subscriptions) {
            chats.add(subscription.getChat());
        }

        return chats;

    }

    public Chat getChat(long chatId) {
        Chat chat = chatRepository.findById(chatId).orElse(null);
        return chat;
    }

    public Chat updateChat(long chatId ,Chat chat) {
        Chat updateChat = chatRepository.findById(chatId)
                .orElse(null);

        if(updateChat != null) {
            updateChat.setBio(chat.getBio());
            updateChat.setChatType(chat.getChatType());
            updateChat.setDescription(chat.getDescription());
            updateChat.setPhoto(chat.getPhoto());

            chatRepository.save(updateChat);
        }

        return updateChat;
    }


}
