<template>
  <div>
    <v-flex xs12
      v-for="(text, index) in texts"
      :key="'text' + index"
    >
      <v-layout align-center justify-start row>
        <v-flex
          xs12
          sm2
        >
          <v-text-field
            v-model="text.position_x"
            label="x위치"
            name="position_x"
            type="number"
            outlined
            required
            :rules="[$store.state.rules.required, $store.state.rules.largerThanZero]"
            min=0
          ></v-text-field>
        </v-flex>
        <v-flex
          xs12
          sm2
        >
          <v-text-field
            v-model="text.position_y"
            label="y위치"
            name="position_y"
            type="number"
            outlined
            required
            :rules="[$store.state.rules.required, $store.state.rules.largerThanZero]"
            min=0
          ></v-text-field>
        </v-flex>
        <v-text-field
          v-model="text.text"
          label="텍스트"
          name="text"
          type="text"
          outlined
          required
          :rules="[$store.state.rules.required]"
        ></v-text-field>
        <v-btn outlined fab small color="red lighten-3" @click="$emit('deleteText', photo_index, index)">
          <v-icon>remove</v-icon>
        </v-btn>
      </v-layout>
    </v-flex>
  </div>
</template>

<script>
export default {
  props: ['texts', 'photo_index']
}
</script>
