package com.techpixe.picnie.template.service.impl;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.picnie.template.Entity.ImageElement;
import com.techpixe.picnie.template.Entity.Template;
import com.techpixe.picnie.template.Entity.TextElement;
import com.techpixe.picnie.template.repository.TemplateRepository;
import com.techpixe.picnie.template.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {
	private static final String UPLOAD_FOLDER = "C://Users//pc//Desktop//image//";
	@Autowired
	private TemplateRepository templateRepository;

	@Override
	public List<Template> getAllTemplates() {
		return templateRepository.findAll();
	}

//	@Override
//	public Template createTemplate(Template template) {
//		List<TextElement> textElements = new ArrayList<>();
//		for (TextElement textElement : template.getTextElements()) {
//			textElement.setTemplate(template);
//			textElement.setName(textElement.getName());
//			textElement.setText(textElement.getText());
//			textElement.setTextSize(textElement.getTextSize());
//			textElement.setFontStyle(textElement.getFontStyle());
//			textElement.setTextColor(textElement.getTextColor());
//			textElement.setAngle(textElement.getAngle());
//			textElement.setDestX(textElement.getDestX());
//			textElement.setDestY(textElement.getDestY());
//			textElement.setMaxLength(textElement.getMaxLength());
//			textElement.setMaxLines(textElement.getMaxLines());
//			textElement.setLetterSpacing(textElement.getLetterSpacing());
//			textElement.setInputLineHeight(textElement.getInputLineHeight());
//			textElement.setTextAlign(textElement.getTextAlign());
//			// Add the text element to the list
//			textElements.add(textElement);
//			template.setTextElements(textElements);
//		}
//
//		// Save the template with its associated text elements
//		return templateRepository.save(template);
//	}

//	@Override
//	public String getById(long id) {
//		Optional<Template> templateOptional = templateRepository.findById(id);
//		if (templateOptional.isPresent()) {
//			Template template = templateOptional.get();
//			List<TextElement> textElements = template.getTextElements();
//			if (!textElements.isEmpty()) {
//				StringBuilder htmlBuilder = new StringBuilder();
//				for (TextElement textElement : textElements) {
//					htmlBuilder.append("<div style=\"");
//					htmlBuilder.append("font-style: ").append(textElement.getFontStyle()).append("; ");
//					htmlBuilder.append("font-size: ").append(textElement.getTextSize()).append("px; ");
//					htmlBuilder.append("color: rgb(").append(textElement.getTextColor()).append(");");
//					htmlBuilder.append("transform: rotate(").append(textElement.getAngle()).append("deg);");
//					htmlBuilder.append("position: absolute; left: ").append(textElement.getDestX()).append("%; top: ")
//							.append(textElement.getDestY()).append("%;");
//					htmlBuilder.append("max-width: ").append(textElement.getMaxLength()).append("%;");
//					htmlBuilder.append("line-height: ").append(textElement.getInputLineHeight()).append(";");
//					htmlBuilder.append("letter-spacing: ").append(textElement.getLetterSpacing()).append("px;");
//					htmlBuilder.append("text-align: ").append(textElement.getTextAlign()).append(";\">");
//					htmlBuilder.append(textElement.getText());
//					htmlBuilder.append("</div>");
//				}
//				return htmlBuilder.toString();
//			}
//			return "No text elements found for the template.";
//		}
//		return "Template not found.";
//	}

	@Override
	public Template createTemplate(String templateName, String type, String textElementName, String textElementType,
			String textElementText, int textElementTextSize, String textElementFontStyle, String textElementTextColor,
			int textElementAngle, int textElementDestX, int textElementDestY, int textElementMaxLength,
			int textElementMaxLines, double textElementLetterSpacing, double textElementInputLineHeight,
			String textElementTextAlign, String imageElementType, String imageElementName,
			MultipartFile imageElementImageUrl) {
		Template template = new Template();
		template.setTemplateName(templateName);
		template.setType(type);

		TextElement textElement = new TextElement();
		textElement.setName(textElementName);
		textElement.setType(textElementType);
		textElement.setText(textElementText);
		textElement.setTextSize(textElementTextSize);
		textElement.setFontStyle(textElementFontStyle);
		textElement.setTextColor(textElementTextColor);
		textElement.setAngle(textElementAngle);
		textElement.setDestX(textElementDestX);
		textElement.setDestY(textElementDestY);
		textElement.setMaxLength(textElementMaxLength);
		textElement.setMaxLines(textElementMaxLines);
		textElement.setLetterSpacing(textElementLetterSpacing);
		textElement.setInputLineHeight(textElementInputLineHeight);
		textElement.setTextAlign(textElementTextAlign);

//		template.getTextElements().add(textElement);
		template.setTextElements(textElement);

		String imageUrl = uploadImageToFileSystem(imageElementImageUrl);
		// Create the image element
		ImageElement imageElement = new ImageElement();
		imageElement.setTemplate(template);
		imageElement.setImageUrl(imageUrl);
		imageElement.setName(imageElementName);
		imageElement.setType(imageElementType);

		template.setImageElements(imageElement);
		
		templateRepository.save(template);
		return template;
	}

	private String uploadImageToFileSystem(MultipartFile imageElementImageUrl) {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(imageElementImageUrl.getOriginalFilename()));
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		String generatedFileName = UUID.randomUUID().toString() + fileExtension;

		// Create the directory if it doesn't exist
		Path uploadPath = Paths.get(UPLOAD_FOLDER);
		if (!Files.exists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Copy the image file to the upload directory
		Path filePath = uploadPath.resolve(generatedFileName);
		try {
			Files.copy(imageElementImageUrl.getInputStream(), filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Return the file path or URL where the image is stored
		return UPLOAD_FOLDER + generatedFileName;
	}
}
