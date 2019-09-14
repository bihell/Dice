<template>

  <card :id="`show-snippet-${index}`">
    <header slot="card-header" class="card-header">
      <collapsible-controls
        :id="`#show-snippet-${index}`"
        :index="index"
      />
      <div class="card-header-title with-text-overflow" style="padding-left: 0;">
        {{ snippetFile.id ? snippetFile.title : '请选择「代码段」' }}
      </div>
      <div v-if="snippetFile.id" class="card-header-icon">
        <div class="field" :class="{ 'has-addons': !isMarkdown }">
          <p v-if="!isMarkdown" class="control">
            <a
              :id="`snippet-copy-${index}`"
              class="button is-outlined is-small"
              :data-clipboard-target="`#code-${index}`"
            >
              <i class="el-icon-document-copy"></i>
              <span>拷贝</span></a>
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
import VueMarkdown from 'vue-markdown'

export default {
  name: 'SnippetFileShow',

  components: { Card, CollapsibleControls, VueMarkdown },

  props: {
    index: { type: Number, default: undefined },
    collapse: { type: Boolean, default: undefined }
  },

  data() {
    return {
      clipboard: null,
      destroyed: false
    }
  },

  computed: {
    snippet() {
      return this.$store.state.labelSnippets.active
    },

    snippetFile() {
      return this.$store.state.labelSnippets.active.snippetFiles[this.index]
    },

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
      this.$util.message.success('已拷贝')
    }).on('error', e => {
      this.$util.message.error('拷贝失败')
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
