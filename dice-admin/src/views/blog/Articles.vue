<template>
  <div class="app-container">
    <div class="filter-wrap mb-2">
      <el-row style="width: 100%">
        <el-col :xs="24" :sm="24" :md="12" :lg="3">
          <el-select v-model="queryParam.status" placeholder="文章状态" @change="handleFilter">
            <el-option label="不限制" value="" />
            <el-option
              v-for="status in Object.keys(postStatus)"
              :key="status"
              :label="postStatus[status].text"
              :value="postStatus[status].value"
            />
          </el-select>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="3">
          <el-select v-model="queryParam.priority" placeholder="是否置顶" style="margin-left: 10px" @change="handleFilter">
            <el-option label="不限制" value="" />
            <el-option label="普通" value="0" />
            <el-option label="置顶" value="1" />
          </el-select>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="7" style="margin-left: 10px">
          <el-input
            v-model.trim="queryParam.title"
            placeholder="搜索文章标题"
            clearable
            class="mr-3"
            @keyup.enter.native="handleFilter"
          />
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="7" style="margin-left: 10px">
          <el-input
            v-model.trim="queryParam.content"
            placeholder="搜索文章内容"
            clearable
            @keyup.enter.native="handleFilter"
          />
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="1" style="margin-left: 10px">
          <el-button type="primary" icon="el-icon-search" @click="handleFilter">
            搜索
          </el-button>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="1" style="margin-left: 40px">
          <el-button
            v-permission="'/blog/article/new'"
            type="primary"
            icon="el-icon-document-add"
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
      <el-table-column
        label="评论"
        prop="commentCount"
        show-overflow-tooltip
        width="62"
      >
        <template slot-scope="{row}">
          <div style="display:flex;justify-content: center;">
            <span class="radius-count" style="cursor: pointer;" @click="handleShowPostComments(row.id)">{{ row.commentCount }}</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="发布日期" width="150" show-overflow-tooltip align="center">
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.publish }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改日期" width="150" show-overflow-tooltip align="center">
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150" align="center">
        <template slot-scope="{row}">
          <el-button
            v-permission="'/blog/article/edit'"
            size="small"
            @click="handleEdit(row.id)"
          >编辑
          </el-button>
          <el-button
            v-permission="'/blog/article/delete'"
            size="small"
            type="danger"
            @click="handleDelete(row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="queryParam.total>0"
      :total="queryParam.total"
      :page.sync="queryParam.pageNum"
      :limit.sync="queryParam.pageSize"
      @pagination="handleFilter"
    />

    <CommentDrawer
      :visible="postCommentVisible"
    >
    </CommentDrawer>

  </div>
</template>

<script type="text/ecmascript-6">

import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import * as blogApi from '@/api/blog'
import CommentDrawer from '@/components/Components/CommentDrawer'

export default {
  components: {
    Pagination,
    CommentDrawer
  },
  data: function() {
    return {
      postStatus: blogApi.postStatus(),
      articleDetail: [],
      postCommentVisible: false,
      queryParam: {
        total: 0,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        pageNum: 1,
        status: undefined,
        title: '',
        content: '',
        priority: undefined
      }
    }
  },
  mounted() {
    this.queryParam.pageNum = Number(this.$route.query.page) || 1
    this.handleFilter()
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
    initArticleData(articles) {
      this.articleDetail = []
      for (const key in articles) {
        const data = articles[key]
        const article = {
          id: data.id,
          title: data.title,
          frontUrl: this.$serverConfig.frontUrl + 'article/' + data.id,
          publish: this.$dayjs(data.createTime).format('YYYY-MM-DD HH:mm'),
          updateTime: this.$dayjs(data.updateTime).format('YYYY-MM-DD HH:mm'),
          category: data.category || this.$static.DEFAULT_CATEGORY,
          status: data.status,
          priority: data.priority === 0 ? '普通' : '置顶',
          commentCount: data.commentCount
        }
        this.articleDetail.push(article)
      }
    },
    deleteArticle(id) {
      blogApi.deleteArticle(id).then(() => {
        this.$util.message.success('删除成功!')
        this.handleFilter()
      })
    },
    handleFilter() {
      blogApi.getArticles(this.queryParam).then(response => {
        this.initArticleData(response.data.list)
        this.queryParam.total = response.data.total
        this.queryParam.pageSize = response.data.pageSize
      })
    },
    handleShowPostComments(postId) {
      // todo
      // this.postCommentVisible = true
    }
  }
}
</script>
