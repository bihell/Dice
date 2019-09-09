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
                <input id="title" v-model="snippet.snippetTitle" name="title" class="input" type="text">
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
                <input v-model="snippet.snippetDescription" name="description" class="input" />
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
                <input id="snippet-labels" v-model="snippet.snippetLabel" class="input" type="text">
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
              Add file
            </button>
          </div>

          <div class="field is-grouped">
            <div class="control">
              <button class="button is-primary" type="submit" @click="submitAction">{{ action | capitalize }}</button>
            </div>
            <div class="control">
              <button class="button is-text" type="button" @click="cancelAction">Cancel</button>
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

export default {

  components: { Card, SnippetFileForm },

  mixins: [Filters],
  props: ['title', 'action'],

  data() {
    return {
      snippetFiles: [{}],
      snippet: { snippetTitle: '', snippetDescription: '', snippetLabel: '' }
    }
  },

  computed: {
    // editSnippetTitle: {
    //   get() {
    //     // return this.$store.state.labelSnippets.edit.title
    //     return 'test title'
    //   },
    //
    //   set(value) {
    //     // this.$store.commit('setLabelSnippetsEditTitle', value)
    //   }
    // },

    // editSnippetDescription: {
    //   get() {
    //     return 'test description'
    //     // return this.$store.state.labelSnippets.edit.description
    //   },
    //
    //   set(value) {
    //     // this.$store.commit('setLabelSnippetsEditDescription', value)
    //   }
    // },
    //
    // editSnippetLabel: {
    //   get() {
    //     return 'test lable'
    //     // return this.$store.state.labelSnippets.edit.label
    //   },
    //
    //   set(value) {
    //     // this.$store.commit('setLabelSnippetEditLabel', value)
    //   }
    // }

    // snippet() {
    //   return ''
    //   // return this.$store.state.labelSnippets.edit
    // }

    // snippetFiles() {
    //   return [{ content: 'test code 2',
    //     created_at: '2019-08-30T15:02:46.606Z',
    //     id: 20,
    //     language: 'automatically',
    //     snippet_id: 11,
    //     tabs: 4,
    //     title: 'test file 2',
    //     updated_at: '2019-08-30T15:02:46.606Z' }, { content: '# markdown 测试↵##  markdown 测试标题↵↵```↵markdown hello↵```',
    //     created_at: '2019-08-30T15:02:46.603Z',
    //     id: 19,
    //     language: 'markdown',
    //     snippet_id: 11,
    //     tabs: 4,
    //     title: 'test title 1',
    //     updated_at: '2019-09-06T05:54:58.426Z' }]
    //   return this.$store.state.labelSnippets.edit.snippetFiles
    //
    //   return []
    // }
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
      // this.$store.commit('addSnippetFile', snippetIndex)
      // this.$store.commit('setScrollToLatestFileFlag', true)
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
  .card-content {
    padding: 1rem 0.75em;}
</style>
