package com.jhl.StudyBoard;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyBoardApplicationTests {

	@Autowired
	private DocumentService documentService;
	
	@Test
	@Transactional
	public void createDocument() {
		Document document = new Document();
		document.setTitle("테스트");
		document.setContent("테스트입니다.");
		documentService.createDocument(document);
	}
}
