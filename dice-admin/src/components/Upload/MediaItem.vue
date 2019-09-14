<template>
  <el-card
    :body-style="{ padding: '0px', textAlign: 'center' }"
    shadow="always"
  >
    <el-image
      :src="media.showUrl"
      fit="cover"
      @click="showDetailDialog(media.id)"
    ></el-image>
    <div @click="showDetailDialog(media.id)">
      <span class="media-title">{{ media.name }}</span>
    </div>
    <div style="margin-bottom: 14px;">
      <el-button-group>
        <el-button
          size="mini"
          icon="el-icon-copy-document"
          @click="copyUrl(media.url)"
        ></el-button>
        <el-button
          type="primary"
          size="mini"
          icon="el-icon-document-copy"
          @click="copyMarkdownUrl(media.name, media.url)"
        >
        </el-button>
        <el-button
          type="danger"
          size="mini"
          icon="el-icon-delete"
          @click="handleDelete(media.id)"
        ></el-button>
      </el-button-group>
    </div>
  </el-card>
</template>

<script>
import { deleteMedia } from '@/api/media'

export default {
  name: 'MediaItem',
  props: {
    media: {
      type: Object,
      default: function() {
        return { id: '', name: '', url: '', thumbUrl: '', suffix: '' }
      }
    },
    afterDelete: {
      type: Function,
      default: function() {}
    }
  },
  methods: {
    copyUrl(url) {
      this.$util.copyText(this.$util.getServerMediaUrl(url))
      this.$util.message.success('复制链接到剪切板成功')
    },
    copyMarkdownUrl(name, url) {
      const realUrl = this.$util.getServerMediaUrl(url)
      const text = '![' + name + '](' + realUrl + ')'
      this.$util.copyText(text)
      this.$util.message.success('复制Markdown格式链接到剪切板成功')
    },
    handleDelete(id) {
      this.$confirm('此操作将永久删除该文章, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'danger'
      })
        .then(() => {
          deleteMedia(id).then(data => {
            if (data.success) {
              this.$util.message.success('删除成功')
              this.afterDelete(data)
            } else {
              this.$util.message.error(data.msg)
            }
          })
        })
        .catch(() => {})
    },
    showDetailDialog() {
      const url = this.$util.getServerMediaUrl(this.media.url)
      window.open(url, '_blank')
    }
  }
}
</script>

<style scoped>
.media-title {
  color: #8492a6;
  font-size: 14px;
  padding: 14px;
  display: inline-block;
  white-space: nowrap;
  width: 60%;
  overflow: hidden;
  text-overflow: ellipsis;
}

.el-image {
  width: 100%;
  height: 160px;
}
</style>
