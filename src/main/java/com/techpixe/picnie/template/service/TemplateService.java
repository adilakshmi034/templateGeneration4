package com.techpixe.picnie.template.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.dto.TemplateDto;

public interface TemplateService {

	//Template createTemplate(Template template);

	List<Template> getAllTemplates();

//	String getById(long id);

	Template createTemplate(String templateName, String type, String textElementName, String textElementType,
			String textElementText, int textElementTextSize, String textElementFontStyle, String textElementTextColor,
			int textElementAngle, int textElementDestX, int textElementDestY, int textElementMaxLength,
			int textElementMaxLines, double textElementLetterSpacing, double textElementInputLineHeight,
			String textElementTextAlign, String imageElementType, String imageElementName, MultipartFile imageElementImageUrl);

	

	// Template createTemplate(Template template);

}
