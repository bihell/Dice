<template>
  <div class="app-container">
    <div class="filter-wrap mb-3">
      <div>
        <el-button v-permission="'/auth/conent/add'" type="primary" icon="el-icon-edit" @click="handleCreate">
          新增
        </el-button>
      </div>
      <div style="display: flex">
        <el-select v-model="listQuery.projectType" placeholder="所属项目" class="mr-2" @change="handleFilter()">
          <el-option label="不限制" value />
          <el-option v-for="item in subSystem" :key="item.type" :label="item.typeName" :value="item.type" />
        </el-select>
        <el-input v-model="listQuery.criteria" placeholder="内容名称" clearable style="width: 200px;" class="mr-3" @keyup.enter.native="handleFilter" />
        <el-button v-waves icon="el-icon-search" type="primary" @click="handleFilter">
          搜索
        </el-button>
      </div>
    </div>

    <el-card>
      <m-table
        :data="list"
        :columns="columns"
      />

      <pagination v-show="listQuery.total>0" :total="listQuery.total" :page.sync="listQuery.pageNum" :limit.sync="listQuery.pageSize" @pagination="getCurrentList" />

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

  </div>
</template>

<script type="script" lang="js">
import * as api from '@/api/auth'
import Pagination from '@/components/Pagination'
import MTable from '@/components/MTable'
import MForm from '@/components/MForm'
import { mapState } from 'vuex'

export default {
  name: 'PageRoleList',
  components: { Pagination, MTable, MForm },
  data() {
    return {
      list: [],
      total: 0,
      columns: [{
        prop: 'id',
        label: 'ID'
      }, {
        prop: 'contentName',
        label: '内容名称'
      }, {
        prop: 'projectType',
        label: '所属项目'
      }, {
        prop: 'contentType',
        label: '内容类型'
      }, {
        prop: 'contentValue',
        label: '内容信息'
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
            // icon: 'square-edit-outline',
            code: '/auth/content/update',
            event: this.handleUpdate
          }
        ]
      }],
      formInfo: {
        ref: null,
        fieldList: [{
          type: 'input',
          prop: 'contentName',
          label: '内容名称',
          required: true,
          min: 1,
          max: 20
        }, {
          type: 'input',
          prop: 'contentType',
          label: '内容类型',
          required: true,
          min: 1,
          max: 20
        }, {
          type: 'input',
          prop: 'contentValue',
          label: '内容信息',
          required: true
        }, {
          type: 'select',
          prop: 'projectType',
          label: '所属项目',
          listInfo: [],
          required: true
        }]
      },
      data: {},
      listQuery: {
        pageNum: 1,
        pageSize: this.$static.DEFAULT_PAGE_SIZE,
        criteria: '',
        projectType: undefined,
        total: 0
      },
      rules: {},
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '新增'
      }
    }
  },
  computed: {
    ...mapState({
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
      api.getContentList(this.listQuery).then(res => {
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
    },
    /**
       * 打开编辑弹窗
       */
    handleUpdate(row) {
      this.resetForm()
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      api.getContentSingle(row.id).then(res => {
        if (res.code === 0) {
          const result = res.data
          this.data['id'] = result.id

          Object.keys(this.data).forEach(key => {
            if (result[key]) {
              this.data[key] = result[key]
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
      api.addContent(data).then(res => {
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
      api.updateContentSingle(data).then(res => {
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
</style>
