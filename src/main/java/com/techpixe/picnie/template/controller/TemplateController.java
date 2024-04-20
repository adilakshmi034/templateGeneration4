package com.techpixe.picnie.template.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.ImageElement;
import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.service.TemplateService;

@RestController
@RequestMapping("/templates")

public class TemplateController {
	@Autowired
	private TemplateService templateService;

	@GetMapping
	public List<Template> getAllTemplates() {
		return templateService.getAllTemplates();
	}

//	@PostMapping("/create")
//	public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
//		templateService.createTemplate(template);
//		return ResponseEntity.ok(template);
//
//	}

	@PostMapping("/create")
	public Template createTemplate( @RequestParam("templateName") String templateName,
	        @RequestParam("type") String type,
	        @RequestParam(value = "textElementName", required = false) String textElementName,
	        @RequestParam(value = "textElementType", required = false) String textElementType,
	        @RequestParam(value = "textElementText", required = false) String textElementText,
	        @RequestParam(value = "textElementTextSize", required = false) Integer textElementTextSize,
	        @RequestParam(value = "textElementFontStyle", required = false) String textElementFontStyle,
	        @RequestParam(value = "textElementTextColor", required = false) String textElementTextColor,
	        @RequestParam(value = "textElementAngle", required = false) Integer textElementAngle,
	        @RequestParam(value = "textElementDestX", required = false) Integer textElementDestX,
	        @RequestParam(value = "textElementDestY", required = false) Integer textElementDestY,
	        @RequestParam(value = "textElementMaxLength", required = false) Integer textElementMaxLength,
	        @RequestParam(value = "textElementMaxLines", required = false) Integer textElementMaxLines,
	        @RequestParam(value = "textElementLetterSpacing", required = false) Double textElementLetterSpacing,
	        @RequestParam(value = "textElementInputLineHeight", required = false) Double textElementInputLineHeight,
	        @RequestParam(value = "textElementTextAlign", required = false) String textElementTextAlign,
	        @RequestParam(value = "imageElementName") String imageElementName,
	        @RequestParam(value = "imageElementType") String imageElementType,
	        @RequestParam(value = "imageElementImageUrl") MultipartFile imageElementImageUrl) {

		Template template = templateService.createTemplate(templateName, type, textElementName, textElementType,
				textElementText, textElementTextSize, textElementFontStyle, textElementTextColor, textElementAngle,
				textElementDestX, textElementDestY, textElementMaxLength, textElementMaxLines, textElementLetterSpacing,
				textElementInputLineHeight, textElementTextAlign, imageElementType, imageElementName,
				imageElementImageUrl);
		return template;

	}

//	@GetMapping("/{id}")
//	public ResponseEntity<String> getTextElementHtmlByTemplateId(@PathVariable Long id) {
//		String htmlMarkup = templateService.getById(id);
//		if (htmlMarkup != null) {
//			return ResponseEntity.ok(htmlMarkup);
//		}
//		return ResponseEntity.notFound().build();
//	}

}
