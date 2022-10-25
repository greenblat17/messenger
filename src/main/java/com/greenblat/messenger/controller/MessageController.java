package com.greenblat.messenger.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.greenblat.messenger.model.Message;
import com.greenblat.messenger.model.Views;
import com.greenblat.messenger.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {

        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageService.saveMessage(messageFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

}
