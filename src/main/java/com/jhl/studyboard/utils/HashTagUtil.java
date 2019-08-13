package com.jhl.studyboard.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jhl.studyboard.dto.DocumentDTO;
import com.jhl.studyboard.dto.TagDTO;

public class HashTagUtil {

	public static void extractHashTag(DocumentDTO dto) {
		// 내용에서 해시태그 추출 후 태그 리스트를 dto에 담는다.
		Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣ㄱ-ㅎㅏ-ㅣ_]*)");
		Matcher m = p.matcher(dto.getContent());
		String extractText = null;
		
		while(m.find()) {
			extractText = m.group(1);
			if(extractText != null && !extractText.equals("")) {
				dto.addTag(new TagDTO(extractText));
			}
		}
	}
	
}
