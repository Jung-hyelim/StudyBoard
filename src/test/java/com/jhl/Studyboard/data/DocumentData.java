package com.jhl.StudyBoard.data;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.PhotoDTO;
import com.jhl.StudyBoard.dto.PhotoTextDTO;
import com.jhl.StudyBoard.dto.TagDTO;

public class DocumentData {

	public static DocumentDTO initData() {
		DocumentDTO document = new DocumentDTO();
		document.setTitle("title");
		document.setContent("content #new #test code");

		PhotoDTO photo1 = new PhotoDTO("/photos_file", "file1");
		PhotoDTO photo2 = new PhotoDTO("/photos_file", "file2");
		PhotoDTO photo3 = new PhotoDTO("/photos_file", "file3");

		PhotoTextDTO photoText1 = new PhotoTextDTO(1, 1, "text1");
		PhotoTextDTO photoText2 = new PhotoTextDTO(10.5, 7.4, "text2");
		
		TagDTO tag1 = new TagDTO("new");
		TagDTO tag2 = new TagDTO("test");
		
		document.addTag(tag1);
		document.addTag(tag2);
		
		photo1.addText(photoText1);
		photo1.addText(photoText2);
		
		document.addPhoto(photo1);
		document.addPhoto(photo2);
		document.addPhoto(photo3);

		return document;
	}
	
	public static DocumentDTO updateData(Long id) {
		DocumentDTO document = new DocumentDTO();
		document.setId(id);
		document.setTitle("change title");
		document.setContent("#change #content #test code");

		PhotoDTO photo1 = new PhotoDTO("/photos_file", "file1");
		PhotoDTO photo2 = new PhotoDTO("/photos_file", "file2");

		PhotoTextDTO photoText1 = new PhotoTextDTO(9.1, 1.9, "update text");
		
		TagDTO tag1 = new TagDTO("change");
		TagDTO tag2 = new TagDTO("content");
		TagDTO tag3 = new TagDTO("test");
		
		document.addTag(tag1);
		document.addTag(tag2);
		document.addTag(tag3);
		
		photo1.addText(photoText1);
		
		document.addPhoto(photo1);
		document.addPhoto(photo2);
		
		return document;
	}
}
