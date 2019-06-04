package com.jhl.StudyBoard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.transaction.Transactional;

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
	
	// Data
	public Document document() {
		Document document = new Document();
		document.setTitle("title");
		document.setContent("content #new #test code");
		List<Photo> photos = new ArrayList<Photo>();
		for(int i = 0; i < PHOTO_SIZE; i++){
			Photo photo = new Photo(document, "file"+i);
			photos.add(photo);
		}
		document.setPhotos(photos);
		return document;
	}
	
	@Test
	public void test10_insert() throws Exception {
		Document document = document();
		Document saved = documentService.insert(document);
		
		assertThat(saved).isNotNull();
		assertThat(saved.getId()).isEqualTo(1L);
		assertThat(saved.getTitle()).isEqualTo(document.getTitle());
		assertThat(saved.getContent()).isEqualTo(document.getContent());
		assertThat(saved.getPhotos()).isNotEmpty();
		assertThat(saved.getPhotos().size()).isEqualTo(PHOTO_SIZE);
	}
	
	@Test
	@Transactional
	public void test20_findById() throws Exception {
		Document selected = documentService.findById(1L).get();
		
		// document
		Document document = document();
		assertThat(selected).isNotNull();
		assertThat(selected.getId()).isEqualTo(1L);
		assertThat(selected.getTitle()).isEqualTo(document.getTitle());
		assertThat(selected.getContent()).isEqualTo(document.getContent());
		
		// photos
		List<Photo> selectedPhotos = selected.getPhotos();
		assertThat(selectedPhotos.size()).isEqualTo(PHOTO_SIZE);
		for(int i = 0; i < selectedPhotos.size(); i++){
			assertThat(selectedPhotos.get(i).getId()).isEqualTo(i+1);
			assertThat(selectedPhotos.get(i).getFile_name()).isEqualTo("file"+i);
			assertThat(selectedPhotos.get(i).getDocument().getId()).isEqualTo(1L);
		}
	}
	
	@Test
	@Transactional
	public void test30_update() throws Exception {
		Document document = documentService.findById(1L).get();
		assertThat(document).isNotNull();
		document.setTitle("change title");
		document.setContent("#change #content #test code");
		
		List<Photo> photos = document.getPhotos();
		assertThat(photos.size()).isEqualTo(PHOTO_SIZE);
		photos.add(new Photo(document, "file3"));
		document.setPhotos(photos);
		
		// update
		Document updated = documentService.update(document, 1L);

		// document
		assertThat(updated).isNotNull();
		assertThat(updated.getId()).isEqualTo(1L);
		assertThat(updated.getTitle()).isEqualTo(document.getTitle());
		assertThat(updated.getContent()).isEqualTo(document.getContent());
		
		// photos
		List<Photo> updatedPhotos = updated.getPhotos();
		assertThat(updatedPhotos.size()).isEqualTo(PHOTO_SIZE+1);
		for(int i = 0; i < updatedPhotos.size(); i++){
			assertThat(updatedPhotos.get(i).getId()).isEqualTo(i+1);
			assertThat(updatedPhotos.get(i).getFile_name()).isEqualTo("file"+i);
			assertThat(updatedPhotos.get(i).getDocument().getId()).isEqualTo(1L);
		}
	}
	
	@Test
	public void test40_delete() throws Exception {
		// before delete
		Optional<Document> before = documentService.findById(1L);
		assertThat(before).isNotEmpty();
		
		documentService.delete(1L);
		
		// after delete
		Optional<Document> after = documentService.findById(1L);
		assertThat(after).isEmpty();
	}
}
