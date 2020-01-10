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

<script type="script" lang="js" src="./user.js">
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
