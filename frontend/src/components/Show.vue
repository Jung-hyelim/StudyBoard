<template>
  <v-container>
    <v-card elevation="12" class="pa-5">
      <v-card-text>
        <v-layout align-center justify-center row fill-height>
	        <v-text-field
	          v-model="document.id"
	          label="No."
	          type="text"
	          outlined
	          readonly
	        ></v-text-field>
        </v-layout>
        <v-layout align-center justify-center row fill-height>
	        <v-text-field
	          v-model="document.read_count"
	          label="조회수"
	          type="text"
	          outlined
	          readonly
	        ></v-text-field>
	        <v-text-field
	          v-model="document.create_date"
	          label="생성일자"
	          type="text"
	          outlined
	          readonly
	        ></v-text-field>
	        <v-text-field
	          v-model="document.update_date"
	          label="수정일자"
	          type="text"
	          outlined
	          readonly
	        ></v-text-field>
        </v-layout>
        <v-layout align-center justify-center row fill-height>
	        <v-text-field
	          v-model="document.title"
	          label="제목"
	          type="text"
	          outlined
	          readonly
	        ></v-text-field>
        </v-layout>
        <v-layout align-center justify-center row fill-height>
	        <v-textarea
	          v-model="document.content"
	          label="내용"
	          outlined
	          readonly
	        ></v-textarea>
        </v-layout>
        <v-layout class="mb-10" align-center justify-start row fill-height>
        	<v-chip class="mr-1" v-for="tag in document.tags">
        	  #{{ tag.name }}
        	</v-chip>
	    </v-layout>
        <v-layout align-center justify-start row fill-height>
          <v-card class="mr-1" flat v-for="photo in document.photos">
            <v-img
              :src="`${photo.file_path}${photo.file_name}`"
              max-width="800"
              min-width="200"
            >
	            <div v-for="text in photo.photo_texts"
	              :style="`position: absolute; top: ${text.position_x}px; left: ${text.position_y}px;`"
	            >
	            {{ text.text }}
	            </div>
            </v-img>
          </v-card>
        </v-layout>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red lighten-1" dark><v-icon>delete</v-icon>삭제</v-btn>
        <v-btn color="indigo lighten-1" dark><v-icon>edit</v-icon>수정</v-btn>
        <v-btn color="blue-grey lighten-1" dark><v-icon>list</v-icon>목록</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      document: {
        id: 1,
        create_date: "2019-08-01 12:36",
        update_date: "2019-08-07 20:43",
        read_count: 15,
        title: "제목 작성",
        content: "내용입니다.",
        tags: [
          { tag_id: 100, name: "1번태그" },
          { tag_id: 101, name: "2번" },
          { tag_id: 102, name: "귀여워" },
        ],
        photos: [
          { photo_id: 10, file_path: "http://localhost:8080/", file_name: "favicon.ico", 
      		photo_texts: [
      		  { photo_text_id: 20, position_x: 0, position_y: 1, text: "0,1에 글자 넣음" },
      		  { photo_text_id: 21, position_x: 100, position_y: 100, text: "글자 또 넣음" }
      		]
          },
          { photo_id: 11, file_path: "http://localhost:8080/", file_name: "favicon.ico" },
          { photo_id: 12, file_path: "../../../images/", file_name: "studyboard-시스템구성도.png" },
          { photo_id: 12, file_path: "/images/", file_name: "image3.jpg" },
          { photo_id: 12, file_path: "/images/", file_name: "image3.jpg" },
        ],
      }
    }),
  }
</script>
