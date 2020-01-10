<template>
  <div class="app-container">
    <div class="filter-wrap mb-3">
      <div>
        <el-button v-permission="'/auth/api/add'" type="primary" icon="el-icon-edit" size="small" @click="handleCreate">
          新增
        </el-button>
      </div>
      <div style="display: flex">
        <el-select v-model="listQuery.projectType" placeholder="所属项目" class="mr-2" @change="handleFilter()">
          <el-option label="不限制" value="" />
          <el-option v-for="item in subSystem" :key="item.type" :label="item.typeName" :value="item.type" />
        </el-select>
        <el-input v-model="listQuery.criteria" placeholder="api" clearable style="width: 200px;" class="mr-3" @keyup.enter.native="handleFilter" />
        <el-button v-waves icon="el-icon-search" type="primary" size="small" @click="handleFilter">
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

    <el-dialog width="700px" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
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

<script type="script" lang="js" src="./api.js">
</script>

<style lang="scss" scoped>
  .filter-wrap {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
</style>
