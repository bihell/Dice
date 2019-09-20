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
    <pagination v-show="listQuery.total>0" :total="listQuery.total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="init" />
  </div>
</template>

<script>
import Upload from '../../components/Upload/Upload'
import MediaItem from '../../components/Upload/MediaItem'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

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
      listQuery: {
        total: 0,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        pageNum: 1
      }
    }
  },
  mounted() {
    this.listQuery.pageNum = Number(this.$route.query.page) || 1
    this.init()
  },
  methods: {
    init() {
      pageMedia(this.listQuery.pageSize, this.listQuery.pageNum).then(response => {
        this.mediaDatas = response.data.list
        this.listQuery.total = response.data.total
        this.listQuery.pageSize = response.data.pageSize
        this.listQuery.pageNum = response.data.pageNum
        for (const media of this.mediaDatas) {
          if (media.thumbUrl && media.thumbUrl !== '') {
            media.showUrl = this.$util.getServerMediaUrl(media.thumbUrl)
          } else {
            media.showUrl = this.$util.getServerMediaUrl(media.url)
          }
        }
      })
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
