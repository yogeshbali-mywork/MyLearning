package com.ybali.springAI.services;

import java.util.List;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatWithMemoryService {

	private final ChatModel chatModel;
	private final ChatMemory memory;
	private final String conversationId = "yogeshBali";

	public ChatWithMemoryService(ChatModel chatModel) {
		this.chatModel = chatModel;

		ChatMemoryRepository repository = new InMemoryChatMemoryRepository();
		this.memory = MessageWindowChatMemory.builder()
				.chatMemoryRepository(repository)
				.maxMessages(10)
				.build();
	}

	public ChatResponse generateAnswer(String userMessage) {
		UserMessage userMsg = new UserMessage(userMessage);

		// Add current user message to memory
		memory.add(conversationId, userMsg);

		// Re-fetch history including the new message
		List<Message> updatedHistory = memory.get(conversationId);

		// Build prompt using full conversation context
		Prompt prompt = new Prompt(updatedHistory);

		// Call the model
		ChatResponse chatResponse = chatModel.call(prompt);

		// Add assistant's response to memory
		memory.add(conversationId, chatResponse.getResult().getOutput());

		return chatResponse;
	}
}
