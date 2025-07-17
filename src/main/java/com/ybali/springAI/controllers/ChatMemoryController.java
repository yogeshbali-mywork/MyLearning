package com.ybali.springAI.controllers;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybali.springAI.services.ChatService;
import com.ybali.springAI.services.ChatWithMemoryService;

@Controller
public class ChatMemoryController {

	@Autowired
	private ChatWithMemoryService  chatWithMemoryService;

    public void setChatService(ChatWithMemoryService chatWithMemoryService) {
		this.chatWithMemoryService = chatWithMemoryService;
	}
    
	@GetMapping("/chatwithmemory.html")
    public String getChatWithMemoryPage() {
        return "chatwithmemory"; 
    }
    
    @PostMapping("/chatwithmemory.html")
    public String handleChatPost(@RequestParam("userMessage") String userMessage, Model model) {
    	ChatResponse response = chatWithMemoryService.generateAnswer(userMessage);
    	String answer = response.getResult().getOutput().getText();
    	model.addAttribute("question", "You:" + userMessage);
    	model.addAttribute("answer", "AI:" + answer);
        return "chatwithmemory";
    }
}
