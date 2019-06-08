package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.entity.Photo;
import com.jhl.StudyBoard.service.DocumentService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DocumentServiceTest {

	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DocumentService documentService;

	private static int PHOTO_SIZE = 3;
	
	@Before
	public void di() throws Exception {
		try(Connection connection = datasource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			assertThat(metaData.getURL()).isEqualTo("jdbc:h2:mem:testdb");
			assertThat(metaData.getDriverName()).isEqualTo("H2 JDBC Driver");
		}
	}
	
	@Test
	public void full_test() {
		// insert data
		Document document = new Document(null, "title", "content #new #test code");
		for(int i = 0; i < PHOTO_SIZE; i++){
			document.addPhoto(new Photo(null, document, "/photos_file", "file"+i));
		}
		
		// insert
		Document saved = documentService.insert(document);
		
		// insert test
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isEqualTo(1L);
		assertThat(saved.getTitle()).isEqualTo(document.getTitle());
		assertThat(saved.getContent()).isEqualTo(document.getContent());
		assertThat(saved.getPhotos()).isNotEmpty();
		assertThat(saved.getPhotos().size()).isEqualTo(PHOTO_SIZE);
		
		// update data
		System.out.println(">>"+saved.getId());
		Document newDocument = new Document(saved.getId(), "change title", "#change #content #test code");
		newDocument.addPhoto(new Photo(null, null, "/photos_file", "file0"));
		newDocument.addPhoto(new Photo(null, null, "/photos_file", "file99"));
		
		// update
		Document updated = documentService.update(newDocument);

		// update test - document
		assertThat(updated).isNotNull();
		assertThat(updated.getId()).isEqualTo(newDocument.getId());
		assertThat(updated.getTitle()).isEqualTo(newDocument.getTitle());
		assertThat(updated.getContent()).isEqualTo(newDocument.getContent());
		
		// update test - photos
		List<Photo> updatedPhotos = updated.getPhotos();
		assertThat(updatedPhotos.size()).isEqualTo(2);
		assertThat(updatedPhotos.get(0).getFile_name()).isEqualTo(newDocument.getPhotos().get(0).getFile_name());
		assertThat(updatedPhotos.get(0).getDocument().getId()).isEqualTo(newDocument.getId());
		assertThat(updatedPhotos.get(1).getFile_name()).isEqualTo(newDocument.getPhotos().get(1).getFile_name());
		assertThat(updatedPhotos.get(1).getDocument().getId()).isEqualTo(newDocument.getId());

		// select
		Document selected = documentService.findById(updated.getId());
		
		// select test - document
		assertThat(selected).isNotNull();
		assertThat(selected.getId()).isEqualTo(updated.getId());
		assertThat(selected.getTitle()).isEqualTo(updated.getTitle());
		assertThat(selected.getContent()).isEqualTo(updated.getContent());
		
		// select test - photos
		List<Photo> selectedPhotos = selected.getPhotos();
		assertThat(selectedPhotos).isNotNull();
		assertThat(selectedPhotos).isNotEmpty();
		assertThat(selectedPhotos.size()).isEqualTo(updated.getPhotos().size());
		for(int i = 0; i < selectedPhotos.size(); i++){
			assertThat(selectedPhotos.get(i).getFile_name()).isEqualTo(updated.getPhotos().get(i).getFile_name());
			assertThat(selectedPhotos.get(i).getDocument().getId()).isEqualTo(updated.getPhotos().get(i).getDocument().getId());
		}
		
		// delete
		documentService.delete(saved.getId());
		
		// delete - test
		Document deleted = null;
		try{
			deleted = documentService.findById(saved.getId());
		} catch(Exception e){
			assertThat(deleted).withFailMessage(e.getMessage());
		}
	}
}
