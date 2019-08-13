<template>
  <v-container>
    <v-card elevation="12" class="pa-5">
      <v-card-text>
        <v-form>
          <v-layout v-if="mode === 'edit'" align-center justify-center row fill-height>
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
              v-model="document.title"
              label="제목"
              name="title"
              type="text"
              outlined
            ></v-text-field>
          </v-layout>
          <v-layout align-center justify-center row fill-height>
            <v-textarea
              v-model="document.content"
              label="내용"
              name="content"
              outlined
            ></v-textarea>
          </v-layout>
          <PhotoBox
            :photos="photos"
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
        <v-btn color="indigo lighten-1" dark><v-icon>done</v-icon>저장</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import PhotoBox from './PhotoBox'
export default {
  components: {
    PhotoBox
  },
  data: () => ({
    mode: 'new',
    document: {},
    photos: [
      // {file_path: '', file_name: '', texts: [{position_x: 0, position_y: 0, text: ''}]}
    ]
  }),
  methods: {
    addPhoto () {
      this.photos.push({file_path: '', file_name: '', texts: [{position_x: '', position_y: '', text: ''}]})
    },
    deletePhoto (index) {
      this.photos.splice(index, 1)
    }
  }
}
</script>
