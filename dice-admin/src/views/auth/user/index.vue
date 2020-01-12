<template>
  <div class="app-container">
    <div class="filter-wrap mb-3">
      <div>
        <el-button v-permission="'/auth/user/add'" type="primary" size="small" icon="el-icon-edit" @click="handleCreate">
          新增
        </el-button>
      </div>
      <div style="display: flex">
        <el-input v-model.trim="listQuery.criteria" placeholder="用户名称" size="small" style="width: 200px;" clearable class="mr-3" @keyup.enter.native="handleFilter" />
        <el-button v-waves type="primary" icon="el-icon-search" @click="handleFilter">
          搜索
        </el-button>
      </div>
    </div>

    <el-card>
      <m-table
        :data="tableInfo1.list"
        :columns="tableInfo1.columns"
      />
      <pagination v-show="listQuery.total>0" :total="listQuery.total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getList" />
    </el-card>

    <el-dialog width="800px" :visible.sync="dialogFormVisible">
      <div slot="title"> <span class="dialog-custom-title" v-html="dialogTitle" /></div>
      <el-transfer
        ref="transfer"
        v-model="roleData"
        filterable
        filter-placeholder="请输入角色名称"
        :data="roleList"
        :props="defaultProps"
        :titles="titles"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="submitForm()">
          确认
        </el-button>
      </div>
    </el-dialog>

    <el-dialog width="700px" :title="dialog2.title" :visible.sync="dialog2.dialogFormVisible">
      <m-form
        :ref-obj.sync="formInfo.ref"
        :data="formInfo.data"
        :field-list="formInfo.fieldList"
        :rules="formInfo.rules"
      />

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog2.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveForm()">
          确认
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script type="script" lang="js">
import * as api from '@/api/auth'
import Pagination from '@/components/Pagination'
import MTable from '@/components/MTable'
import MForm from '@/components/MForm'

export default {
  name: 'PageUserList',
  components: { Pagination, MTable, MForm },
  data() {
    const checkUserId = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'))
      }
      // setTimeout(() => {
      //   if (!/^(-)[1-9][0-9]*$/.test(value)) {
      //     callback(new Error('只能输入负数'))
      //   } else {
      //     callback()
      //   }
      // }, 300)
      callback()
    }
    const repeatPasswordValidate = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入确认密码'))
      } else if (value !== this.formInfo.data.passwordMd5) {
        callback(new Error('两次输入的密码不一样'))
      } else {
        callback()
      }
    }
    return {
      tableInfo1: {
        list: [],
        columns: [{
          prop: 'id',
          label: '用户ID'
        }, {
          prop: 'username',
          label: '用户名'
        }, {
          prop: 'email',
          label: '邮箱'
        }, {
          prop: 'screenName',
          label: '别名'
        }, {
          prop: 'created',
          label: '创建时间'
        }, {
          prop: 'logged',
          label: '最后登录时间'
        }, {
          prop: 'options',
          label: '操作',
          width: 230,
          align: 'center',
          icon: 'edit',
          options: [
            {
              type: 'primary',
              label: '编辑',
              code: '/auth/user/update',
              event: this.handleUser
            },
            {
              type: 'primary',
              label: '分配角色',
              code: '/auth/user/assign/role',
              event: this.handleRoles
            }
          ]
        }
        ]
      },
      listQuery: {
        pageNum: 1,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        criteria: '',
        total: 0
      },
      dialogFormVisible: false,
      defaultProps: {
        key: 'id',
        label: 'roleName'
      },
      roleData: [],
      roleList: [],
      titles: ['全部', '已选'],
      userId: null,
      dialogTitle: '',
      activeTab: 'first',
      formInfo: {
        ref: null,
        data: {},
        rules: [],
        fieldList: [{
          type: 'input',
          prop: 'username',
          label: '用户名',
          required: true,
          validator: checkUserId
        }, {
          type: 'input',
          prop: 'email',
          label: '邮箱',
          required: true,
          validatorType: 'email'
        }, {
          type: 'input',
          prop: 'screenName',
          label: '别名',
          required: false
        },
        {
          type: 'password',
          prop: 'passwordMd5',
          label: '密码',
          required: true
        },
        {
          type: 'password',
          prop: 'repeatPassword',
          label: '确认密码',
          required: true,
          validator: repeatPasswordValidate
        }
        ]
      },
      dialog2: {
        dialogFormVisible: false,
        title: '新增用户',
        dialogStatus: ''
      }
    }
  },
  watch: {
    'dialog2.dialogFormVisible'(val) {
      const formInfo = this.formInfo
      if (!val) {
        // 表单验证初始化
        if (formInfo.ref) {
          formInfo.ref.resetFields()
        }
        this.resetForm()
      }
    }
  },
  created() {
    // 初始化验证规则
    this.formInfo.rules = this.$initRules(this.formInfo.fieldList)
    // 初始化表单数据
    this.formInfo.data = this.$initData(this.formInfo.fieldList)
    // 初始化普通用户
    this.getList()
    // 获取角色
    api.getRoleList({ pageSize: -1 }).then(res => {
      this.roleList = res.data.list
      this.roleList.map(item => (item.roleName = `【${item.projectType}】${item.roleName}`))
    })
  },
  mounted() {},
  methods: {
    /**
       * 获取列表数据
       */
    getList() {
      api.getUserList(this.listQuery).then(res => {
        this.tableInfo1.list = res.data.list || []
        this.listQuery.total = res.data.total
        this.listQuery.pageSize = res.data.pageSize
      })
    },
    getCurrentList(page) {
      this.listQuery.pageNum = page.page
      this.listQuery.pageSize = page.limit
      this.getList()
    },
    /**
       * 搜索
       */
    handleFilter() {
      this.listQuery.pageNum = 1
      this.getList()
    },
    handleRoles(row) {
      this.roleData = []
      this.userId = null
      this.dialogTitle = `为<i>#${row.username}#</i>分配角色`
      api.getRoleList({ userId: row.id, pageSize: -1 }).then(res => {
        this.dialogFormVisible = true
        setTimeout(() => this.$refs.transfer.clearQuery('left') && this.$refs.transfer.clearQuery('right'), 0)
        this.userId = row.id
        const result = res.data.list || []
        result.forEach(item => this.roleData.push(item.id))
      })
    },
    submitForm() {
      api.saveRoles({
        id: this.userId,
        roleIds: this.roleData
      }).then(res => {
        if (res.code === 0) {
          this.dialogFormVisible = false
          this.msgTips('success', '成功', '角色分配成功')
        } else {
          this.msgTips('error', '角色分配失败', res.msg)
        }
      })
    },
    /**
       * 打开新增弹窗 ToB
       */
    handleCreate() {
      this.dialog2.dialogFormVisible = true
      this.dialog2.title = '新增'
    },
    /**
       * 打开编辑弹窗 ToB
       */
    handleUser(row) {
      this.resetForm()
      this.dialog2.title = '编辑'
      this.dialog2.dialogFormVisible = true
      api.getUserSingle(row.id).then(res => {
        if (res.code === 0) {
          const result = res.data
          this.formInfo.data['id'] = result.id

          Object.keys(this.formInfo.data).forEach(key => {
            if (result[key]) {
              this.formInfo.data[key] = result[key]
            }
          })
        } else {
          this.$message({
            message: res.msg,
            type: 'error'
          })
        }
      })
    },
    /**
       * 新增数据
       */
    createData(params) {
      const data = {
        ...params
      }
      api.addUser(data).then(res => {
        if (res.code === 0) {
          this.dialog2.dialogFormVisible = false
          this.msgTips('success', '成功', '新增成功')
          this.listQuery.pageNum = 1
          this.getList()
        } else {
          this.msgTips('error', '失败', res.msg)
        }
      })
    },
    /**
       * 更新数据
       */
    updateData(params) {
      const data = {
        ...params
      }
      api.updateUserSingle(data).then(res => {
        if (res.code === 0) {
          this.dialog2.dialogFormVisible = false
          this.msgTips('success', '成功', '更新成功')
          this.getList()
        } else {
          this.msgTips('error', '失败', res.msg)
        }
      })
    },
    /**
       * 提交表单
       */
    saveForm() {
      this.formInfo.ref.validate((valid) => {
        if (valid) {
          if (this.formInfo.data.id) {
            this.updateData(this.formInfo.data)
          } else {
            this.createData(this.formInfo.data)
          }
        }
      })
    },
    /**
       * 数据重置
       */
    resetForm() {
      this.formInfo.data = this.$initData(this.formInfo.fieldList)
    },
    /**
       * 消息提示
       */
    msgTips(type, title, msg) {
      this.$notify({
        title: title,
        message: msg,
        type: type,
        duration: 2000
      })
    }
  }
}

</script>

<style lang="scss" scoped>
  .filter-wrap {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .dialog-custom-title {
    /deep/ i {
      font-style: normal;
      color: #1890ff;
    }
  }
</style>
