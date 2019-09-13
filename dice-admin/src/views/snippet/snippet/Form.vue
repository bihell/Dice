<template>
  <card id="snippet-form" class="animated">
    <header slot="card-header" class="card-header">
      <p class="card-header-title no-wrap" v-html="title"></p>
    </header>

    <div slot="card-content" class="card-content">
      <form action="/" @submit="submitAction">
        <div class="field is-horizontal">
          <div class="field-label is-normal">
            <label class="label">Title *</label>
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
            <label class="label">Description</label>
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
            <label class="label">Label</label>
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
          <div v-for="(snippetFile, index) in snippetFiles" class="field">
            <snippet-file-form :index="index" :title="snippetFile.title || 'New snippet file'" />
          </div>
        </div>

        <div class="field is-horizontal form-footer" style="justify-content: space-between">
          <div class="control">
            <button
              class="button is-primary is-outlined"
              type="button"
              @click="addFile($store.state.snippets.indexOf(snippet), $event)"
            >
              添加文件
            </button>
          </div>

          <div class="field is-grouped">
            <div class="control">
              <button class="button is-primary" type="submit" @click="submitAction">{{ action | capitalize }}</button>
            </div>
            <div class="control">
              <button class="button is-text" type="button" @click="cancelAction">取消</button>
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
import { saveSnippet } from '@/api/snippet'

export default {

  components: { Card, SnippetFileForm },

  mixins: [Filters],
  props: ['title', 'action'],

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

      // TODO: move to action
      this.$store.commit('addSnippetFile', snippetIndex)
      this.$store.commit('setScrollToLatestFileFlag', true)
    },

    submitAction(e) {
      e.preventDefault()
      const snippetFilesAttributes = []
      this.$store.state.labelSnippets.edit.snippetFiles.forEach((snippetFile, index) => {
        snippetFilesAttributes.push({
          id: snippetFile.id || null,
          title: snippetFile.title,
          content: this.$children[0].$children[index].editor.getValue(),
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

      // console.log(this.$store.state.labelSnippets.edit.snippetFiles)

      saveSnippet(data)

      // Backend.snippet[this.action](this)
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
