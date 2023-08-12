package com.codestar.HAMI.controller;

import com.codestar.HAMI.entity.Chat;
import com.codestar.HAMI.model.ChatModel;
import com.codestar.HAMI.repository.ChatRepository;
import com.codestar.HAMI.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/chat")
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/get")
    public List<ChatModel> getChats(long profileId) {
        List<Chat> chats = chatService.getAllChats(profileId);
        List<ChatModel> chatModels = new ArrayList<>();
        for(Chat chat : chats) {
            chatModels.add(ChatModel.builder()
                            .chatId(chat.getId())
                            .bio(chat.getBio())
                            .chatType(chat.getChatType())
                            .description(chat.getDescription())
                            .photo(chat.getPhoto())
                            .build());
        }
        return chatModels;

    }

    @GetMapping("{chatId}")
    public ChatModel getChat(@PathVariable long chatId) {
        Chat chat = chatService.getChat(chatId);
        if(chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found chat by chatId");
        }
        return ChatModel.builder()
                .chatId(chat.getId())
                .bio(chat.getBio())
                .chatType(chat.getChatType())
                .description(chat.getDescription())
                .photo(chat.getPhoto())
                .build();
    }

    @PutMapping("{chatId}")
    public ChatModel updateChat(@PathVariable long chatId,@RequestBody Chat chatDetail) {
        Chat chat  = chatService.updateChat(chatId, chatDetail);
        if(chat == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat id doesn't exist!");
        }

        return ChatModel.builder()
                .chatId(chat.getId())
                .bio(chat.getBio())
                .chatType(chat.getChatType())
                .description(chat.getDescription())
                .photo(chat.getPhoto())
                .build();
    }

    @PostMapping("/create")
    public void createChat(@RequestBody Chat chat) {

    }

}
