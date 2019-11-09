<template>
  <div class="app-container">
    <div class="tool-container">
      <el-row style="width: 100%">
        <el-col :xs="24" :sm="24" :md="12" :lg="4">
          <span>
            状态：
          </span>
          <el-radio-group v-model="listQuery.status" size="mini" @change="init">
            <el-radio-button label="">全部</el-radio-button>
            <el-radio-button
              :label="this.$static.STATUS_PUBLISH"
            >公开
            </el-radio-button>
            <el-radio-button
              :label="this.$static.STATUS_DRAFT"
            >隐藏
            </el-radio-button>
          </el-radio-group>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="7">
          <span>
            类型：
          </span>
          <el-radio-group
            v-model="listQuery.priority"
            size="mini"
            @change="init"
          >
            <el-radio-button label="">全部</el-radio-button>
            <el-radio-button label="0">普通
            </el-radio-button>
            <el-radio-button label="1">置顶
            </el-radio-button>
          </el-radio-group>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="5">
          <el-input
            v-model.trim="listQuery.title"
            size="small"
            placeholder="搜索文章标题"
            clearable
            @keyup.enter.native="init"
          />
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="5" style="margin-left: 10px">
          <el-input
            v-model.trim="listQuery.content"
            size="small"
            placeholder="搜索文章内容"
            clearable
            @keyup.enter.native="init"
          />
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="1" style="margin-left: 10px">
          <el-button type="primary" icon="el-icon-search" size="small" @click="init">
            搜索
          </el-button>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="1" style="margin-left: 20px">
          <el-button
            type="primary"
            icon="el-icon-document-add"
            size="small"
            @click="handleNew"
          >新文章
          </el-button>
        </el-col>
      </el-row>

    </div>

    <el-table
      :data="articleDetail"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID" width="60" align="center" />
      <el-table-column prop="title" label="标题" show-overflow-tooltip>
        <template slot-scope="{row}">
          <el-link
            :href="row.frontUrl"
            target="_blank"
            type="primary"
          >{{ row.title }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column prop="category" label="分类" width="150" align="center" show-overflow-tooltip>
        <template slot-scope="{row}">
          <el-tag effect="plain">{{ row.category }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        width="68"
        show-overflow-tooltip
      >
        <template slot-scope="{row}">
          <el-tag
            :type="row.status === '公开' ? 'success' : 'warning'"
            disable-transitions
            effect="plain"
          >{{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="priority"
        label="类型"
        width="68"
        show-overflow-tooltip
      >
        <template slot-scope="{row}">
          <el-tag
            :type="row.priority === '置顶' ? 'warning' : ''"
            effect="plain"
            disable-transitions
          >{{ row.priority }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="发布日期" width="150" show-overflow-tooltip align="center">
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.publish }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期" width="150" show-overflow-tooltip align="center">
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.modified }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="{row}">
          <el-button
            size="small"
            @click="handleEdit(row.id)"
          >编辑
          </el-button>
          <el-button
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
import { getArticles, deleteArticle } from '@/api/blog'

export default {
  components: {
    Pagination
  },
  data: function() {
    return {
      articleDetail: [],
      listQuery: {
        total: 0,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        pageNum: 1,
        status: '',
        title: '',
        content: '',
        priority: ''
      }
    }
  },
  mounted() {
    this.listQuery.pageNum = Number(this.$route.query.page) || 1
    this.init()
  },
  methods: {
    handleNew() {
      this.$router.push('/blog/article/create')
    },
    handleEdit(id) {
      this.$router.push('/blog/article/edit/' + id)
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      }).then(() => {
        this.deleteArticle(id)
      }).catch(() => {
      })
    },
    initArticleDatas(articles) {
      this.articleDetail = []
      for (const key in articles) {
        const data = articles[key]
        const article = {
          id: data.id,
          title: data.title,
          frontUrl: this.$serverConfig.frontUrl + 'article/' + data.id,
          publish: this.$dayjs(data.created).format('YYYY-MM-DD HH:mm'),
          modified: this.$dayjs(data.modified).format('YYYY-MM-DD HH:mm'),
          category: data.category || this.$static.DEFAULT_CATEGORY,
          status: this.$static.STATUS_PUBLISH === data.status ? '公开' : '隐藏',
          priority: data.priority === 0 ? '普通' : '置顶'
        }
        this.articleDetail.push(article)
      }
    },
    deleteArticle(id) {
      deleteArticle(id).then(() => {
        this.$util.message.success('删除成功!')
        this.init()
      })
    },
    init() {
      getArticles(this.listQuery).then(response => {
        this.initArticleDatas(response.data.list)
        this.listQuery.total = response.data.total
        this.listQuery.pageSize = response.data.pageSize
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
  padding-bottom: 10px;
  display: flex;
  justify-content: space-between;
  background: #fff;
}
</style>
