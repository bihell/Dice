<template>
  <div class="app-container">
    <el-form ref="pageForm" :rules="rules" :model="page">
      <el-row :gutter="30">
        <el-col :xs="24" :sm="16" :md="16" :lg="20">
          <el-form-item prop="title">
            <el-input
              v-model="page.title"
              placeholder="请输入自定义页面标题"
            />
          </el-form-item>
          <el-form-item prop="content">
            <markdown-editor v-model="page.content" :on-save="onSave" />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="8" :md="8" :lg="4">
          <div class="panel">
            <div class="panel-content">
              <el-form-item>
                <el-switch
                  v-model="page.status"
                  active-value="publish"
                  inactive-value="draft"
                  active-text="公开"
                  inactive-text="隐藏"
                />
              </el-form-item>
              <el-form-item label="排序权重">
                <el-input-number
                  v-model="page.priority"
                  :min="0"
                  size="mini"
                  controls-position="right"
                ></el-input-number>
              </el-form-item>
              <el-form-item>
                <el-switch
                  v-model="page.allowComment"
                  active-text="开启评论"
                  inactive-text="关闭"
                >
                </el-switch>
              </el-form-item>
              <el-form-item>
                <el-button-group>
                  <el-row>
                    <el-button
                      type="success"
                      size="small"
                      @click="onSave"
                    >保存
                    </el-button>
                    <el-button
                      type="primary"
                      size="small"
                      @click="onPublish"
                    >发布
                    </el-button>
                  </el-row>
                </el-button-group>
              </el-form-item>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import MarkdownEditor from '../../components/MarkdownEditor/MarkdownEditor'
import { getPage, savePage } from '@/api/blog'

export default {
  components: {
    MarkdownEditor
  },
  data: function() {
    return {
      page: {
        id: '',
        title: '',
        content: '',
        status: this.$static.STATUS_PUBLISH,
        allowComment: false,
        priority: 0
      },
      rules: {
        title: [
          { required: true, message: '文章标题必须输入', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '文章内容不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.getPage()
  },
  methods: {
    getPage() {
      const id = this.$route.params.id
      if (id) {
        getPage(id).then(response => {
          this.page.id = response.data.id
          this.page.title = response.data.title
          this.page.content = response.data.content
          this.page.status = response.data.status
          this.page.allowComment = response.data.allowComment
          this.page.priority = response.data.priority
        })
      } else {
        this.page.id = ''
        this.page.title = ''
        this.page.content = ''
        this.page.status = this.$static.STATUS_PUBLISH
        this.page.priority = 0
        this.page.allowComment = false
      }
    },
    onPublish() {
      this.$refs['pageForm'].validate(valid => {
        if (valid) {
          savePage(this.page).then(() => {
            this.$router.push('/blog/page')
            this.$util.message.success('发布页面成功!')
          })
        }
      })
    },
    onSave() {
      this.$refs['pageForm'].validate(valid => {
        if (valid) {
          savePage(this.page).then(() => {
            this.$util.message.success('保存页面成功!')
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.el-select {
  width: 100%;
}
</style>
