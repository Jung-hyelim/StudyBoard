package com.jhl.StudyBoard.data;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Photo;

public class DocumentData {

	public static int PHOTO_INIT_SIZE = 3;
	public static int PHOTO_UPDATE_SIZE = 2;
	
	public static Document initData() {
		Document document = new Document(null, "title", "content #new #test code");
		for(int i = 0; i < PHOTO_INIT_SIZE; i++){
			document.addPhoto(new Photo(null, document, "/photos_file", "file"+i));
		}
		return document;
	}
	
	public static Document updateData(Long id) {
		Document document = new Document(id, "change title", "#change #content #test code");
		document.addPhoto(new Photo(null, null, "/photos_file", "file0"));
		document.addPhoto(new Photo(null, null, "/photos_file", "file99"));
		return document;
	}
}
