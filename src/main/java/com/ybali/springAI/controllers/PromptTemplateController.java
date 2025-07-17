package com.ybali.springAI.controllers;

import java.util.Map;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ybali.springAI.services.PromptTemplateService;

@Controller
public class PromptTemplateController {

	@Autowired
	private PromptTemplateService promptTemplateService;

	@GetMapping("/prompttemplate.html")
	public String getPromptTemplatePage() {
		return "prompttemplate";
	}

	@PostMapping("/prompttemplate.html")
	public String handleCPromptFormPost(@RequestParam("topic") String topic, @RequestParam("audience") String audience,
			Model model) {

		// This is a Prompt Template — a plain string with placeholders in {}.
		String template = "Explain the topic of {topic} to an audience of {audience} in 50 words.";

		// This Map provides the values to fill into the template.
		Map<String, Object> variables = Map.of("topic", topic, "audience", audience);

		// PromptTemplate(template).render(variables) generates the final prompt string.
		// This replaces {topic} and {audience} with actual input.
		// Prompt is the class used to wrap this final message before sending to LLM.
		Prompt prompt = new Prompt(new PromptTemplate(template).render(variables));

		// Sends the constructed prompt to my custom PromptTemplateService,
		// which uses a ChatModel to call the LLM
		String response = promptTemplateService.generateAnswer(prompt);

		// Puts the response into the Model so Thymeleaf can display it
		model.addAttribute("result", response);
		return "prompttemplate";
	}
}
