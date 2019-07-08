package com.jhl.studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.studyboard.data.DocumentData;
import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.dto.DocumentListDTO;
import com.jhl.studyboard.dto.DocumentListItemDTO;
import com.jhl.studyboard.dto.PhotoDTO;
import com.jhl.studyboard.dto.PhotoTextDTO;
import com.jhl.studyboard.dto.TagDTO;
import com.jhl.studyboard.service.RedisService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;
	
	@Value("${spring.redis.expireTime}")
	private long redisExpireTime;

	@Test
	public void 레디스에데이터저장_후_가져오기_5초후_레디스데이터파기() {
		DocumentDTO data = DocumentData.initData();
		data.setId(99L);
		
		redisService.setDocumentRedis(data.getId(), data);

		DocumentDTO get = redisService.getDocumentRedis(data.getId());
		assertThat(get.getId()).isEqualTo(data.getId());
		assertThat(get.getTitle()).isEqualTo(data.getTitle());
		assertThat(get.getContent()).isEqualTo(data.getContent());
		
		// photos
		List<PhotoDTO> getPhotos = get.getPhotos();
		List<PhotoDTO> dataPhotos = data.getPhotos();
		assertThat(getPhotos.size()).isEqualTo(dataPhotos.size());
		for(int i = 0; i < getPhotos.size(); i++){
			assertThat(getPhotos.get(i).getFile_path()).isEqualTo(dataPhotos.get(i).getFile_path());
			assertThat(getPhotos.get(i).getFile_name()).isEqualTo(dataPhotos.get(i).getFile_name());
			
			// photo texts
			List<PhotoTextDTO> getPhotoTexts = getPhotos.get(i).getPhoto_texts();
			List<PhotoTextDTO> dataPhotoTexts = dataPhotos.get(i).getPhoto_texts();
			assertThat(getPhotoTexts.size()).isEqualTo(dataPhotoTexts.size());
			for(int j = 0; j < getPhotoTexts.size(); j++){
				assertThat(getPhotoTexts.get(j).getPosition_x()).isEqualTo(dataPhotoTexts.get(j).getPosition_x());
				assertThat(getPhotoTexts.get(j).getPosition_y()).isEqualTo(dataPhotoTexts.get(j).getPosition_y());
				assertThat(getPhotoTexts.get(j).getText()).isEqualTo(dataPhotoTexts.get(j).getText());
			}
		}
		
		// tags
		List<TagDTO> getTags = get.getTags();
		List<TagDTO> dataTags = data.getTags();
		assertThat(getTags).isNotEmpty();
		assertThat(getTags.size()).isEqualTo(dataTags.size());
		for(int i = 0; i < getTags.size(); i++){
			assertThat(getTags.get(i).getName()).isEqualTo(dataTags.get(i).getName());
		}
		
		try {
			Thread.sleep(redisExpireTime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		DocumentDTO afterSleep = redisService.getDocumentRedis(data.getId());
		assertThat(afterSleep).isNull();
	}
	
	@Test
	public void 레디스에리스트_저장() {
		int page = 1;
		DocumentListItemDTO data1 = DocumentListItemDTO.builder()
										.id(88L)
										.title("title1")
										.content("content1")
										.build();
		DocumentListItemDTO data2 = DocumentListItemDTO.builder()
										.id(77L)
										.title("title2")
										.content("content2")
										.build();
		List<DocumentListItemDTO> listData = new ArrayList<DocumentListItemDTO>();
		listData.add(data1);
		listData.add(data2);
		
		DocumentListDTO list = new DocumentListDTO();
		list.setPage(page);
		list.setTotalPages(page);
		list.setList(listData);
		
		redisService.setDocumentListRedis(page, list);
		
		DocumentListDTO get = redisService.getDocumentListRedis(page);
		assertThat(get).isNotNull();
		assertThat(get.getPage()).isEqualTo(list.getPage());
		assertThat(get.getTotalPages()).isEqualTo(list.getTotalPages());
		assertThat(get.getList().size()).isEqualTo(list.getList().size());
		for(int i = 0; i < get.getList().size(); i++) {
			DocumentListItemDTO getItem = get.getList().get(i);
			DocumentListItemDTO listItem = list.getList().get(i);
			assertThat(getItem.getId()).isEqualTo(listItem.getId());
			assertThat(getItem.getTitle()).isEqualTo(listItem.getTitle());
			assertThat(getItem.getContent()).isEqualTo(listItem.getContent());
		}

		try {
			Thread.sleep(redisExpireTime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		DocumentListDTO afterSleep = redisService.getDocumentListRedis(page);
		assertThat(afterSleep).isNull();
	}
}
