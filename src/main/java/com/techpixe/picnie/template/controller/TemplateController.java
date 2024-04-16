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
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.ImageElement;
import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.dto.ImageElementDTO;
import com.techpixe.picnie.template.dto.TemplateDto;
import com.techpixe.picnie.template.dto.TextElementDTO;
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
	@PostMapping
	public ResponseEntity<?> createTemplate(@RequestParam("templateName") String templateName,
	                                        @RequestParam("type") String type,
	                                        @RequestParam("textElements") List<TextElementDTO> textElements,
	                                        @RequestParam("imageElements") List<MultipartFile> imageFiles) {
	    TemplateDto templateDTO = new TemplateDto();
	    templateDTO.setTemplateName(templateName);
	    templateDTO.setType(type);
	    templateDTO.setTextElements(textElements);
	    
	    // Convert MultipartFile to ImageElementDTO
	    List<ImageElementDTO> imageElements = new ArrayList<>();
	    for (MultipartFile imageFile : imageFiles) {
	        ImageElementDTO imageElementDTO = new ImageElementDTO();
	        // Set other properties for image element if needed
	        imageElements.add(imageElementDTO);
	    }
	    templateDTO.setImageElements(imageElements);

	    Template template = templateService.createTemplate(templateDTO);
	    return ResponseEntity.ok(template);
	}

//	@PostMapping("/create")
//	public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
//		// Template template = template.getTemplate();
//		List<TextElement> textElements = template.getTextElements();
//
//		// Call the service method to create the template with text elements
//		Template savedTemplate = templateService.createTemplateWithTextElements(template, textElements);
//
//		return ResponseEntity.ok(savedTemplate);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<String> getTextElementHtmlByTemplateId(@PathVariable Long id) {
		String htmlMarkup = templateService.getById(id);
		if (htmlMarkup != null) {
			return ResponseEntity.ok(htmlMarkup);
		}
		return ResponseEntity.notFound().build();
	}

}
