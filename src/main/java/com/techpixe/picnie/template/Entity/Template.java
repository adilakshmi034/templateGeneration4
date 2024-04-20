package com.techpixe.picnie.template.Entity;

import java.util.List;

import com.techpixe.picnie.template.dto.TextElementDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Template {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "type")
	private String type;

//	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<TextElement> textElements;
//	
//	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private List<ImageElement> imageElements;

   @OneToOne
	private TextElement textElements;
	
	@OneToOne
	private ImageElement imageElements;

}
