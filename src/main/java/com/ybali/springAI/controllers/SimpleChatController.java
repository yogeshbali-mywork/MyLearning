package com.ybali.springAI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybali.springAI.services.ChatService;

@Controller
public class SimpleChatController {
	
	@Autowired
	private ChatService chatService;

    public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}

	@GetMapping("/simplechat.html")
    public String getSimpleChatPage() {
        return "simplechat"; 
    }
    
    @PostMapping("/simplechat.html")
    public String handleChatPost(@RequestParam("userMessage") String userMessage, Model model) {
    	ChatResponse response = chatService.generateAnswer(userMessage);
    	model.addAttribute("question", "You:" + userMessage);
    	model.addAttribute("answer", "AI:" + response.getResult().getOutput().getText());
        return "simplechat";
    }
}
