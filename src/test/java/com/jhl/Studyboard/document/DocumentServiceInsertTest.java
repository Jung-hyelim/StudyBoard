package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.data.DocumentData;
import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.DocumentAndTag;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.entity.PhotoText;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceInsertTest {

	@Autowired
	private DocumentService documentService;

	@Test
	public void insert() {
		Document data = DocumentData.initData();

		// insert
		Document saved = documentService.insert(data);
		
		// document
		assertThat(saved).isNotNull();
		assertThat(saved.getTitle()).isEqualTo(data.getTitle());
		assertThat(saved.getContent()).isEqualTo(data.getContent());
		
		// photos
		List<Photo> savedPhotos = saved.getPhotos();
		List<Photo> dataPhotos = data.getPhotos();
		assertThat(savedPhotos).isNotEmpty();
		assertThat(savedPhotos.size()).isEqualTo(dataPhotos.size());
		for(int i = 0; i < savedPhotos.size(); i++){
			assertThat(savedPhotos.get(i).getDocument().getId()).isEqualTo(dataPhotos.get(i).getDocument().getId());
			assertThat(savedPhotos.get(i).getFile_path()).isEqualTo(dataPhotos.get(i).getFile_path());
			assertThat(savedPhotos.get(i).getFile_name()).isEqualTo(dataPhotos.get(i).getFile_name());
			
			// photo texts
			List<PhotoText> savedPhotoTexts = savedPhotos.get(i).getPhoto_texts();
			List<PhotoText> dataPhotoTexts = dataPhotos.get(i).getPhoto_texts();
			assertThat(savedPhotoTexts).isNotNull();
			assertThat(savedPhotoTexts.size()).isEqualTo(dataPhotoTexts.size());
			for(int j = 0; j < savedPhotoTexts.size(); j++){
				assertThat(savedPhotoTexts.get(j).getPhoto().getId()).isEqualTo(dataPhotoTexts.get(j).getPhoto().getId());
				assertThat(savedPhotoTexts.get(j).getPosition_x()).isEqualTo(dataPhotoTexts.get(j).getPosition_x());
				assertThat(savedPhotoTexts.get(j).getPosition_y()).isEqualTo(dataPhotoTexts.get(j).getPosition_y());
				assertThat(savedPhotoTexts.get(j).getText()).isEqualTo(dataPhotoTexts.get(j).getText());
			}
		}
		
		// tags
		List<DocumentAndTag> savedMappings = saved.getMappings();
		List<DocumentAndTag> dataMappings = data.getMappings();
		assertThat(savedMappings).isNotEmpty();
		assertThat(savedMappings.size()).isEqualTo(dataMappings.size());
		savedMappings.stream().forEach(m -> {
			assertThat(dataMappings.contains(m)).isTrue();
		});
	}
}
