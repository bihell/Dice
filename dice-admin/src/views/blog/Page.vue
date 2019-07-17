<template>
  <div class="app-container">
    <el-form ref="pageForm" :rules="rules" :model="page">
      <el-row :gutter="30">
        <el-col :xs="24" :sm="16" :md="16" :lg="16">
          <el-form-item prop="title">
            <el-input
              v-model="page.title"
              placeholder="请输入自定义页面标题"
            />
          </el-form-item>
          <el-form-item prop="content">
            <markdown-editor v-model="page.content" />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="8" :md="8" :lg="8">
          <div class="panel">
            <div class="panel-content">
              <el-form-item label="状态">
                <el-switch
                  v-model="page.status"
                  active-value="publish"
                  inactive-value="draft"
                  active-text="公开"
                  inactive-text="隐藏"
                />
              </el-form-item>
              <el-form-item>
                <el-button-group>
                  <el-button
                    type="primary"
                    size="small"
                    @click="onPublish"
                  >发布页面
                  </el-button>
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

export default {
  components: {
    MarkdownEditor
  },
  data: function() {
    return {
      submitting: false,
      page: {
        id: '',
        title: '',
        content: '',
        status: ''
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
        this.$api.blog.getPage(id).then(data => {
          this.page.id = data.id
          this.page.title = data.title
          this.page.content = data.content
          this.page.status = data.status
        })
      } else {
        this.page.id = ''
        this.page.title = ''
        this.page.content = ''
        this.page.status = this.$util.STATIC.STATUS_PUBLISH
      }
    },
    savePage(formName) {
      if (this.submitting) {
        this.$util.message.warning('请不要提交过快!')
        return
      }
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitting = true
          this.$api.blog.savePage(this.page).then(() => {
            this.$router.push('/blog/page')
            this.$util.message.success('发布自定义页面成功!')
            this.submitting = false
          })
        }
      })
    },
    onPublish() {
      this.savePage('pageForm')
    }
  }
}
</script>

<style scoped>
.el-select {
  width: 100%;
}
</style>
