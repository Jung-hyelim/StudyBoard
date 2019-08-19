package com.jhl.studyboard.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.jhl.studyboard.entity.PhotoText;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhotoTextDTO {

	@NotNull @Min(0) private double position_x;
	@NotNull @Min(0) private double position_y;
	@NotNull private String text;
	
	public PhotoTextDTO(PhotoText photoText) {
		this.position_x = photoText.getPosition_x();
		this.position_y = photoText.getPosition_y();
		this.text = photoText.getText();
	}
	
	public PhotoTextDTO(PhotoTextDTO photoText) {
		this.position_x = photoText.getPosition_x();
		this.position_y = photoText.getPosition_y();
		this.text = photoText.getText();
	}
}
