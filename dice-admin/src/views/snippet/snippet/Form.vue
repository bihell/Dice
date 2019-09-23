<template>
  <card id="snippet-form" class="animated">
    <header slot="card-header" class="card-header" style="height: 54px">
      <p class="card-header-title no-wrap" v-html="title"></p>
    </header>

    <div slot="card-content" class="card-content">
      <form action="/" @submit="submitAction">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">标题 *</label>
          </div>
          <div class="field-body">
            <div class="field">
              <div class="control">
                <input id="title" v-model="editSnippetTitle" name="title" class="input" type="text">
              </div>
            </div>
          </div>
        </div>

        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">描述</label>
          </div>
          <div class="field-body">
            <div class="field">
              <div class="control">
                <input v-model="editSnippetDescription" name="description" class="input" />
              </div>
            </div>
          </div>
        </div>

        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">标签 *</label>
          </div>
          <div class="field-body">
            <div class="field">
              <div class="control">
                <input id="snippet-labels" v-model="editSnippetLabel" class="input" type="text">
              </div>
            </div>
          </div>
        </div>

        <div class="field">
          <div v-for="(snippetFile, index) in snippetFiles" :key="index" class="field">
            <snippet-file-form :index="index" :title="snippetFile.title || 'New snippet file'" />
          </div>
        </div>

        <div class="field is-horizontal form-footer" style="justify-content: space-between">
          <div class="control">
            <el-button
              size="small"
              type="primary"
              @click="addFile($store.state.snippets.indexOf(snippet), $event)"
            >
              添加文件
            </el-button>
          </div>

          <div class="field is-grouped">
            <div class="control">
              <el-button type="primary" @click="submitAction">{{ action | capitalize }} </el-button>
            </div>
            <div class="control">
              <el-button size="small" @click="cancelAction">取消</el-button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </card>

</template>

<script>
import Card from '../Card.vue'
import SnippetFileForm from '../snippet_file/Form.vue'
import 'codemirror/addon/display/placeholder'
import '../../../utils/codemirror_modes'
import Filters from '../mixins/filters'
import { saveSnippet, getSnippetByMeta, getSnippetById } from '@/api/snippet'
import { getAllTags } from '@/api/snippet'

export default {

  components: { Card, SnippetFileForm },

  mixins: [Filters],

  props: {
    title: { type: String, default: undefined },
    action: { type: String, default: undefined }
  },

  computed: {
    editSnippetTitle: {
      get() {
        return this.$store.state.labelSnippets.edit.title
      },

      set(value) {
        this.$store.commit('setLabelSnippetsEditTitle', value)
      }
    },

    editSnippetDescription: {
      get() {
        return this.$store.state.labelSnippets.edit.description
      },

      set(value) {
        this.$store.commit('setLabelSnippetsEditDescription', value)
      }
    },

    editSnippetLabel: {
      get() {
        return this.$store.state.labelSnippets.edit.label
      },

      set(value) {
        this.$store.commit('setLabelSnippetEditLabel', value)
      }
    },

    snippet() {
      return this.$store.state.labelSnippets.edit
    },

    snippetFiles() {
      return this.$store.state.labelSnippets.edit.snippetFiles
    }
  },

  mounted() {
    // set focus on title textfield
    setTimeout(() => {
      this.$el.querySelector('input[type=text]').focus()
    }, 100)
  },

  methods: {
    addFile(snippetIndex, e) {
      e.preventDefault()
      this.$store.commit('addSnippetFile', snippetIndex)
      this.$store.commit('setScrollToLatestFileFlag', true)
    },

    submitAction(e) {
      e.preventDefault()
      // TODO: 这里代码有重复有时间要整合
      const snippetFilesAttributes = []
      // 因为用了 element UI 的组件，这里需要先把 form 过滤出来
      const content = this.$children[0].$children.filter(children => children.$el.tagName === 'FORM')
      this.$store.state.labelSnippets.edit.snippetFiles.forEach((snippetFile, index) => {
        snippetFilesAttributes.push({
          id: snippetFile.id || null,
          title: snippetFile.title,
          content: content[index].editor.getValue(),
          language: snippetFile.language,
          tabs: snippetFile.tabs
        })
        if (snippetFile.hasOwnProperty('_destroy')) {
          snippetFilesAttributes[snippetFilesAttributes.length - 1]['destroy'] = true
        }
      })
      const data = {
        id: this.snippet.id || null,
        title: this.$store.state.labelSnippets.edit.title,
        description: this.$store.state.labelSnippets.edit.description,
        snippetFiles: snippetFilesAttributes,
        label: this.$store.state.labelSnippets.edit.label
      }

      async function saveData(store, message, action) {
        await saveSnippet(data).then(response => {
          if (response.success) {
            message.success('代码段已' + action)
            // 代码段创建或更新后直接展示
            getSnippetById(response.data).then(response => {
              store.commit('setActiveLabelSnippet', response.data)
              store.commit('setSnippetMode', 'show')
            })
          } else {
            message.error('代码段' + action + '失败')
          }
        })
        // 更新标签列表
        getAllTags(store.state.querySnippet).then(response => {
          store.commit('setLabels', response.data)
        })
        // 更新代码段列表
        getSnippetByMeta(store.state.labels.active).then(response => {
          store.commit('setLabelSnippets', response.data)
        })
      }
      saveData(this.$store, this.$util.message, this.action)
    },
    cancelAction(e) {
      e.preventDefault()
      if (this.$store.state.snippets.mode === 'create') {
        this.$store.commit('setSnippetMode', null)
      } else {
        this.$store.commit('setSnippetMode', 'show')
      }
    }
  }
}
</script>
<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";
  .card-content {
    padding: 1rem 0.75em;}
</style>
