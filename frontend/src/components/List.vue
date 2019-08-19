<template>
  <v-container>
    <v-data-table
      :headers="headers"
      :items="documents"
      :page.sync="page"
      :items-per-page="itemsPerPage"
      hide-default-footer
      :loading="loading"
      disable-sort
    >
      <template v-slot:item.title="props">
        <router-link :to="{name: 'show', params: {id: props.item.id}}" exact>{{ props.item.title }}</router-link>
      </template>
    </v-data-table>
    <div class="text-center pt-2">
      <v-pagination v-model="page" :length="pageCount" color="deep-purple lighten-2"></v-pagination>
    </div>
  </v-container>
</template>

<script>
import axios from 'axios'
export default {
  data: () => ({
    page: null,
    pageCount: 0,
    itemsPerPage: 10,
    loading: false,
    headers: [
      { text: 'No.', align: 'center', value: 'id' },
      { text: '제목', align: 'left', value: 'title' },
      { text: '조회수', align: 'center', value: 'read_count' },
      { text: '태그수', align: 'center', value: 'tags.length' },
      { text: '사진수', align: 'center', value: 'photos.length' },
      { text: '생성일', align: 'center', value: 'create_date' },
      { text: '수정일', align: 'center', value: 'update_date' }
    ],
    documents: []
  }),
  watch: {
    page () {
      this.getList(this.page)
    }
  },
  created: function () {
    this.page = this.$store.state.page
    this.getList(this.page)
  },
  methods: {
    getList (page) {
      this.loading = true
      axios({
        method: 'get',
        url: this.$store.state.BASE_URL,
        params: {
          page: page - 1
        }
      })
        .then((result) => {
          this.pageCount = result.data.page.totalPages
          this.documents = result.data._embedded.documentDTOList
          this.$store.commit('changePage', page)
        })
        .catch((error) => {
          console.log(error)
        })
        .then(() => {
          this.loading = false
        })
    }
  }
}
</script>
