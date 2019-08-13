<template>
  <div>
    <v-flex xs12
      v-for="(list, index) in texts"
      :key="'text'+index"
    >
      <v-layout align-center justify-start row>
        <v-flex
          xs12
          sm2
        >
          <v-text-field
            label="x위치"
            name="position_x"
            type="number"
            outlined
            hide-details
            v-model="list.position_x"
          ></v-text-field>
        </v-flex>
        <v-flex
          xs12
          sm2
        >
          <v-text-field
            label="y위치"
            name="position_y"
            type="number"
            outlined
            hide-details
            v-model="list.position_y"
          ></v-text-field>
        </v-flex>
        <v-text-field
          label="텍스트"
          name="text"
          type="text"
          outlined
          hide-details
          v-model="list.text"
        ></v-text-field>
        <v-btn outlined fab small color="red lighten-3" @click="$emit('deleteText', index)">
          <v-icon>remove</v-icon>
        </v-btn>
      </v-layout>
    </v-flex>
  </div>
</template>

<script>
export default {
  props: ['texts']
}
</script>
