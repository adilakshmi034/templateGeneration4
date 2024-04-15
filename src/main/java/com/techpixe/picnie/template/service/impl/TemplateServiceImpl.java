package com.techpixe.picnie.template.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.repository.TemplateRepository;
import com.techpixe.picnie.template.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	@Autowired
	private TemplateRepository templateRepository;

	@Override
	public List<Template> getAllTemplates() {
		return templateRepository.findAll();
	}

	@Override
	public Template createTemplateWithTextElements(Template template, List<TextElement> textElements) {
		textElements.forEach(textElement -> textElement.setTemplate(template));

		// Set the text elements for the template
		template.setTextElements(textElements);

		// Save the template with its associated text elements
		return templateRepository.save(template);
	}

	@Override
	public String getById(long id) {
		 Optional<Template> templateOptional = templateRepository.findById(id);
	        if (templateOptional.isPresent()) {
	            Template template = templateOptional.get();
	            List<TextElement> textElements = template.getTextElements();
	            if (!textElements.isEmpty()) {
	                StringBuilder htmlBuilder = new StringBuilder();
	                for (TextElement textElement : textElements) {
	                    htmlBuilder.append("<div style=\"");
	                    htmlBuilder.append("font-style: ").append(textElement.getFontStyle()).append("; ");
	                    htmlBuilder.append("font-size: ").append(textElement.getTextSize()).append("px; ");
	                    htmlBuilder.append("color: rgb(").append(textElement.getTextColor()).append(");");
	                    htmlBuilder.append("transform: rotate(").append(textElement.getAngle()).append("deg);");
	                    htmlBuilder.append("position: absolute; left: ").append(textElement.getDestX()).append("%; top: ")
	                            .append(textElement.getDestY()).append("%;");
	                    htmlBuilder.append("max-width: ").append(textElement.getMaxLength()).append("%;");
	                    htmlBuilder.append("line-height: ").append(textElement.getInputLineHeight()).append(";");
	                    htmlBuilder.append("letter-spacing: ").append(textElement.getLetterSpacing()).append("px;");
	                    htmlBuilder.append("text-align: ").append(textElement.getTextAlign()).append(";\">");
	                    htmlBuilder.append(textElement.getText());
	                    htmlBuilder.append("</div>");
	                }
	                return htmlBuilder.toString();
	            }
	            return "No text elements found for the template.";
	        }
	        return "Template not found.";
	}

}
