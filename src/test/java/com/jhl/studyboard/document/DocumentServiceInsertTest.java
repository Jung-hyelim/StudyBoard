package com.jhl.studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.studyboard.data.DocumentData;
import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.dto.PhotoDTO;
import com.jhl.studyboard.dto.PhotoTextDTO;
import com.jhl.studyboard.dto.TagDTO;
import com.jhl.studyboard.entity.Document;
import com.jhl.studyboard.entity.DocumentAndTag;
import com.jhl.studyboard.entity.Photo;
import com.jhl.studyboard.entity.PhotoText;
import com.jhl.studyboard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceInsertTest {

	@Autowired
	private DocumentService documentService;

	private Long savedDocumentId;

	@After
	public void after() {
		documentService.delete(savedDocumentId);
	}

	@Test
	public void insert() {
		DocumentDTO data = DocumentData.initData();

		// insert
		Document saved = documentService.insert(data);
		savedDocumentId = saved.getId();
		
		// document
		assertThat(saved).isNotNull();
		assertThat(saved.getTitle()).isEqualTo(data.getTitle());
		assertThat(saved.getContent()).isEqualTo(data.getContent());
		
		// photos
		List<Photo> savedPhotos = saved.getPhotos();
		List<PhotoDTO> dataPhotos = data.getPhotos();
		assertThat(savedPhotos).isNotEmpty();
		assertThat(savedPhotos.size()).isEqualTo(dataPhotos.size());
		for(int i = 0; i < savedPhotos.size(); i++){
			assertThat(savedPhotos.get(i).getFile_path()).isEqualTo(dataPhotos.get(i).getFile_path());
			assertThat(savedPhotos.get(i).getFile_name()).isEqualTo(dataPhotos.get(i).getFile_name());
			
			// photo texts
			List<PhotoText> savedPhotoTexts = savedPhotos.get(i).getPhoto_texts();
			List<PhotoTextDTO> dataPhotoTexts = dataPhotos.get(i).getPhoto_texts();
			assertThat(savedPhotoTexts).isNotNull();
			assertThat(savedPhotoTexts.size()).isEqualTo(dataPhotoTexts.size());
			for(int j = 0; j < savedPhotoTexts.size(); j++){
				assertThat(savedPhotoTexts.get(j).getPosition_x()).isEqualTo(dataPhotoTexts.get(j).getPosition_x());
				assertThat(savedPhotoTexts.get(j).getPosition_y()).isEqualTo(dataPhotoTexts.get(j).getPosition_y());
				assertThat(savedPhotoTexts.get(j).getText()).isEqualTo(dataPhotoTexts.get(j).getText());
			}
		}
		
		// tags
		List<DocumentAndTag> savedMappings = saved.getMappings();
		List<TagDTO> dataMappings = data.getTags();
		assertThat(savedMappings).isNotEmpty();
		assertThat(savedMappings.size()).isEqualTo(dataMappings.size());
		for(int i = 0; i < savedMappings.size(); i++){
			assertThat(savedMappings.get(i).getTag().getName()).isEqualTo(dataMappings.get(i).getName());
		}
	}
}
