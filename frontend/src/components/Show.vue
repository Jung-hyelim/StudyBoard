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
          <v-chip
            class="mr-1"
            v-for="(tag, index) in document.tags"
            :key="'tag' + index"
          >#{{ tag.name }}</v-chip>
      </v-layout>
        <v-layout align-center justify-start row fill-height>
          <v-card
            class="mr-1"
            flat
            v-for="(photo, index) in document.photos"
            :key="'photo' + index"
          >
            <v-img
              :src="`${photo.file_path}${photo.file_name}`"
              max-width="800"
              min-width="200"
            >
              <div
                v-for="(text, index) in photo.photo_texts"
                :key="'text' + index"
                :style="`position: absolute; top: ${text.position_x}px; left: ${text.position_y}px;`"
              >{{ text.text }}</div>
            </v-img>
          </v-card>
        </v-layout>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="red lighten-1"
          dark
          @click="deleteDocument"
        ><v-icon>delete</v-icon>삭제</v-btn>
        <v-btn
          color="indigo lighten-1"
          dark
          @click="editDocument"
        ><v-icon>edit</v-icon>수정</v-btn>
        <v-btn
          color="blue-grey lighten-1"
          dark
          router :to="{ name: 'home' }" exact
        ><v-icon>list</v-icon>목록</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({
    document: {}
  }),
  created: function () {
    this.getDocument(this.$route.params.id)
  },
  methods: {
    getDocument (id) {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/document/' + id,
        responseType: 'json'
      })
        .then((result) => {
          this.document = result.data
        })
        .catch((error) => {
          console.log(error)
          alert('비정상 접근입니다.')
          this.$router.push({ name: 'home' })
        })
        .then(() => {
        })
    },
    deleteDocument () {
      if (confirm(this.document.title + ' 을 삭제하시겠습니까?')) {
        axios({
          method: 'delete',
          url: 'http://localhost:8080/api/document/' + this.document.id,
          responseType: 'json'
        })
          .then((result) => {
            alert('삭제되었습니다.')
          })
          .catch((error) => {
            console.log(error)
            alert('문제가 발생하여 삭제되지 않았습니다.')
          })
          .then(() => {
            this.$router.push({ name: 'home' })
          })
      }
    },
    editDocument () {
      this.$router.push({
        name: 'edit',
        params: {
          id: this.document.id,
          document: this.document
        }
      })
    }
  }
}
</script>
