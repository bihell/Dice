<template>

  <card :id="`show-snippet-${index}`">
    <header slot="card-header" class="card-header">
      <collapsible-controls
        :id="`#show-snippet-${index}`"
        :index="index"
      />
      <div class="card-header-title with-text-overflow" style="padding-left: 0;">
        {{ snippetFile.id ? snippetFile.title : 'Select snippet' }}
      </div>
      <div v-if="snippetFile.id" class="card-header-icon">
        <div class="field" :class="{ 'has-addons': !isMarkdown }">
          <p class="control">
            <a
              :id="`snippet-raw-${index}`"
              class="button is-outlined is-small"
              :href="linkRaw"
              target="_blank"
            ><span>Raw</span></a>
          </p>
          <p v-if="!isMarkdown" class="control">
            <a
              :id="`snippet-copy-${index}`"
              class="button is-outlined is-small"
              :data-clipboard-target="`#code-${index}`"
            >
              <i class="el-icon-document-copy"></i>
              <span>Copy</span></a>
          </p>
        </div>
      </div>
    </header>
    <div slot="card-content" class="card-content">
      <div v-if="isMarkdown" class="markdown-body">
        <vue-markdown lang-prefix="" :source="snippetFile.content"></vue-markdown>
      </div>
      <div v-else class="code-body">
        <pre v-highlightjs="snippetFile.content" :style="{'tab-size': snippetFile.tabs}" class="is-paddingless"><code
            :id="`code-${index}`"
    :class="hljsClass"
style="background:#FFFFFF"
        ></code></pre>
      </div>
    </div>
  </card>

</template>
<script>
import Card from '../Card.vue'
import ClipboardJS from 'clipboard'
import CollapsibleControls from '../CollapsibleControls.vue'
import * as HighlighterHelper from '../../../utils/highlighter_helper'
// import Notifications from '../../utils/notifications'
import VueMarkdown from 'vue-markdown'

export default {
  name: 'SnippetFileShow',

  components: { Card, CollapsibleControls, VueMarkdown },

  props: ['index', 'collapse'],

  data() {
    return {
      clipboard: null,
      destroyed: false,
      // snippetFile: {
      //   'id': 19,
      //   'title': 'test title 1',
      //   'content': "import hljs from 'highlight.js'\nimport { getPropertyOrDefault } from './tools'\n\n",
      //   'language': 'javascript',
      //   'tabs': 4,
      //   'snippet_id': 11,
      //   'created_at': '2019-08-30T15:02:46.603Z',
      //   'updated_at': '2019-09-05T08:59:29.886Z'
      // },
      snippetFile: {
        'id': 19,
        'title': 'test title 1',
        'content': '# markdown 测试\n##  markdown 测试标题\n\n```\nmarkdown hello\n```',
        'language': 'markdown',
        'tabs': 4,
        'snippet_id': 11,
        'created_at': '2019-08-30T15:02:46.603Z',
        'updated_at': '2019-09-06T05:54:58.426Z'
      },
      snippet: {
        description: 'test description',
        id: 11,
        label: Object,
        snippetFiles: Array(2),
        title: 'test title'
      }
    }
  },

  computed: {
    linkRaw() {
      return '/api/v1/snippets/' + this.snippet.id + '/raw/' + this.snippetFile.id
    },

    // snippet() {
    //   return this.$store.state.labelSnippets.active
    // },

    // snippetFile() {
    //   return this.$store.state.labelSnippets.active.snippetFiles[this.index]
    // },

    isMarkdown() {
      return this.snippetFile.language === 'markdown'
    },

    hljsClass() {
      return HighlighterHelper.processHljsMode(this.snippetFile.language)
    }
  },

  mounted() {
    HighlighterHelper.highlightMarkdownCodeBlocks(this)

    this.clipboard = new ClipboardJS(`#snippet-copy-${this.index}`)

    this.clipboard.on('success', e => {
      // Notifications.toast.success('Copied!')
    }).on('error', e => {
      // Notifications.toast.error('Unable to copy snippet.')
    })
  },

  updated() {
    if (!this.destroyed) {
      HighlighterHelper.highlightMarkdownCodeBlocks(this)
    }
  },

  beforeDestroy() {
    this.clipboard.destroy()
  },

  destroyed() {
    this.destroyed = true
  }
}
</script>
<style scoped lang="scss">
  @import "~bulma-scss/bulma.scss";

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
