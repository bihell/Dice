<template>
  <div class="app-container">
    <div class="filter-wrap mb-3">
      <div>
        <el-button v-permission="'/auth/role/list/add'" type="primary" icon="el-icon-edit" @click="handleCreate">
          新增
        </el-button>
      </div>
      <div style="display: flex">
        <el-select v-model="listQuery.userType" placeholder="用户类型" style="width: 135px;" class="mr-2" @change="handleFilter()">
          <el-option label="不限制" value />
          <el-option label="ToB用户" value="2" />
          <el-option label="普通用户" value="1" />
        </el-select>
        <el-select v-model="listQuery.projectType" placeholder="所属项目" style="width: 135px;" class="mr-2" @change="handleFilter()">
          <el-option label="不限制" value />
          <el-option v-for="item in subSystem" :key="item.type" :label="item.typeName" :value="item.type" />
        </el-select>
        <el-select v-model="listQuery.roleType" placeholder="请选择角色" style="width: 135px;" class="mr-2" @change="handleFilter()">
          <el-option label="不限制" value />
          <el-option label="用户" value="2" />
          <el-option label="管理员" value="1" />
        </el-select>
        <el-input v-model="listQuery.criteria" placeholder="角色名称" style="width: 200px;" class="mr-3" @keyup.enter.native="handleFilter" />
        <el-button v-waves type="primary" icon="el-icon-search" @click="handleFilter">
          搜索
        </el-button>
      </div>
    </div>

    <el-card>
      <m-table
        :data="list"
        :columns="columns"
      >
        <template slot="tb-userType" slot-scope="scope">
          {{ scope.row.userType === 2 ? 'ToB用户' : '普通用户' }}
        </template>
        <template slot="tb-operation" slot-scope="scope">
          <el-dropdown trigger="click">
            <el-button circle class="border-none">
              <svg-icon icon-class="edit" size="20" />
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-permission="'/auth/role/update'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleUpdate(scope.row)"
                > 编辑 </el-link>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.userType === 2" v-permission="'/auth/role/assign_api'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleApi(scope.row)"
                > 分配API </el-link>
              </el-dropdown-item>
              <el-dropdown-item v-permission="'/auth/role/assign_content'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleContent(scope.row)"
                > 分配内容 </el-link>
              </el-dropdown-item>
              <el-dropdown-item v-permission="'/auth/role/assign_user'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleUser(scope.row)"
                > 分配用户 </el-link>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.userType === 1" v-permission="'/auth/role/assign_item'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleItem(scope.row)"
                > 分配操作项 </el-link>
              </el-dropdown-item>
              <el-dropdown-item v-if="scope.row.userType === 1" v-permission="'/auth/role/show_item'">
                <el-link
                  :underline="false"
                  type="primary"
                  @click="handleShow(scope.row)"
                > 查看操作项 </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </m-table>

      <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getCurrentList" />

    </el-card>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <m-form
        :ref-obj.sync="formInfo.ref"
        :data="data"
        :field-list="formInfo.fieldList"
        :rules="rules"
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

    <el-dialog ref="cc" width="830px" :visible.sync="transfer.dialogFormVisible">
      <div slot="title"> <span class="dialog-custom-title" v-html="transfer.title" /></div>
      <template v-if="!isCustomTransfer">
        <el-transfer
          ref="transfer"
          v-model="transfer.data"
          filterable
          :filter-placeholder="transfer.placeholder"
          :data="transfer.list"
          :props="transfer.props"
          :titles="transfer.titles"
        />
      </template>
      <template v-if="isCustomTransfer">
        <m-transfer
          ref="transfer2"
          v-model="transfer.data"
          :filter-placeholder="transfer.placeholder"
          :data="transfer.list"
          :props="transfer.props"
          :titles="transfer.titles"
        />
      </template>
      <div slot="footer" class="dialog-footer">
        <el-button @click="transfer.dialogFormVisible = false">
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
import MTransfer from '@/components/MTransfer'
import MForm from '@/components/MForm'
import { mapState } from 'vuex'

export default {
  name: 'PageRoleList',
  components: { Pagination, MTable, MForm, MTransfer },
  data() {
    return {
      list: [],
      total: 0,
      columns: [{
        prop: 'id',
        label: '角色ID'
      }, {
        prop: 'roleName',
        label: '角色名字'
      }, {
        prop: 'slot',
        label: '用户类型',
        sign: 'userType'
      }, {
        prop: 'projectType',
        label: '所属项目'
      }, {
        prop: 'userCount',
        label: '有效用户数'
      }, {
        prop: 'slot',
        label: '操作',
        sign: 'operation'
      }],
      formInfo: {
        ref: null,
        fieldList: [{
          type: 'radio',
          prop: 'roleType',
          label: '角色',
          listInfo: [
            { label: '用户', value: 2 },
            { label: '管理员', value: 1 }
          ],
          required: true
        }, {
          type: 'input',
          prop: 'roleName',
          label: '角色名称',
          required: true,
          min: 1,
          max: 20
        }, {
          type: 'select',
          prop: 'userType',
          label: '用户类型',
          listInfo: [
            { label: 'ToB用户', value: 2 },
            { label: '普通用户', value: 1 }
          ],
          required: true
        }, {
          type: 'select',
          prop: 'projectType',
          label: '所属项目',
          listInfo: [],
          required: true
        }, {
          type: 'textarea',
          prop: 'description',
          label: '角色说明',
          max: 100
        }]
      },
      data: {},
      listQuery: {
        pageNum: 1,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        criteria: '',
        roleType: undefined,
        userType: undefined,
        projectType: undefined,
        total: 0
      },
      rules: {},
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      },
      defaultProps: {
        api: {
          key: 'id',
          label: 'apiPath'
        },
        content: {
          key: 'id',
          label: 'contentName'
        },
        user: {
          key: 'id',
          label: 'username'
        }
      },
      transfer: {
        title: '',
        titles: ['全部', '已选'],
        placeHolder: '',
        props: {},
        list: [],
        data: [],
        dialogFormVisible: false,
        roleId: null
      },
      apiList: [],
      contentList: [],
      isCustomTransfer: false
    }
  },
  computed: {
    ...mapState({
      users: state => state.user.users,
      subSystem: state => state.permission.allSubSystemList
    })
  },
  watch: {
    'dialogFormVisible'(val) {
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
  async created() {
    if (!this.subSystem.length) {
      await this.$store.dispatch('permission/getAllSubSystem')
    }
    this.getList()
    // 初始化验证规则
    this.rules = this.$initRules(this.formInfo.fieldList)
    // 初始化表单数据
    this.data = this.$initData(this.formInfo.fieldList)
    this.formInfo.fieldList.forEach(item => {
      if (item.prop === 'projectType') {
        item.listInfo = this.dealData(this.subSystem, { key: 'typeName', value: 'type' })
      }
    })
    api.getApiList({ pageSize: -1 }).then(res => {
      const data = res.data.list || []
      if (data.length) {
        data.forEach(item => {
          item.apiPath = `【${item.projectType}】${item.apiPath}`
        })
      }
      this.apiList = data
    })
    api.getContentList({ pageSize: -1 }).then(res => {
      const data = res.data.list || []
      if (data.length) {
        data.forEach(item => {
          item.contentName = `【${item.projectType}】${item.contentName}`
        })
      }
      this.contentList = data
    })
    if (!this.users.length) {
      await this.$store.dispatch('user/getAllUsers')
    }
  },
  mounted() {},
  methods: {
    dealData(arr, obj) {
      arr.forEach(item => {
        item['label'] = item[obj.key]
        item['value'] = item[obj.value]
      })
      return arr
    },
    /**
       * 获取列表数据
       */
    getList() {
      api.getRoleList(this.listQuery).then(res => {
        if (res.code === 0) {
          this.list = res.data.list || []
          this.listQuery.total = res.data.total
        }
      })
    },
    getCurrentList(page) {
      // 服务端分页需要对查询参数里面的pageSize和pageNum重新赋值
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
    /**
       * 打开新增弹窗
       */
    handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      if (this.dialogStatus === 'create') {
        this.data['roleType'] = 2
      }
    },
    /**
       * 打开编辑弹窗
       */
    handleUpdate(row) {
      this.resetForm()
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      api.getRoleSingle(row.id).then(res => {
        if (res.code === 0) {
          const result = res.data
          this.data['id'] = result.id

          Object.keys(this.data).forEach(key => {
            if (result[key]) {
              this.data[key] = key === 'roleType' ? parseInt(result[key]) : result[key]
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
      api.addRole(data).then(res => {
        if (res.code === 0) {
          this.dialogFormVisible = false
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
      api.updateRoleSingle(data).then(res => {
        if (res.code === 0) {
          this.dialogFormVisible = false
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
    submitForm() {
      this.formInfo.ref.validate((valid) => {
        if (valid) {
          if (this.data.id) {
            this.updateData(this.data)
          } else {
            this.createData(this.data)
          }
        }
      })
    },
    /**
       * 数据重置
       */
    resetForm() {
      this.data = this.$initData(this.formInfo.fieldList)
    },
    handleApi(row) {
      this.isCustomTransfer = false
      this._dealDialogData(row, 1)
    },
    handleContent(row) {
      this.isCustomTransfer = false
      this._dealDialogData(row, 2)
    },
    handleUser(row) {
      this.isCustomTransfer = true
      this._dealDialogData(row, 3)
    },
    saveForm() {
      let _api = api.saveRoleApi
      const params = { id: this.transfer.roleId }
      if (this.transfer.placeHolder === '请选择内容') {
        _api = api.saveRoleContent
        params['contentIds'] = this.transfer.data
      } else if (this.transfer.placeHolder === '请选择用户') {
        _api = api.saveRoleUser
        params['userIds'] = this.transfer.data
      } else {
        params['apiIds'] = this.transfer.data
      }
      _api(params).then(res => {
        if (res.code === 0) {
          this.transfer.dialogFormVisible = false
          this.msgTips('success', '成功', '分配成功')
          if (this.transfer.placeHolder === '请选择用户') {
            this.getList()
          }
        } else {
          this.msgTips('error', '分配失败', res.msg)
        }
      })
    },
    handleItem(row) {
      this.$router.push({ path: '/auth/role/item', query: { id: row.id }})
    },
    handleShow(row) {
      this.$router.push({ path: '/auth/role/show', query: { id: row.id }})
    },
    _dealDialogData(row, type) {
      this.transfer.roleId = null
      this.transfer.data = []
      let _api = null
      switch (type) {
        case 1:
          this.transfer.title = `为<i>#${row.roleName}#</i>分配API`
          this.transfer.placeHolder = '请选择API'
          this.transfer.props = this.defaultProps.api
          this.transfer.list = this.apiList
          _api = api.getApiList({ roleId: row.id, pageSize: -1 })
          break
        case 2:
          this.transfer.title = `为<i>#${row.roleName}#</i>分配内容`
          this.transfer.placeHolder = '请选择内容'
          this.transfer.props = this.defaultProps.content
          this.transfer.list = this.contentList
          _api = api.getContentList({ roleId: row.id, pageSize: -1 })
          break
        case 3:
          this.transfer.title = `为<i>#${row.roleName}#</i>分配用户`
          this.transfer.placeHolder = '请选择用户'
          this.transfer.props = this.defaultProps.user
          this.transfer.list = this.users
          _api = api.getAllUsers({ roleId: row.id, pageSize: -1 })
          break
      }
      _api.then(res => {
        this.transfer.dialogFormVisible = true
        if (type === 3) {
          setTimeout(() => {
            this.$refs.transfer2.clearQuery('left')
            this.$refs.transfer2.clearQuery('right')
          }, 0)
        } else {
          setTimeout(() => {
            this.$refs.transfer.clearQuery('left')
            this.$refs.transfer.clearQuery('right')
          }, 0)
        }
        this.transfer.roleId = row.id
        const result = res.data.list || []
        this.transfer.data = []
        result.forEach(item => {
          // if (type === 3) {
          //   this.transfer.data.push(item.id)
          // } else {
          this.transfer.data.push(item.id)
          // }
        })
      })
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
