<template>
  <div class="app-container">
    <div class="filter-wrap mb-2" style="justify-content: flex-end">
      <el-button
        v-permission="'/blog/page/new'"
        type="primary"
        icon="el-icon-document-add"
        size="small"
        style="margin-left: 16px;"
        @click="handleNew"
      >新页面
      </el-button>
    </div>

    <el-table
      :data="pageDetail"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column
        prop="title"
        label="标题"
        min-width="150"
        show-overflow-tooltip
      />
      <el-table-column label="发布日期" width="160" show-overflow-tooltip>
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.publish }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期" width="160" show-overflow-tooltip>
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="79"
        show-overflow-tooltip
      >
        <template slot-scope="{row}">
          <el-tag
            :type="postStatus[row.status].color"
            disable-transitions
            effect="plain"
          >{{ postStatus[row.status].text }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="{row}">
          <el-button
            v-permission="'/blog/page/update'"
            size="small"
            @click="handleEdit(row.id)"
          >编辑
          </el-button>
          <el-button
            v-permission="'/blog/page/delete'"
            size="small"
            type="danger"
            @click="handleDelete(row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="listQuery.total>0" :total="listQuery.total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="init" />

  </div>
</template>

<script type="text/ecmascript-6">
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import * as blogApi from '@/api/blog'

export default {
  components: {
    Pagination
  },
  data: function() {
    return {
      postStatus: blogApi.postStatus(),
      pageDetail: [],
      listQuery: {
        total: 0,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        pageNum: 1
      }
    }
  },
  mounted() {
    this.listQuery.pageNum = Number(this.$route.query.page) || 1
    this.init()
  },
  methods: {
    handleNew() {
      this.$router.push('/blog/page/create')
    },
    handleEdit(id) {
      this.$router.push('/blog/page/edit/' + id)
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
      this.pageDetail = []
      for (const key in pages) {
        const data = pages[key]
        const page = {
          id: data.id,
          title: data.title,
          publish: this.$dayjs(data.createTime).format('YYYY-MM-DD HH:mm'),
          updateTime: this.$dayjs(data.updateTime).format('YYYY-MM-DD HH:mm'),
          status: data.status
        }
        this.pageDetail.push(page)
      }
    },
    deletePage(id) {
      blogApi.deletePage(id).then(() => {
        this.$util.message.success('删除成功!')
        this.init()
      })
    },
    init() {
      blogApi.getPages(this.currentPage, this.limit).then(response => {
        this.initPageDatas(response.data.list)
        this.listQuery.total = response.data.total
        this.listQuery.pageSize = response.data.pageSize
      })
    }
  }
}
</script>
