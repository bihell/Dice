import * as api from '@/api/auth'
import Pagination from '@/components/Pagination'
import MTable from '@/components/MTable'
import MForm from '@/components/MForm'
import { mapState } from 'vuex'

export default {
  name: 'PageAPIList',
  components: { Pagination, MTable, MForm },
  data() {
    return {
      list: [],
      columns: [{
        prop: 'id',
        label: 'id'
      }, {
        prop: 'apiPath',
        label: 'api地址'
      }, {
        prop: 'projectType',
        label: '所属项目'
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
            code: '/auth/api/update',
            event: this.handleUpdate
          }
        ]
      }],
      formInfo: {
        ref: null,
        fieldList: [{
          type: 'input',
          prop: 'apiPath',
          label: 'api地址',
          required: true,
          hide: true
        }, {
          type: 'textarea',
          prop: 'apiPaths',
          label: 'api地址',
          hide: true,
          autosize: {
            minRows: 6
          },
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
      api.getApiList(this.listQuery).then(res => {
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
      this._dealFormItem(1)
      this.dialogFormVisible = true
    },
    /**
     * 打开编辑弹窗
     */
    handleUpdate(row) {
      this.resetForm()
      this.dialogStatus = 'update'
      this._dealFormItem(2)
      this.dialogFormVisible = true
      api.getApi(row.id).then(res => {
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
      if (data.apiPaths) {
        data.apiPaths = this._dealApiParams(data.apiPaths)
      }
      api.addApi(data).then(res => {
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
      api.updateApi(data).then(res => {
        if (res.code === 0) {
          this.dialogFormVisible = false
          this.msgTips('success', '成功', '更新成功')
          this.getList()
        } else {
          this.msgTips('error', '失败', res.msg)
        }
      })
    },
    _dealApiParams(arr) {
      const _arr = arr.split('\n')
      _arr.forEach(item => item.trim())
      _arr.filter(item => item !== null && item !== '')
      return _arr
    },
    /**
     * 处理表单项
     * @param {number} type  1、新增 2、编辑
     */
    _dealFormItem(type) {
      this.formInfo.fieldList.forEach(item => {
        if (item.prop === 'apiPath') {
          item.hide = type === 1
        }
        if (item.prop === 'apiPaths') {
          item.hide = type === 2
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
