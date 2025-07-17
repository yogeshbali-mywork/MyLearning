package com.ybali.springAI.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private ChatClient chatClient;

	public ChatService(ChatClient.Builder builder) {
		super();
		this.chatClient = builder.build();
	}

	/**
	 * This method generates a response from the AI model (like Ollama, OpenAI,
	 * etc.) using the Spring AI framework.
	 * 
	 * It sends the user's message to the LLM through the Spring AI `ChatClient` and
	 * returns the generated reply wrapped inside a `ChatResponse`.
	 * 
	 */
	public ChatResponse generateAnswer(String userMessage) {
		return chatClient.prompt(userMessage) // Build the prompt
				.call() // Make the actual API call to underlying LLM
				.chatResponse(); // Extract and return the final response
	}
}
