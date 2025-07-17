package com.ybali.springAI.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import org.springframework.ai.ollama.api.OllamaOptions;
@Service
public class PromptTemplateService {

	private ChatModel chatModel;

	public PromptTemplateService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String generateAnswer(Prompt prompt) {
		return chatModel.call(prompt).getResult().getOutput().getText();
	}
}
