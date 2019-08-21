package com.jhl.studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
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
import com.jhl.studyboard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DocumentServiceSelectTest {

	@Autowired
	private DocumentService documentService;

	private DocumentDTO data;
	
	@Before
	public void insert() {
		Document initData = documentService.insert(DocumentData.initData());
		data = new DocumentDTO(initData);
	}

	@After
	public void after() {
		documentService.delete(data.getId());
	}

	@Test
	public void select() {
		// select
		DocumentDTO selected = documentService.select(data.getId());
		
		// document
		assertThat(selected).isNotNull();
		assertThat(selected.getId()).isEqualTo(data.getId());
		assertThat(selected.getTitle()).isEqualTo(data.getTitle());
		assertThat(selected.getContent()).isEqualTo(data.getContent());
//		assertThat(selected.getRead_count()).isGreaterThan(0);
		
		// photos
		List<PhotoDTO> selectedPhotos = selected.getPhotos();
		List<PhotoDTO> dataPhotos = data.getPhotos();
		assertThat(selectedPhotos).isNotEmpty();
		assertThat(selectedPhotos.size()).isEqualTo(dataPhotos.size());
		for(int i = 0; i < selectedPhotos.size(); i++){
			assertThat(selectedPhotos.get(i).getFile_path()).isEqualTo(dataPhotos.get(i).getFile_path());
			assertThat(selectedPhotos.get(i).getFile_name()).isEqualTo(dataPhotos.get(i).getFile_name());
			
			// photo texts
			List<PhotoTextDTO> selectedPhotoTexts = selectedPhotos.get(i).getPhoto_texts();
			List<PhotoTextDTO> dataPhotoTexts = dataPhotos.get(i).getPhoto_texts();
			assertThat(selectedPhotoTexts).isNotNull();
			assertThat(selectedPhotoTexts.size()).isEqualTo(dataPhotoTexts.size());
			for(int j = 0; j < selectedPhotoTexts.size(); j++){
				assertThat(selectedPhotoTexts.get(j).getPosition_x()).isEqualTo(dataPhotoTexts.get(j).getPosition_x());
				assertThat(selectedPhotoTexts.get(j).getPosition_y()).isEqualTo(dataPhotoTexts.get(j).getPosition_y());
				assertThat(selectedPhotoTexts.get(j).getText()).isEqualTo(dataPhotoTexts.get(j).getText());
			}
		}
		
		// tags
		List<TagDTO> selectedTags = selected.getTags();
		List<TagDTO> dataTags = data.getTags();
		assertThat(selectedTags).isNotEmpty();
		assertThat(selectedTags.size()).isEqualTo(dataTags.size());
		for(int i = 0; i < selectedTags.size(); i++){
			assertThat(selectedTags.get(i).getName()).isEqualTo(dataTags.get(i).getName());
		}
	}
}
