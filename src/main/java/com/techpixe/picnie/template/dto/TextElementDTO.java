package com.techpixe.picnie.template.dto;

import lombok.Data;

@Data
public class TextElementDTO {
	private String name;
	private String type;
	private String text;
	private int textSize;
	private String fontStyle;
	private String textColor;
	private double angle;
	private double destX;
	private double destY;
	private double maxLength;
	private int maxLines;
	private double letterSpacing;
	private double inputLineHeight;
	private String textAlign;

}
