<template>
  <div class="upload-component">
    <el-button
      type="primary"
      icon="el-icon-upload"
      size="small"
      @click="uploadVisible = !uploadVisible"
    >
      <span v-if="uploadVisible">隐藏</span>
      <span v-else>上传</span>
    </el-button>
    <el-button
      v-show="uploadVisible"
      v-waves
      size="small"
      type="info"
      @click="clearUploadFile"
    >
      清空上传列表
    </el-button>
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
  </div>
</template>

<script>
import serverConfig from '../../utils/server-config'
import waves from '@/directive/waves' // waves directive
import store from '@/store'

export default {
  name: 'Upload',
  directives: { waves },
  props: {
    afterUpload: {
      type: Function,
      default: function() {}
    }
  },
  data: function() {
    return {
      uploadVisible: false,
      uploadAction: serverConfig.api + 'v1/api/admin/media/upload',
      uploadData: {
        name: '',
        path: ''
      },
      header: { Authorization: store.getters.token.access_token }
    }
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
