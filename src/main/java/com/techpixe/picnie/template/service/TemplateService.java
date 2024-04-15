package com.techpixe.picnie.template.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;

public interface TemplateService {

	Template createTemplateWithTextElements(Template template, List<TextElement> textElements);

	List<Template> getAllTemplates();

	String getById(long id);

}
