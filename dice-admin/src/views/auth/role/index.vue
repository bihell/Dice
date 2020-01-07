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

<script type="script" lang="js" src="./role.js">
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
