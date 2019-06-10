package com.jhl.StudyBoard.data;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.entity.PhotoText;

public class DocumentData {

	public static int PHOTO_INIT_SIZE = 3;
	public static int PHOTO_TEXT_INIT_SIZE = 2;
	
	
	public static Document initData() {
		Document document = new Document(null, "title", "content #new #test code");

		Photo photo1 = new Photo(null, document, "/photos_file", "file1");
		Photo photo2 = new Photo(null, document, "/photos_file", "file2");
		Photo photo3 = new Photo(null, document, "/photos_file", "file3");

		PhotoText photoText1 = new PhotoText(null, photo1, 1, 1, "text1");
		PhotoText photoText2 = new PhotoText(null, photo1, 10.5, 7.4, "text2");
		
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
		
		photo1.addText(photoText1);
		
		document.addPhoto(photo1);
		document.addPhoto(photo2);
		
		return document;
	}
}
