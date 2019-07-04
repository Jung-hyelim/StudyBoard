package com.jhl.Studyboard.document;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.jhl.StudyBoard.dto.DocumentDTO;
import com.jhl.StudyBoard.dto.PhotoDTO;
import com.jhl.StudyBoard.dto.PhotoTextDTO;
import com.jhl.StudyBoard.dto.TagDTO;
import com.jhl.StudyBoard.service.RedisService;
import com.jhl.Studyboard.data.DocumentData;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {

	@Autowired
	private RedisService redisService;
	
	@Value("${spring.redis.expireTime}")
	private long redisExpireTime;

	private DocumentDTO data;
	
	@Before
	public void 테스트데이터셋팅() {
		data = DocumentData.initData();
		data.setId(99L);
	}
	
	@Test
	public void 레디스에데이터저장_후_가져오기_5초후_레디스데이터파기() {
		
		redisService.setRedis(data.getId(), data);

		DocumentDTO get = redisService.getRedis(data.getId());
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
		
		DocumentDTO afterSleep = redisService.getRedis(data.getId());
		assertThat(afterSleep).isNull();
	}
}
