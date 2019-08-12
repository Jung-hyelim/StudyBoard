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
		        <v-btn outlined fab small color="deep-purple lighten-1">
		          <v-icon>comment</v-icon>
		        </v-btn>
		        <v-btn outlined fab small color="red lighten-3">
		          <v-icon>clear</v-icon>
		        </v-btn>
	        </v-layout>
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
		        ></v-text-field>
		    </v-flex>
	            <v-text-field
		          label="텍스트"
	              name="text"
		          type="text"
		          outlined
		          hide-details
		        ></v-text-field>
		        <v-btn outlined fab small color="red lighten-3">
		          <v-icon>remove</v-icon>
		        </v-btn>
	        </v-layout>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn outlined fab small color="deep-purple lighten-1">
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
  export default {
    data: () => ({
      mode: "new",
      document: {}
    }),
  }
</script>
