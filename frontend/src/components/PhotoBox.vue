<template>
  <div>
    <v-flex xs12 pb-5
      v-for="(list, index) in photos"
      :key="index"
    >
      <v-layout align-center justify-start row fill-height>
        <v-text-field
          label="파일경로"
          name="file_path"
          type="text"
          outlined
          hide-details
        ></v-text-field>
        <v-text-field
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
        :texts="texts"
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
  data: () => ({
    texts: []
  }),
  methods: {
    addText (index) {
      this.texts.push({position_x: '', position_y: '', text: ''})
    },
    deleteText (index) {
      this.texts.splice(index, 1)
    }
  }
}
</script>
