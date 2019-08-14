<template>
  <div>
    <v-flex xs12 pb-5
      v-for="(photo, index) in photos"
      :key="'photo' + index"
    >
      <v-layout align-center justify-start row fill-height>
        <v-text-field
          v-model="photo.file_path"
          label="파일경로"
          name="file_path"
          type="text"
          outlined
          hide-details
        ></v-text-field>
        <v-text-field
          v-model="photo.file_name"
          label="파일이름"
          name="file_name"
          type="text"
          outlined
          hide-details
        ></v-text-field>
        <v-btn outlined fab small color="deep-purple lighten-1" @click="addText(index)">
          <v-icon>comment</v-icon>
        </v-btn>
        <v-btn outlined fab small color="red lighten-3" @click="$emit('deletePhoto', index)">
          <v-icon>clear</v-icon>
        </v-btn>
      </v-layout>
      <PhotoTextBox
        :texts="photo.photo_texts"
        :photo_index="index"
        @deleteText="deleteText"
      />
    </v-flex>
  </div>
</template>

<script>
import PhotoTextBox from './PhotoTextBox'
export default {
  components: {
    PhotoTextBox
  },
  props: ['photos'],
  methods: {
    addText (index) {
      this.photos[index].photo_texts.push({position_x: '', position_y: '', text: ''})
    },
    deleteText (photoIndex, textIndex) {
      this.photos[photoIndex].photo_texts.splice(textIndex, 1)
    }
  }
}
</script>
