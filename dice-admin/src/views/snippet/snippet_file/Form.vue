<template>

  <form v-if="!snippetFile._destroy" action="/" @submit="submitAction">
    <card :id="`snippet-file-form-${index}`">
      <header slot="card-header" class="card-header" style="justify-content: space-between">
        <div style="display: flex; align-items: center;">
          <collapsible-controls
            :id="`#snippet-file-form-${index}`"
            :index="index"
          />
          <div class="field has-addons is-marginless">
            <div class="control is-expanded">
              <input
                :id="`title-${index}`"
                v-model="editFileTitle"
                class="input"
                type="text"
                placeholder="File title"
              >
            </div>
            <div class="control">
              <a
                :id="`snippet-delete-${index}`"
                class="button is-danger is-outlined"
                :disabled="this.snippet.snippetFiles.length === 1"
                @click="destroyFile(index, $event)"
              >
                <i class="el-icon-delete"></i>
              </a>
            </div>
          </div>
        </div>

        <div class="card-header-icon">
          <div class="field is-grouped is-grouped-right is-marginless">
            <div class="control">
              <div class="select">
                <select v-model="editFileLanguage">
                  <option v-for="(v, k) in languageOptions" :value="k">{{ v }}</option>
                </select>
              </div>
            </div>
            <div class="control">
              <div class="select">
                <select v-model="editFileTabs">
                  <option v-for="(v, k) in tabOptions" :value="k">{{ v }}</option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </header>

      <div :id="`card-content-${index}`" slot="card-content" class="card-content is-paddingless shadow-light">
        <div class="field">
          <div class="editor" :style="{maxHeight: editorHeight}" style="border: none;">
            <textarea class="file textarea" placeholder="Paste a snippet of code">{{ snippetFile.content }}</textarea>
          </div>
        </div>
      </div>
    </card>
  </form>

</template>

<script>
// import Backend from '../../api/backend'
import Card from '../Card.vue'
import CodeMirror from 'codemirror'
import 'codemirror/addon/display/placeholder'
import CollapsibleControls from '../CollapsibleControls.vue'
import '../../../utils/codemirror_modes'
import Editor from '../mixins/editor'
import Filters from '../mixins/filters'
import { processEditorMode } from '../../../utils/editor_helper'
import 'codemirror/lib/codemirror.css'

export default {

  components: { Card, CollapsibleControls },

  mixins: [Editor, Filters],
  props: ['title', 'action', 'index'],

  data() {
    return {
      editor: null
    }
  },

  computed: {
    editFileTitle: {
      get() {
        return this.snippetFile.title
      },

      set(value) {
        // const index = this.index
        // this.$store.commit('setLabelSnippetEditFileTitle', { index, value })
      }
    },

    editFileLanguage: {
      get() {
        return this.snippetFile.language
      },

      set(value) {
        // const index = this.index
        // this.$store.commit('setLabelSnippetEditFileLanguage', { index, value })
        this.editor.setOption('mode', processEditorMode(value))
      }
    },

    editFileTabs: {
      get() {
        return this.snippetFile.tabs
      },

      set(value) {
        // const index = this.index
        // this.$store.commit('setLabelSnippetEditFileTabs', { index, value })
        this.editor.setOption('tabSize', value)
      }
    },

    snippet() {
      // return this.$store.state.labelSnippets.edit
      return { description: 'test description',
        id: 11,
        label: 'test lable',
        snippetFiles: [{ content: 'test code 2',
          created_at: '2019-08-30T15:02:46.606Z',
          id: 20,
          language: 'automatically',
          snippet_id: 11,
          tabs: 4,
          title: 'test file 2',
          updated_at: '2019-08-30T15:02:46.606Z' }, { content: '# markdown 测试↵##  markdown 测试标题↵↵```↵markdown hello↵```',
          created_at: '2019-08-30T15:02:46.603Z',
          id: 19,
          language: 'markdown',
          snippet_id: 11,
          tabs: 4,
          title: 'test title 1',
          updated_at: '2019-09-06T05:54:58.426Z' }],
        title: 'test title' }
    },

    snippetFile() {
      return this.snippet.snippetFiles[this.index]
    }
  },

  mounted() {
    // init codemirror
    this.editor = CodeMirror.fromTextArea(this.$el.querySelector('.file'), {
      lineNumbers: true,
      mode: processEditorMode(this.snippetFile.language),
      tabSize: this.snippetFile.language.tabs
    })

    //
    // set focus on title textfield
    setTimeout(() => {
      this.$el.querySelector('input[type=text]').focus()
    }, 100)

    // TODO: replace scrollIntoView to something where smooth is compatible with all browsers
    // if (this.$store.state.flags.scrollToLatestFile) {
    //   this.$store.commit('setScrollToLatestFileFlag', false)
    //   setTimeout(() => {
    //     if (this.index) {
    //       const el = document.getElementById(`snippet-file-form-${this.index}`)
    //       if (el) {
    //         el.scrollIntoView({ behavior: 'smooth' })
    //       }
    //     }
    //   }, 200)
    // }
  },

  methods: {
    destroyFile(snippetIndex, e) {
      e.preventDefault()

      // if (this.snippet.snippetFiles.length > 1) {
      //   this.$store.commit('removeSnippetFile', snippetIndex)
      //   this.$forceUpdate()
      // }
    },

    submitAction(e) {
      e.preventDefault()
      // Backend.snippet[this.action](this)
    },

    cancelAction(e) {
      e.preventDefault()
      // if (this.$store.state.snippets.mode === 'create') {
      //   this.$store.commit('setSnippetMode', null)
      // } else {
      //   this.$store.commit('setSnippetMode', 'show')
      // }
    }
  }
}
</script>
<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";
  .shadow-light {
    box-shadow: 0 0 1px 1px rgba($black, 0.1);
  }

  .card-content {
    padding: 1rem 0.75em;

    &.collapsed {
      // to make collapsed elements compatible with clipboard.js
      // https://github.com/zenorocha/clipboard.js/issues/560#issuecomment-408606225
      opacity: 0;
      position: absolute;
      z-index: -10;

      max-height: 0;
      overflow: hidden;
    }
  }
</style>
