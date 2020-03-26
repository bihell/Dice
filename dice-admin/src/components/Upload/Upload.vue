<template>
  <div class="upload-component">
    <el-row>
      <el-col :lg="7">
        <el-input
          v-model.trim="queryList.criteria"
          placeholder="搜索媒体"
          clearable
          @clear="init"
          @keyup.enter.native="init"
        />
      </el-col>
      <el-col :lg="2">
        <el-select v-model="queryList.mediaType" placeholder="文件类型" style="margin-left: 10px" @change="init">
          <el-option label="不限制" value="" />
          <el-option
            v-for="(item, index) in mediaTypes"
            :key="index"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-col>
      <el-col :lg="1" style="margin-left: 10px">
        <el-button type="primary" icon="el-icon-search" @click="init">
          搜索
        </el-button>
      </el-col>
      <el-col :lg="1" style="margin-left: 50px">
        <el-button v-permission="'/tool/media/upload'" type="primary" icon="el-icon-upload" @click="uploadVisible = !uploadVisible">
          <span v-if="uploadVisible">隐藏</span>
          <span v-else>上传</span>
        </el-button>
      </el-col>
      <el-col :lg="1" style="margin-left: 45px">
        <el-button
          v-show="uploadVisible"
          v-waves
          type="info"
          @click="clearUploadFile"
        >
          清空上传列表
        </el-button>
      </el-col>
    </el-row>
    <transition name="flow">
      <el-upload
        v-show="uploadVisible"
        ref="upload"
        class="upload-container"
        drag
        :multiple="true"
        list-type="picture"
        :action="uploadAction"
        :with-credentials="true"
        :data="uploadData"
        :before-upload="beforeUpload"
        :on-success="successUpload"
        :on-error="errorUpload"
        :headers="header"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处
          <br />
          或
          <br />
          <em>点击上传</em>
          <div slot="tip" class="el-upload__tip">
            只能上传图片文件，且不超过10m
          </div>
        </div>
      </el-upload>
    </transition>
    <div class="media-list">
      <el-row>
        <el-col
          v-for="media in mediaList"
          :key="media.id"
          style="padding: 6px"
          :xs="24"
          :sm="12"
          :md="12"
          :lg="6"
          :xl="4"
        >
          <media-item
            :media="media"
            :after-delete="afterDeleteMedia"
          />
        </el-col>
      </el-row>
      <pagination v-show="queryList.total>0" :total="queryList.total" :page.sync="queryList.pageNum" :limit.sync="queryList.pageSize" @pagination="init" />
    </div>
  </div>
</template>

<script>
import serverConfig from '@/utils/server-config'
import waves from '@/directive/waves' // waves directive
import store from '@/store'
import { getMediaList, getMediaTypes } from '@/api/media'
import MediaItem from '@/components/Upload/MediaItem'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

export default {
  name: 'Upload',
  components: {
    MediaItem,
    Pagination
  },
  directives: { waves },
  data: function() {
    return {
      mediaTypes: [],
      mediaList: [],
      queryList: {
        total: 0,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        pageNum: 1,
        criteria: '',
        mediaType: undefined
      },
      uploadAction: serverConfig.api + 'v1/api/admin/media/upload',
      uploadVisible: false,
      mediaName: '',
      uploadData: {
        name: '',
        path: ''
      },
      header: { Authorization: store.getters.token.access_token }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    clearUploadFile() {
      this.$refs.upload.clearFiles()
    },
    beforeUpload(file) {
      const fileName = file.name
      const extension = fileName.substring(file.name.lastIndexOf('.') + 1)
      if (extension === '') {
        this.$util.message.error(fileName + '文件格式不正确')
        return false
      }

      const size = file.size / (1024 * 1024)
      if (size > 10) {
        this.$util.message.error(fileName + '大于10m')
        return false
      }

      if (
        extension.toLowerCase() !== 'jpg' &&
        extension.toLowerCase() !== 'png' &&
        extension.toLowerCase() !== 'gif' &&
        extension.toLowerCase() !== 'jpeg' &&
        extension.toLowerCase() !== 'svg'
      ) {
        this.$util.message.error(fileName + '不为图片格式')
        return false
      }
      this.uploadData.name = file.name
      this.uploadData.path = this.$dayjs(new Date()).format('YYYY/MM')
    },
    successUpload(response, file) {
      if (response.success) {
        this.$util.message.success('上传' + file.name + '成功!')
      } else {
        this.$util.message.error('上传' + file.name + '失败!' + response.msg)
      }
      this.afterUpload(response, file)
    },
    errorUpload(err, file) {
      this.$util.message.error('网络异常,上传' + file.name + '失败!')
      console.log(err)
    },
    init() {
      getMediaTypes().then(res => {
        this.mediaTypes = res.data
      })
      getMediaList(this.queryList).then(response => {
        this.mediaList = response.data.list
        this.queryList.total = response.data.total
        this.queryList.pageSize = response.data.pageSize
        this.queryList.pageNum = response.data.pageNum
        for (const media of this.mediaList) {
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
    },
    afterDeleteMedia(data) {
      if (data.success) {
        this.init()
      }
    }
  }
}
</script>

<style>
.el-upload--picture {
  display: block;
}

.el-upload-dragger {
  width: 100%;
}
</style>

<style scoped>
.upload-component {
  margin-bottom: 24px;
}

.upload-container {
  margin-top: 24px;
  text-align: center;
}

.upload-container .el-icon-upload {
  margin-top: 20px;
}

/* flow */
.flow-enter-active,
.flow-leave-active {
  transition: all 0.5s;
}

.flow-enter,
.flow-leave {
  transform: translateY(-20px);
  opacity: 0;
}
</style>
