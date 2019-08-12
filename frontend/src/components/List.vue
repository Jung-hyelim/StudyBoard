<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="documents"
      :page.sync="page"
      :items-per-page="itemsPerPage"
      hide-default-footer
      :loading="loading"
      @page-count="pageCount = $event"
      disable-sort
    ></v-data-table>
    <div class="text-center pt-2">
      <v-pagination v-model="page" :length="pageCount" color="deep-purple lighten-2"></v-pagination>
    </div>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
        page: 1,
        pageCount: 0,
        itemsPerPage: 10,
        loading: false,
        headers: [
          { text: 'No.', align: 'center', value: 'id', },
          { text: '제목', align: 'left', value: 'title', },
          { text: '조회수', align: 'center', value: 'read_count', },
          { text: '태그수', align: 'center', value: 'tags_count', },
          { text: '사진수', align: 'center', value: 'photos_count', },
          { text: '생성일', align: 'center', value: 'create_date', },
          { text: '수정일', align: 'center', value: 'update_date', }
        ],
        documents: [
          {
            id: 1,
            title: "제목테스트테스트123",
            read_count: 100,
            tags_count: 1,
            photos_count: 5,
            create_date: "2019-08-01 12:36",
            update_date: "2019-08-07 12:36"
          },
        ],
      }
    },
  }
</script>