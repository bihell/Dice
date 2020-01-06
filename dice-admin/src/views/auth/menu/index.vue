<template>
  <div class="app-container custom-tree-container">
    <el-tabs v-model="currentMenu" @tab-click="handleClick">
      <el-tab-pane
        v-for="item in subSystem"
        :key="item.type"
        :label="item.typeName"
        :name="item.type"
      >
        <template v-if="currentMenu && menus[currentMenu]">
          <div class="m-toolbar">
            <div class="m-toolbar-row">
              <el-badge :value="1" class="badge-item mr-3">
                <el-tooltip content="展开一级">
                  <svg-icon icon-class="arrow-expand" size="17" @click.native="expandNode(1, true)" />
                </el-tooltip>
              </el-badge>
              <el-badge :value="2" class="badge-item mr-3">
                <el-tooltip content="展开二级">
                  <svg-icon icon-class="arrow-expand" size="17" @click.native="expandNode(2, true)" />
                </el-tooltip>
              </el-badge>
              <el-badge :value="3" class="badge-item mr-3">
                <el-tooltip content="展开三级">
                  <svg-icon icon-class="arrow-expand" size="17" @click.native="expandNode(3, true)" />
                </el-tooltip>
              </el-badge>
              <el-tooltip content="收起全部">
                <svg-icon icon-class="arrow-collapse" size="17" style="position: relative; top: 12px;" @click.native="expandNode(3, false)" />
              </el-tooltip>
              <svg-icon v-if="edit" icon-class="content-save" size="20" style="position: relative; top: 9px;" @click.native="getCheckedNodes()" />
            </div>
          </div>
          <el-tree
            :ref="item.type"
            :key="item.type"
            default-expand-all
            :show-checkbox="showCheckBox"
            :data="menus[currentMenu]"
            :props="defaultProps"
            node-key="id"
            :default-checked-keys="itemCodesList[item.type]"
            :expand-on-click-node="false"
          >
            <span slot-scope="{ node, data }" class="custom-tree-node">
              <span>
                <svg-icon v-if="node.level === 1" size="17" icon-class="windows" />
                <svg-icon v-if="node.level === 2" size="17" icon-class="folder" />
                <svg-icon v-if="node.level === 3" size="17" icon-class="file-outline" />
                <svg-icon v-if="node.level === 4" size="17" icon-class="settings" />
                {{ node.label }} <span class="tree-itemcode">{{ node.data.itemCode ? node.data.itemCode : '' }}</span></span>
              <span class="slot-icon-more">
                <el-dropdown
                  class="menu-bar-item"
                  trigger="click"
                >
                  <div class="menu-bar-item-more">
                    <svg-icon icon-class="dots-vertical" size="20" />
                  </div>
                  <el-dropdown-menu slot="dropdown" class="menu-bar-dropdown-item">
                    <el-dropdown-item v-if="node.level === 1" v-permission="'/auth/menu/addPageGroup'" @click.native="() => openDialog(1,1, data, node)">
                      添加页面分组
                    </el-dropdown-item>
                    <el-dropdown-item v-if="node.level === 2" v-permission="'/auth/menu/addPage'" @click.native="() => openDialog(2,1, data)">
                      添加页面
                    </el-dropdown-item>
                    <el-dropdown-item v-if="node.level === 3" v-permission="'/auth/menu/addItem'" @click.native="() => openDialog(3,1, data)">
                      添加功能
                    </el-dropdown-item>
                    <el-dropdown-item v-if="node.level === 4" v-permission="'/auth/menu/addItem'" @click.native="() => openApiDialog(data)">
                      配置API
                    </el-dropdown-item>
                    <el-dropdown-item v-if="node.level !== 1" v-permission="'/auth/menu/edit'" @click.native="() => openDialog(node.level -1,2, data)">
                      编辑
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
                <!-- <el-button
                type="text"
                size="mini"
                @click="() => append(data)">
                Append
                </el-button>
                <el-button
                type="text"
                size="mini"
                @click="() => remove(node, data)">
                Delete
                </el-button> -->
              </span>
            </span>
          </el-tree>
        </template>
      </el-tab-pane>
    </el-tabs>
    <div v-if="!currentMenu || currentMenu === '0'" class="text-center"><h1 style="color: rgba(0,0,0,0.54); margin: 0;">选择系统</h1></div>

    <!-- 一级菜单弹窗 -->
    <el-dialog width="800px" :title="form1.dialogStatus === 'update' ? '编辑页面分组' : '添加页面分组'" :visible.sync="form1.dialogFormVisible">
      <m-form
        :ref-obj.sync="form1.ref"
        :data="form1.data"
        :field-list="form1.fieldList"
        :rules="form1.rules"
      >
        <template v-slot:form-isDisplay>
          <div class="slot-isDisplay">
            <el-switch v-model="form1.data.isDisplay" />
          </div>
        </template>
      </m-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="form1.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="submitForm(1, form1.dialogStatus === 'update' ? 2 : 1)">
          确认
        </el-button>
      </div>
    </el-dialog>

    <!-- 二级菜单弹窗 -->
    <el-dialog width="800px" :title="form2.dialogStatus === 'update' ? '编辑页面' : '添加页面'" :visible.sync="form2.dialogFormVisible">
      <m-form
        :ref-obj.sync="form2.ref"
        :data="form2.data"
        :field-list="form2.fieldList"
        :rules="form2.rules"
      >
        <template v-slot:form-isDisplay>
          <div class="slot-isDisplay">
            <el-switch v-model="form2.data.isDisplay" />
          </div>
        </template>
      </m-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="form2.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="submitForm(2, form2.dialogStatus === 'update' ? 2 : 1)">
          确认
        </el-button>
      </div>
    </el-dialog>

    <!-- itemcode弹窗 -->
    <el-dialog width="800px" :title="form3.dialogStatus === 'update' ? '编辑功能' : '添加功能'" :visible.sync="form3.dialogFormVisible">
      <m-form
        :ref-obj.sync="form3.ref"
        :data="form3.data"
        :field-list="form3.fieldList"
        :rules="form3.rules"
      >
        <template v-slot:form-itemCode>
          <div class="slot-isDisplay">
            <el-input
              v-model.trim="form3.data.itemCode"
              placeholder="请输入唯一码Code"
            >
              <i
                slot="suffix"
                class="el-input__icon el-icon-refresh-right"
                style="cursor: pointer"
                @click="form3.data.itemCode = getUUID()"
              />
            </el-input>
          </div>
        </template>
      </m-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="form3.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="submitForm(3, form3.dialogStatus === 'update' ? 2 : 1)">
          确认
        </el-button>
      </div>
    </el-dialog>

    <el-dialog width="830px" :visible.sync="apiDialog.dialogFormVisible">
      <div slot="title"> <span class="dialog-custom-title" v-html="apiDialog.title" /></div>
      <el-transfer
        ref="transfer"
        v-model="apiDialog.data"
        filterable
        filter-placeholder="请选择API"
        :data="apiDialog.list"
        :props="apiDialog.props"
        :titles="apiDialog.titles"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="apiDialog.dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveApiForm()">
          确认
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script type="script" lang="js" src="./menu.js" />

<style lang="scss" scoped>
.custom-tree-node {
	flex: 1;
	display: flex;
	align-items: center;
	justify-content: space-between;
	font-size: 15px;
	padding-right: 8px;
}
.custom-tree-container .custom-tree-node .slot-icon-more {
	display: none;
}
.custom-tree-container {
  /deep/ .el-tabs__item {
    color: rgba(0,0,0,0.57);
  }
  .m-toolbar {
    height: 48px;
    font-size: 14px;
    background: #f5f5f5;
    color: rgba(0,0,0,0.87);
    .m-toolbar-row {
      display: flex;
      place-content: center flex-start;
      box-sizing: border-box;
      padding: 5px 15px;
      .badge-item {
        top: 10px;
        padding-right: 6px;
        /deep/ .el-badge__content {
          background: none;
          color: #000;
          border: 0;
        }
      }
    }
  }
  /deep/ .el-tree-node__content {
    height: 45px;
  }
  .el-tree-node__content:hover .slot-icon-more {
    display: inline-block;
  }
  .tree-itemcode {
    font-size: 12px;
    color: rgba(0,0,0,0.54)
  }
}
.dialog-custom-title {
  /deep/ i {
    font-style: normal;
    color: #1890ff;
  }
}
</style>
