<template>
  <div class="app-container">
    <upload :after-upload="afterUpload"></upload>
    <el-divider></el-divider>
    <div class="media-list">
      <el-row>
        <el-col
          v-for="media in mediaDatas"
          :key="media.id"
          class="block"
          :xs="24"
          :sm="12"
          :md="12"
          :lg="6"
          :xl="4"
        >
          <media-item :media="media" :after-delete="init"></media-item>
        </el-col>
      </el-row>
    </div>
    <pagination
      :page-size="pageSize"
      :total="total"
      @changePage="changePage"
    ></pagination>
  </div>
</template>

<script>
import Upload from '../../components/Upload/Upload'
import MediaItem from '../../components/Upload/MediaItem'
import Pagination from '../../components/Upload/Pagination'
import { pageMedia } from '@/api/media'

export default {
  components: {
    MediaItem,
    Upload,
    Pagination
  },
  data: function() {
    return {
      mediaDatas: [],
      total: 0,
      pageSize: 10,
      currentPage: 1,
      uploadDialog: false
    }
  },
  mounted() {
    this.currentPage = Number(this.$route.query.page) || 1
    this.init()
  },
  methods: {
    changePage(page) {
      this.currentPage = page
      this.init()
    },
    init() {
      pageMedia(12, this.currentPage).then(data => {
        this.mediaDatas = data.data.list
        this.total = data.data.total
        this.pageSize = data.data.pageSize
        for (const media of this.mediaDatas) {
          if (media.thumbUrl && media.thumbUrl !== '') {
            media.showUrl = this.$util.getServerMediaUrl(media.thumbUrl)
          } else {
            media.showUrl = this.$util.getServerMediaUrl(media.url)
          }
        }
      })
    },
    showUploadDialog() {
      this.uploadDialog = true
    },
    afterUpload(response) {
      if (response.success) {
        this.init()
      }
    }
  }
}
</script>

<style scoped>
.el-table {
  border: 1px solid #e6ebf5;
}

.media-list .block {
  padding: 12px;
  text-align: center;
  border-left: 1px solid #eff2f6;
  border-right: 1px solid #eff2f6;
  display: inline-block;
  box-sizing: border-box;
  vertical-align: top;
}
</style>
