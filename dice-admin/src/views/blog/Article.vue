<template>
  <div class="app-container">
    <el-form
      ref="articleForm"
      label-position="top"
      :rules="rules"
      :model="article"
    >
      <el-row :gutter="30">
        <el-col :xs="24" :sm="16" :md="19" :lg="20">
          <el-form-item prop="title">
            <el-input
              v-model="article.title"
              placeholder="请输入文章标题"
            />
          </el-form-item>
          <el-form-item prop="content">
            <markdown-editor
              v-model="article.content"
              :on-save="onSave"
            />
            <!-- 键修饰符，键别名 -->
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="8" :md="5" :lg="4">
          <div class="panel">
            <div class="panel-content">
              <el-form-item label="标签">
                <el-select
                  v-model="selectTags"
                  multiple
                  filterable
                  placeholder="请选择文章标签"
                >
                  <el-option
                    v-for="tag in tags"
                    :key="tag.value"
                    :label="tag.label"
                    :value="tag.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="分类">
                <el-select
                  v-model="article.category"
                  filterable
                  placeholder="请选择文章分类"
                >
                  <el-option
                    v-for="category in categories"
                    :key="category.value"
                    :label="category.label"
                    :value="category.value"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="状态">
                <el-switch
                  v-model="article.status"
                  active-value="publish"
                  inactive-value="draft"
                  active-text="公开"
                  inactive-text="隐藏"
                />
              </el-form-item>
              <el-form-item label="创建日期">
                <el-date-picker
                  v-model="article.created"
                  type="datetime"
                  placeholder="创建日期"
                  size="small"
                  :editable="flagFalse"
                  value-format="timestamp"
                />
              </el-form-item>
              <el-form-item label="修改日期">
                <el-date-picker
                  v-model="article.modified"
                  type="datetime"
                  placeholder="修改日期"
                  size="small"
                  :editable="flagFalse"
                  value-format="timestamp"
                />
              </el-form-item>
              <el-form-item>
                <el-button-group>
                  <el-row>
                    <el-button
                      type="primary"
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
                    <el-button
                      v-if="article.id !== ''"
                      type="primary"
                      size="small"
                    >
                      <a
                        :href="
                          this.$serverConfig.frontUrl +
                            'article/' +
                            article.id "
                        target="_blank"
                        style="color: #FFFFFF;"
                      >查看</a>
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

export default {
  components: {
    MarkdownEditor
  },
  data: function() {
    return {
      submitting: false,
      article: {
        id: '',
        title: '',
        tags: '',
        category: '',
        content: '',
        status: '',
        created: '',
        modified: ''
      },
      rules: {
        title: [
          { required: true, message: '文章标题必须输入', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '文章内容不能为空', trigger: 'blur' }
        ]
      },
      selectTags: [],
      tags: [],
      categories: [],
      flagFalse: false
    }
  },
  watch: {
    // 监听route刷新绑定的article数据
    $route() {
      this.getArticle()
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    getArticle() {
      const id = this.$route.params.id
      // 如果有id则表示编辑文章,获取文章信息
      if (id) {
        this.$api.blog.getArticle(id).then(data => {
          this.initArticle(data)
        })
      } else {
        // 如果没有id则表示新增文章,不用清空文章信息
        const data = {
          id: '',
          title: '',
          tags: '',
          category: '',
          content: '',
          status: this.$static.STATUS_PUBLISH,
          created: Date.now(),
          modified: Date.now()
        }
        this.initArticle(data)
      }
    },
    initArticle(data) {
      this.article.id = data.id
      this.article.title = data.title
      this.article.tags = data.tags
      this.article.category = data.category
      this.article.content = data.content
      this.article.status = data.status
      this.article.created = new Date(data.created).getTime()
      this.article.modified = Date.now()
      this.selectTags = this.$util.stringToTags(data.tags)
    },
    getTags() {
      this.$api.blog.getAllTags().then(data => {
        for (const key in data) {
          const tag = {
            value: data[key].name,
            label: data[key].name
          }
          this.tags.push(tag)
        }
      })
    },
    getCategories() {
      this.$api.blog.getAllCategories().then(data => {
        for (const key in data) {
          const category = {
            value: data[key].name,
            label: data[key].name
          }
          this.categories.push(category)
        }
      })
    },
    submitArticle(formName, success) {
      if (this.submitting) {
        this.$util.message.warning('请不要提交过快!')
        return
      }
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitting = true
          const params = this.article
          params.tags = this.$util.tagsToString(this.selectTags)
          this.$api.blog.saveArticle(params).then(data => {
            success(data)
            this.submitting = false
          })
        }
      })
    },
    onPublish() {
      const _this = this
      this.submitArticle('articleForm', function() {
        _this.$util.message.success('发布文章成功!')
        _this.$router.push('/blog/article')
      })
    },
    onSave() {
      const _this = this
      this.submitArticle('articleForm', function(data) {
        _this.$util.message.success('保存文章成功!')
        _this.$route.params.id = data
        _this.getArticle()
      })
    },
    init() {
      this.getArticle()
      this.getTags()
      this.getCategories()
    }
  }
}
</script>

<style scoped>
.el-select {
  width: 100%;
}

a {
  text-decoration: none;
}
</style>
