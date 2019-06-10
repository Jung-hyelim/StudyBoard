package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.data.DocumentData;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectTest {

	@Autowired
	private DocumentService documentService;

	private Document saved;
	
	@Before
	public void insert() {
		saved = documentService.insert(DocumentData.initData());
	}

	@Test
	public void select() {
		// select
		Document selected = documentService.findById(saved.getId());
		
		// select test - document
		assertThat(selected).isNotNull();
		assertThat(selected.getId()).isEqualTo(saved.getId());
		assertThat(selected.getTitle()).isEqualTo(saved.getTitle());
		assertThat(selected.getContent()).isEqualTo(saved.getContent());
		
		// select test - photos
		List<Photo> selectedPhotos = selected.getPhotos();
		assertThat(selectedPhotos).isNotNull();
		assertThat(selectedPhotos).isNotEmpty();
		assertThat(selectedPhotos.size()).isEqualTo(saved.getPhotos().size());
		for(int i = 0; i < selectedPhotos.size(); i++){
			assertThat(selectedPhotos.get(i).getFile_name()).isEqualTo(saved.getPhotos().get(i).getFile_name());
			assertThat(selectedPhotos.get(i).getDocument().getId()).isEqualTo(saved.getPhotos().get(i).getDocument().getId());
		}
	}
}
