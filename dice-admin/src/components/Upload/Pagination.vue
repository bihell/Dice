<template>
  <div class="admin-page">
    <el-pagination
      background
      layout="prev, pager, next"
      :current-page.sync="currentPage"
      :page-size="pageSize"
      :total="total"
      @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    pageSize: {
      type: Number,
      default: 0
    },
    total: {
      type: Number,
      default: 1
    }
  },
  data() {
    return { currentPage: 1 }
  },
  created() {},
  methods: {
    changePage() {
      this.$emit('changePage', this.currentPage)
    }
  }
}
</script>

<style scoped>
.admin-page {
  margin-top: 30px;
  text-align: center;
}
</style>
