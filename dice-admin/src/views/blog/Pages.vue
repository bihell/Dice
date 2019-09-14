<template>
  <div class="app-container">
    <div class="tool-container" style="justify-content: flex-end">
      <el-button
        icon="el-icon-edit"
        style="margin-left: 16px;"
        @click="handleNew"
      >新页面
      </el-button>
    </div>

    <el-table :data="pageDatas" border stripe style="width: 100%">
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column
        prop="title"
        label="标题"
        min-width="150"
        show-overflow-tooltip
      />
      <el-table-column label="发布日期" width="160" show-overflow-tooltip>
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span style="margin-left: 10px">{{ scope.row.publish }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期" width="160" show-overflow-tooltip>
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span style="margin-left: 10px">{{ scope.row.modified }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="100"
        show-overflow-tooltip
      />
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <router-link :to="'/blog/page/edit/'+scope.row.id" class="link-type">
            <el-button size="small">
              编辑
            </el-button>
          </router-link>
          <el-button
            size="small"
            @click="handleDelete(scope.row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="admin-page">
      <el-pagination
        layout="total,prev, pager, next"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        :total="total"
        @current-change="init"
      />
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data: function() {
    return {
      pageDatas: [],
      total: 0,
      pageSize: 10,
      currentPage: 1
    }
  },
  mounted() {
    this.currentPage = Number(this.$route.query.page) || 1
    this.init()
  },
  methods: {
    handleNew() {
      this.$router.push('/blog/page/create')
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该自定义页面, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        this.deletePage(id)
      }).catch(() => {
      })
    },
    initPageDatas(pages) {
      this.pageDatas = []
      for (const key in pages) {
        const data = pages[key]
        const page = {
          id: data.id,
          title: data.title,
          publish: this.$dayjs(data.created).format('YYYY-MM-DD HH:mm'),
          modified: this.$dayjs(data.modified).format('YYYY-MM-DD HH:mm'),
          status: this.$static.STATUS_PUBLISH === data.status ? '公开' : '隐藏'
        }
        this.pageDatas.push(page)
      }
    },
    deletePage(id) {
      this.$api.blog.deletePage(id).then(() => {
        this.$util.message.success('删除成功!')
        this.init()
      })
    },
    init() {
      this.$api.blog.getPages(this.currentPage).then(data => {
        this.initPageDatas(data.list)
        this.total = data.total
        this.pageSize = data.pageSize
      })
    }
  }
}
</script>

<style>
.el-table ::-webkit-scrollbar {
  display: block;
  height: 10px;
}

.el-table ::-webkit-scrollbar-thumb {
  background-color: #324157;
}

.el-table ::-webkit-scrollbar-thumb:active {
  background-color: #00aff0;
}

@media screen and (min-width: 600px) {
  .el-table ::-webkit-scrollbar {
    display: block;
    height: 10px;
  }
}

@media screen and (max-width: 600px) {
  .el-table ::-webkit-scrollbar {
    display: none;
  }
}
</style>

<style scoped>
.tool-container {
  padding: 16px;
  display: flex;
  justify-content: space-between;
  background: #fff;
}

.el-table {
  border: 1px solid #e6ebf5;
}

.admin-page {
  margin-top: 30px;
  text-align: center;
}
</style>
