package com.codestar.HAMI.service;

import com.codestar.HAMI.entity.Chat;
import com.codestar.HAMI.entity.Message;
import com.codestar.HAMI.entity.Profile;
import com.codestar.HAMI.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ProfileService profileService;

    public List<Message> getChatMessagesByChatId(Long chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtDesc(chatId);
    }

    public Message createMessage(Message message, Long chatId, Long profileId) {
        //TODO get chat
        this.setProfile(message, profileId);
        return messageRepository.saveAndFlush(message);
    }

    public Message createChatAndMessage(Message message, Long profileId) {
        //TODO create chat
        Chat chat = new Chat();
//        chat.setChatType();
        this.setProfile(message, profileId);
        message = messageRepository.saveAndFlush(message);
        chat.getMessages().add(message);
        return message;
    }

    private void setProfile(Message message, Long profileId) throws EntityNotFoundException{
        Profile profile = profileService.getProfileByProfileId(profileId);
        if (profile == null){
            throw new EntityNotFoundException("No profile found");
        }
        message.setProfile(profile);
    }

    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    public void editMessage(Long messageId, Message message) throws EntityNotFoundException {
        Message mainMessage = messageRepository.findById(messageId).orElse(null);
        if (mainMessage == null){
            throw new EntityNotFoundException("No message found");
        }
        mainMessage.setText(message.getText());
        mainMessage.setFile(message.getFile());
        messageRepository.save(message);
    }
}
