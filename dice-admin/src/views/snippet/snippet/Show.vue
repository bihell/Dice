<template>

  <div id="show-snippet">
    <header slot="card-header" class="card-header" style="height: 54px">
      <div class="card-header-title with-text-overflow">
        {{ snippet.id ? snippet.title : '请选择「代码段」' }}
      </div>

      <div v-if="snippet.id" class="card-header-icon">
        <a id="snippet-edit" class="button is-outlined is-small" @click="editSnippet">
          <i class="el-icon-edit"></i>
          <span>编辑</span>
        </a>
        <a id="snippet-delete" class="button is-outlined is-small is-danger" @click="destroySnippet">
          <i class="el-icon-delete"></i>
          <span>删除</span>
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
      没有内容可以显示，请选择一个「代码段」或者「新建」一个！
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
import SnippetFileShow from '../snippet_file/Show.vue'
import { deleteSnippet, getSnippetByMeta, getAllTags } from '@/api/snippet'
import Factory from '../mixins/factory'

export default {
  name: 'SnippetShow',

  components: { SnippetFileShow },

  computed: {
    showSnippetFile() {
      return this.$store.state.labelSnippets.mode
    },

    snippet() {
      return this.$store.state.labelSnippets.active
    }
  },

  methods: {
    editSnippet(e) {
      e.preventDefault()
      this.$store.commit('setSnippetMode', 'edit')
    },
    destroySnippet() {
      // TODO: 这里 getAllTags 和 getSnippetByMeta 代码重复，需要优化
      async function destroyData(store) {
        await deleteSnippet(store.state.labelSnippets.active.id)
        // 更新标签列表
        getAllTags(store.state.querySnippet).then(response => {
          store.commit('setLabels', response.data)
        })
        // 更新代码段列表
        getSnippetByMeta(store.state.labels.active).then(response => {
          store.commit('setLabelSnippets', response.data)
        })
      }

      // 删除代码段确认
      this.$msgbox.confirm('确定要删除代码段「' + this.$store.state.labelSnippets.active.title + '」吗？', '删除代码段', {
        confirmButtonText: '删除',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        destroyData(this.$store)
        this.$store.commit('setActiveLabelSnippet', Factory.methods.factory().snippet)
      }).catch(() => {})
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
