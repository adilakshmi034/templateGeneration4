package com.techpixe.picnie.template.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.service.TemplateService;

@RestController
@RequestMapping("/api/templates")

public class TemplateController {
	@Autowired
	private TemplateService templateService;
	
//	@PostMapping("/create")
//	public class create Template(@RequestParam )

	@GetMapping
	public List<Template> getAllTemplates() {
		return templateService.getAllTemplates();
	}

	@PostMapping("/create")
	public ResponseEntity<Template> createTemplate1(@RequestBody Template template) {
		// Template template = template.getTemplate();
		List<TextElement> textElements = template.getTextElements();

		// Call the service method to create the template with text elements
		Template savedTemplate = templateService.createTemplateWithTextElements(template, textElements);

		return ResponseEntity.ok(savedTemplate);
	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getTextElementHtmlByTemplateId(@PathVariable Long id) {
		String htmlMarkup = templateService.getById(id);
		if (htmlMarkup != null) {
			return ResponseEntity.ok(htmlMarkup);
		}
		return ResponseEntity.notFound().build();
	}

}
