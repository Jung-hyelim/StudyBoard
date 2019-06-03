package com.jhl.StudyBoard.document;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.entity.Document;
import com.jhl.StudyBoard.repository.DocumentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentRepositoryTest {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Test
	public void di() throws Exception {
		try(Connection connection = datasource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getURL());
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getUserName());
		}
	}
	
	@Test
	public void test() throws SQLException {
		Document document = new Document();
		document.setTitle("테스트 제목");
		document.setContent("테스트 내용.");
		
		Document saved = documentRepository.save(document);
		assertThat(saved).isNotNull();
		
		Optional<Document> getDocument = documentRepository.findById(1L);
		assertThat(getDocument).isNotEmpty();

		Optional<Document> getDocument2 = documentRepository.findById(999L);
		assertThat(getDocument2).isEmpty();
	}
}
