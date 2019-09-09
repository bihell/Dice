<template>

  <div id="show-snippet">
    <header slot="card-header" class="card-header">
      <div class="card-header-title with-text-overflow">
        {{ snippet.id ? snippet.title : 'Select snippet' }}
      </div>

      <div v-if="snippet.id" class="card-header-icon">
        <a id="snippet-edit" class="button is-outlined is-small" @click="editSnippet">
          <i class="el-icon-edit"></i>
          <span>Edit</span>
        </a>
        <a id="snippet-delete" class="button is-outlined is-small is-danger" @click="destroySnippet">
          <i class="el-icon-delete"></i>
          <span>Delete</span>
        </a>
      </div>
    </header>

    <div v-if="snippet.id" class="card-description">
      <!-- TODO: experimental. Improve this -->
      <p class="card-header-description no-wrap" v-html="snippet.description"></p>
      <br v-if="snippet.description" />
      <p class="is-italic">Files ({{ snippet.snippetFiles.length }})</p>
    </div>
    <p v-else class="card-description no-wrap">
      Nothing to show. Select a snippet to view or create the new one!
    </p>

    <div
      v-for="(snippetFile, index) in snippet.snippetFiles"
      :key="snippetFile.id"
    >
      <snippet-file-show v-if="showSnippetFile === 'show'" :index="index"></snippet-file-show>
    </div>
  </div>

</template>

<script>
// import Card from '../Card.vue'
// import VueMarkdown from 'vue-markdown'
import SnippetFileShow from '../snippet_file/Show.vue'

export default {
  name: 'SnippetShow',
  // props: ['index'],

  components: { SnippetFileShow },
  data() {
    return {
      tags: [{ name: 'bihell', count: 12, active: 1 }, { name: 'dice', count: 10086, active: 0 }],
      showSnippet: 'show',
      snippet: { title: 'test title', id: 1, description: 'test description', snippetFiles: [{ id: 2 }, { id: 3 }] }
    }
  },

  computed: {
    showSnippetFile() {
      return 'show'
      // return this.$store.state.labelSnippets.mode
    }

    // snippet() {
    //   return true
    //   // return this.$store.state.labelSnippets.active
    // }
  },

  methods: {
    editSnippet(e) {
      e.preventDefault()
      // this.$store.commit('setSnippetMode', 'edit')
    },

    destroySnippet() {
      // Notifications.confirm(
      //   'Are you really sure you want to delete snippet ' +
      //       "<span class='has-text-weight-bold is-italic'>" +
      //       this.snippet.title +
      //       '</span>?',
      //   result => {
      //     if (result.value) {
      //       Backend.snippet.destroy(this)
      //     }
      //   })
    }
  }
}
</script>
<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";
  @import "~highlight.js/styles/github-gist.css";
  .card-description {
    padding: 1rem 0.75em;
  }
</style>
