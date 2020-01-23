<template>
  <div class="app-container">
    <el-tabs type="card">
      <el-tab-pane label="博客设置">
        <el-form ref="websiteForm" :model="options" :rules="websiteRules">
          <el-form-item label="博客名:">
            <el-input v-model="options.blog_name" placeholder="请输入博客名" />
          </el-form-item>
          <el-form-item label="博客网址:" prop="blog_website">
            <el-input
              v-model="options.blog_website"
              placeholder="请输入博客网址（http, https:// 开头）"
            />
          </el-form-item>
          <el-form-item label="博客底部信息:">
            <el-input
              v-model="options.blog_footer"
              type="textarea"
              :rows="6"
              placeholder="请输入博客底部信息,可以使用html语句"
            />
          </el-form-item>
          <el-form-item>
            <el-button
              v-permission="'/blog/setting/save'"
              type="primary"
              size="small"
              @click="submitOptions"
            >保存修改
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="SEO 设置">
        <el-form ref="form" :model="options">
          <el-form-item label="网站名称(Title):">
            <el-input
              v-model="options.meta_title"
              placeholder="请输入网站名称"
            />
          </el-form-item>
          <el-form-item label="网站描述(description):">
            <el-input
              v-model="options.meta_description"
              placeholder="请输入网站描述"
            />
          </el-form-item>
          <el-form-item label="网站关键字(keywords):">
            <el-input
              v-model="options.meta_keywords"
              placeholder="请输入网站关键字"
            />
          </el-form-item>

          <el-form-item label="Google站点验证(google-site-verification):">
            <el-input
              v-model="options.google_site_verification"
              placeholder="请输入Google站点验证"
            />
          </el-form-item>

          <el-form-item label="Baidu站点验证(baidu-site-verification):">
            <el-input
              v-model="options.baidu_site_verification"
              placeholder="请输入Baidu站点验证"
            />
          </el-form-item>

          <el-form-item label="Google站点分析(google_analytics):">
            <el-input
              v-model="options.google_analytics"
              placeholder="UA-XXXXXXXX-X"
            />
          </el-form-item>

          <el-form-item>
            <el-button
              v-permission="'/blog/setting/save'"
              type="primary"
              size="small"
              @click="submitOptions"
            >保存修改
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane
        label="邮箱设置"
      >
        <el-form ref="emailForm" :model="options" :rules="emailRules">
          <el-form-item label="是否设置邮箱提醒:">
            <el-switch v-model="options.is_email" />
          </el-form-item>
          <div v-show="options.is_email">
            <el-form-item label="邮箱:" prop="email_username">
              <el-input
                v-model="options.email_username"
                placeholder="请输入邮箱号"
              />
            </el-form-item>
            <el-form-item label="邮箱密码:" prop="email_password">
              <el-input
                v-model="options.email_password"
                type="password"
                placeholder="请输入邮箱密码"
              />
            </el-form-item>
            <el-form-item label="主机名:" prop="email_host">
              <el-input
                v-model="options.email_host"
                placeholder="请输入主机名，如smtp.163.com"
              />
            </el-form-item>
            <el-form-item label="端口号:" prop="email_port">
              <el-input
                v-model="options.email_port"
                placeholder="请输入端口号"
              />
            </el-form-item>
            <el-form-item label="邮件标题:" prop="email_subject">
              <el-input
                v-model="options.email_subject"
                placeholder="请输入邮件标题"
              />
            </el-form-item>
          </div>
          <el-form-item>
            <el-button
              v-permission="'/blog/setting/save'"
              type="primary"
              size="small"
              @click="submitOptions()"
            >保存修改
            </el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script type="text/ecmascript-6">
import { getOptions, saveOptions } from '@/api/blog'

export default {
  data: function() {
    const emailSettingValidate = (rule, value, callback) => {
      if (!this.options.is_email) {
        return callback()
      }
      if (value === '') {
        callback(new Error('请输入对应信息'))
      }
    }
    return {
      options: {
        blog_name: '',
        blog_website: '',
        blog_footer: '',
        meta_title: '',
        meta_description: '',
        meta_keywords: '',
        google_site_verification: '',
        baidu_site_verification: '',
        google_analytics: '',
        is_email: false,
        email_username: '',
        email_password: '',
        email_host: '',
        email_port: '',
        email_subject: ''
      },
      websiteRules: {
        blog_website: [{ type: 'url', message: '请输入正确格式的网址', trigger: 'blur' }]
      },
      emailRules: {
        email_username: [
          { type: 'email', message: '请输入正确格式的邮箱', trigger: 'blur' },
          { validator: emailSettingValidate, trigger: 'blur' }
        ],
        email_password: [{ validator: emailSettingValidate, trigger: 'blur' }],
        email_host: [{ validator: emailSettingValidate, trigger: 'blur' }],
        email_port: [
          { validator: emailSettingValidate, trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.getOptions()
  },
  methods: {
    getOptions() {
      getOptions().then(response => {
        const options = response.data
        for (const key in options) {
          this.options[key] = options[key]
        }
        this.options.is_email = this.options.is_email && this.options.is_email === 'true'
      })
    },
    submitOptions() {
      saveOptions(this.options)
        .then(() => {
          this.$util.message.success('更新设置成功!')
        })
    }
  }

}
</script>

<style scoped>
.tip {
  font-size: 14px;
  color: #5e6d82;
  line-height: 1.5em;
}
</style>
