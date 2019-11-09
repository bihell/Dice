<template>
  <div class="app-container">
    <el-table
      :data="commentDetail"
      border
      fit
      highlight-current-row
      style="width: 100%"
    >
      <el-table-column prop="id" label="id" width="60" />
      <el-table-column
        prop="name"
        label="称呼"
        show-overflow-tooltip
      />
      <el-table-column
        prop="content"
        label="内容"
        show-overflow-tooltip
      />
      <el-table-column
        prop="email"
        label="邮箱"
        show-overflow-tooltip
      />
      <el-table-column
        prop=""
        label="评论日期"
        width="160"
        show-overflow-tooltip
      >
        <template slot-scope="{row}">
          <span style="margin-left: 10px">{{ row.created }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="{row}">
          <el-button
            size="small"
            @click="handleDetail(row.id)"
          >详情
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
    <el-dialog
      :visible.sync="detailVisible"
      :modal="false"
      :fullscreen="isMobile"
      :width="dialogWidth"
      center
      append-to-body
      custom-class="comment-dialog"
    >
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :md="3">所属文章:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.title }}</el-col>
        <el-col :xs="24" :sm="12" :md="3">称呼:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.name }}</el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :md="3">邮箱:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.email }}</el-col>
        <el-col :xs="24" :sm="12" :md="3">网址:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.website }}</el-col>
      </el-row>
      <el-row :gutter="10" class="comment-row-detail">
        <el-col :span="24">
          <div v-show="hasReplay" class="markdown-body comment-replay">
            <div>
              <span class="comment-replay-name">{{ comment.replayName }}</span>
            </div>
            <div v-html="comment.replay" />
          </div>
          <div class="markdown-body" v-html="comment.content" />
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :md="3">赞:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.agree }}</el-col>
        <el-col :xs="24" :sm="12" :md="3">踩:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.disagree }}</el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :md="3">ip:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.ip }}</el-col>
        <el-col :xs="24" :sm="12" :md="3">agent:</el-col>
        <el-col :xs="24" :sm="12" :md="9">{{ comment.agent }}</el-col>
      </el-row>
    </el-dialog>

    <pagination v-show="listQuery.total>0" :total="listQuery.total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="init" />

  </div>
</template>

<script>
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import { getComments, getCommentDetail, deleteComment } from '@/api/blog'

export default {
  components: {
    Pagination
  },
  data: function() {
    return {
      commentDetail: [],
      comment: {},
      detailVisible: false,
      hasReplay: false,
      isMobile: false,
      dialogWidth: '60%',
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
    init() {
      getComments(this.listQuery).then(response => {
        this.commentDetail = response.data.list
        this.total = response.data.total
        this.pageSize = response.data.pageSize
        for (const comment of this.commentDetail) {
          comment.created = this.$dayjs(comment.created).format(
            'YYYY-MM-DD HH:mm'
          )
        }
      })
    },
    initDetail(data) {
      this.comment = data
      if (data.article) {
        this.comment.title = data.article.title
      }
      if (data.pComment) {
        this.hasReplay = true
        this.comment.replayName = data.pComment.name
        this.comment.replay = data.pComment.content
      } else {
        this.hasReplay = false
      }
    },
    handleDetail(id) {
      getCommentDetail(id).then(response => {
        this.initDetail(response.data)
        this.detailVisible = true
        if (document.body.clientWidth < 768) {
          this.isMobile = true
          this.dialogWidth = '100%'
        } else {
          this.isMobile = false
          this.dialogWidth = '60%'
        }
      })
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该评论,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      })
        .then(() => {
          this.deleteComment(id)
        })
        .catch(() => {})
    },
    deleteComment(id) {
      deleteComment(id).then(() => {
        this.$util.message.success('删除成功!')
        this.init()
      })
    }
  }
}
</script>

<style scoped>
.el-table {
  border: 1px solid #e6ebf5;
}

.comment-dialog .comment-row-detail {
  width: 90%;
  margin: 15px auto !important;
  padding: 10px;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-dialog .comment-row-detail .el-col {
  text-align: left !important;
}

.comment-dialog .comment-row-detail .comment-replay {
  padding: 10px;
  margin-bottom: 5px;
  border: 1px solid #eee;
  border-radius: 4px;
}

.comment-replay .comment-replay-name {
  text-decoration: underline;
  font-size: 16px;
}

@media screen and (min-width: 768px) {
  .comment-dialog .el-row .el-col:nth-child(odd) {
    text-align: right;
  }
}
</style>
