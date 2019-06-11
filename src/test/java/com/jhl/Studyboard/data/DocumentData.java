package com.jhl.StudyBoard.data;

import java.util.ArrayList;
import java.util.List;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.DocumentAndTag;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.entity.PhotoText;
import com.jhl.StudyBoard.entity.Tag;

public class DocumentData {

	public static Document initData() {
		Document document = new Document(null, "title", "content #new #test code");

		Photo photo1 = new Photo(null, document, "/photos_file", "file1");
		Photo photo2 = new Photo(null, document, "/photos_file", "file2");
		Photo photo3 = new Photo(null, document, "/photos_file", "file3");

		PhotoText photoText1 = new PhotoText(null, photo1, 1, 1, "text1");
		PhotoText photoText2 = new PhotoText(null, photo1, 10.5, 7.4, "text2");
		
		Tag tag1 = new Tag(null, "new");
		Tag tag2 = new Tag(null, "test");

		DocumentAndTag mapping1 = new DocumentAndTag(null, document, tag1);
		DocumentAndTag mapping2 = new DocumentAndTag(null, document, tag2);
		
		List<DocumentAndTag> mappings = new ArrayList<DocumentAndTag>();
		mappings.add(mapping1);
		mappings.add(mapping2);
		document.setMappings(mappings);

		photo1.addText(photoText1);
		photo1.addText(photoText2);
		
		document.addPhoto(photo1);
		document.addPhoto(photo2);
		document.addPhoto(photo3);

		return document;
	}
	
	public static Document updateData(Long id) {
		Document document = new Document(id, "change title", "#change #content #test code");

		Photo photo1 = new Photo(null, document, "/photos_file", "file1");
		Photo photo2 = new Photo(null, document, "/photos_file", "file2");

		PhotoText photoText1 = new PhotoText(null, photo1, 9.1, 1.9, "update text");
		
		Tag tag1 = new Tag(null, "change");
		Tag tag2 = new Tag(null, "content");
		Tag tag3 = new Tag(null, "test");

		DocumentAndTag mapping1 = new DocumentAndTag(null, document, tag1);
		DocumentAndTag mapping2 = new DocumentAndTag(null, document, tag2);
		DocumentAndTag mapping3 = new DocumentAndTag(null, document, tag3);
		
		List<DocumentAndTag> mappings = new ArrayList<DocumentAndTag>();
		mappings.add(mapping1);
		mappings.add(mapping2);
		mappings.add(mapping3);
		document.setMappings(mappings);
		
		photo1.addText(photoText1);
		
		document.addPhoto(photo1);
		document.addPhoto(photo2);
		
		return document;
	}
}
