<template>
  <v-container>
    <v-card elevation="12" class="pa-5">
      <v-card-title>
        {{ mode }}
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-layout v-if="mode === 'edit'" align-center justify-center row fill-height>
            <v-text-field
              v-model="document.id"
              label="No."
              name="id"
              type="text"
              outlined
              readonly
            ></v-text-field>
          </v-layout>
          <v-layout align-center justify-center row fill-height>
            <v-text-field
              v-model="document.title"
              label="제목"
              name="title"
              type="text"
              outlined
              required
            ></v-text-field>
          </v-layout>
          <v-layout align-center justify-center row fill-height>
            <v-textarea
              v-model="document.content"
              label="내용"
              name="content"
              outlined
              required
            ></v-textarea>
          </v-layout>
          <PhotoBox
            :photos="document.photos"
            @deletePhoto="deletePhoto"
          />
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn outlined fab small color="deep-purple lighten-1" @click="addPhoto">
          <v-icon>add_a_photo</v-icon>
        </v-btn>
      </v-card-actions>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="indigo lighten-1"
          dark
          @click="saveDocument"
        ><v-icon>done</v-icon>저장</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import axios from 'axios'
import PhotoBox from './PhotoBox'
export default {
  components: {
    PhotoBox
  },
  data: () => ({
    mode: 'edit',
    document: {
      photos: []
    }
  }),
  created: function () {
    const path = this.$route.path
    if (path.indexOf('new') !== -1) {
      this.mode = 'new'
    } else {
      this.mode = 'edit'

      if (this.$route.params.document !== undefined) {
        this.document = this.$route.params.document
      } else {
        this.getDocument(this.$route.params.id)
      }
    }
  },
  methods: {
    addPhoto () {
      this.document.photos.push({file_path: '', file_name: '', photo_texts: []})
    },
    deletePhoto (index) {
      this.document.photos.splice(index, 1)
    },
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
    saveDocument () {
      var method = 'post'
      var url = 'http://localhost:8080/api/document'

      if (this.mode === 'edit') {
        method = 'put'
        url += '/' + this.document.id
      }

      axios({
        method: method,
        url: url,
        responseType: 'json'
      })
        .then((result) => {
          alert('저장되었습니다.')
          this.$router.push({ name: 'show', params: { id: result.id } })
        })
        .catch((error) => {
          console.log(error)
          alert('저장에 실패했습니다.')
        })
        .then(() => {
        })
    }
  }
}
</script>
