<template>
  <div class="app-container">
    <div class="block">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-input v-model="listQuery.name" placeholder="请输入任务名" />
        </el-col>
        <el-col :span="6">
          <el-button type="success" icon="el-icon-search" @click.native="search">搜索</el-button>
          <el-button type="primary" icon="el-icon-refresh" @click.native="reset">重置</el-button>
        </el-col>
      </el-row>
      <br>
      <el-row>
        <el-col :span="24">
          <el-button type="success" icon="el-icon-plus" @click.native="add">添加</el-button>
          <el-button type="primary" icon="el-icon-edit" @click.native="edit">修改</el-button>
          <el-button type="danger" icon="el-icon-delete" @click.native="remove">删除</el-button>
        </el-col>
      </el-row>
    </div>

    <el-table
      :data="list"
      border
      fit
      highlight-current-row
      @current-change="handleCurrentChange"
    >
      <el-table-column label="任务名">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="执行类" width="300">
        <template slot-scope="scope">
          {{ scope.row.jobClass }}
        </template>
      </el-table-column>
      <el-table-column label="定时规则">
        <template slot-scope="scope">
          {{ scope.row.cron }}
        </template>
      </el-table-column>

      <el-table-column label="说明">
        <template slot-scope="scope">
          {{ scope.row.note }}
        </template>
      </el-table-column>

      <el-table-column label="最近执行时间">
        <template slot-scope="scope">
          {{ scope.row.execAt }}
        </template>
      </el-table-column>

      <el-table-column label="最近执行结果">
        <template slot-scope="scope">
          {{ scope.row.execResult }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200">
        <template slot-scope="scope">
          <el-button icon="el-icon-log" size="mini" @click.native="viewLog(scope.row.id)">查看日志</el-button>
          <el-button
            v-if="scope.row.status===0"
            type="success"
            icon="el-icon-log"
            size="mini"
            @click.native="enable(scope.row.id)"
          >启用</el-button>
          <el-button
            v-if="scope.row.status===1"
            type="danger"
            icon="el-icon-log"
            size="mini"
            @click.native="disable(scope.row.id)"
          >禁用</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :title="formTitle"
      :visible.sync="formVisible"
      width="70%"
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="任务名" prop="name">
              <el-input v-model="form.name" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="定时规则" prop="cron">
              <el-input v-model="form.cron" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="执行类" prop="jobClass">
              <el-input v-model="form.jobClass" type="textarea" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="任务说明" prop="cfgDesc">
              <el-input v-model="form.note" type="textarea" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="执行参数">
              <el-input v-model="form.data" type="textarea" />
            </el-form-item>
          </el-col>

        </el-row>
        <el-form-item>
          <el-button type="primary" @click="save">提交</el-button>
          <el-button @click.native="formVisible = false">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as task from '@/api/task'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      formVisible: false,
      formTitle: '添加任务',
      deptList: [],
      isAdd: true,
      form: {
        id: '',
        name: '',
        jobClass: '',
        cron: '',
        note: '',
        disabled: true,
        data: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入任务名', trigger: 'blur' },
          { min: 2, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
        ],
        jobClass: [
          { required: true, message: '请输入执行类', trigger: 'blur' }
        ],
        cron: [
          { required: true, message: '请输入定时规则', trigger: 'blur' }
        ]

      },
      listQuery: {
        name: undefined
      },
      total: 0,
      list: null,
      selRow: {}
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      this.fetchData()
    },
    fetchData() {
      task.getList(this.listQuery).then(response => {
        this.list = response.data
      })
    },
    search() {
      this.fetchData()
    },
    reset() {
      this.listQuery.name = ''
      this.fetchData()
    },
    handleFilter() {
      this.listQuery.page = 1
      this.task.getList()
    },
    handleClose() {

    },
    handleCurrentChange(currentRow, oldCurrentRow) {
      this.selRow = currentRow
    },
    resetForm() {
      this.form = {}
    },
    add() {
      this.resetForm()
      this.formTitle = '添加任务'
      this.formVisible = true
      this.isAdd = true
    },
    save() {
      const self = this
      this.$refs['form'].validate((valid) => {
        if (valid) {
          task.save({
            id: self.form.id,
            name: self.form.name,
            jobClass: self.form.jobClass,
            cron: self.form.cron,
            data: self.form.data,
            note: self.form.note
          }).then(response => {
            console.log(response)
            this.$message({
              message: '提交成功',
              type: 'success'
            })
            this.fetchData()
            this.formVisible = false
          })
        } else {
          return false
        }
      })
    },
    checkSel() {
      if (this.selRow && this.selRow.id) {
        return true
      }
      this.$message({
        message: '请选中操作项',
        type: 'warning'
      })
      return false
    },
    enable(id) {
      this.$confirm('确定启用该定时任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        task.enable(id).then(response => {
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    },
    disable(id) {
      this.$confirm('确定禁用该定时任务?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        task.disable(id).then(response => {
          this.$message({
            message: '操作成功',
            type: 'success'
          })
          this.fetchData()
        })
      }).catch(() => {
      })
    },
    viewLog(taskId) {
      this.$router.push({ path: '/tool/task-log', query: { taskId: taskId }})
    },
    edit() {
      if (this.checkSel()) {
        this.isAdd = false
        this.form = this.selRow
        this.formTitle = '修改任务'
        this.formVisible = true
      }
    },
    remove() {
      if (this.checkSel()) {
        const id = this.selRow.id
        this.$confirm('确定删除该记录?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          task.remove(id).then(response => {
            this.$message({
              message: '操作成功',
              type: 'success'
            })
            this.fetchData()
          })
        }).catch(() => {
        })
      }
    }

  }
}
</script>

