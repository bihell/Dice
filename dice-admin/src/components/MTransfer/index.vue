<template>
  <div class="m-transfer-main" :value="value">
    <m-transfer-panel
      ref="mLeftPanel"
      v-bind="$props"
      :m-label="labelProp"
      :placeholder="filterPlaceholder"
      :title="titles[0]"
      :operate-id="0"
      :data-show-list="sourceData"
      :page-size="pageSize"
      @check-district="noCheckSelect"
      @search-word="searchWord"
      @check-disable="checkDisable"
    />
    <div class="opera">
      <el-button
        class="el-transfer__button"
        icon="el-icon-arrow-left"
        type="primary"
        circle
        :disabled="disablePre"
        @click="deleteData"
      />
      <el-button
        class="el-transfer__button"
        icon="el-icon-arrow-right"
        type="primary"
        circle
        :disabled="disableNex"
        @click="addData"
      />
    </div>
    <m-transfer-panel
      ref="mRightPanel"
      v-bind="$props"
      :m-label="labelProp"
      :placeholder="filterPlaceholder"
      :title="titles[1]"
      :operate-id="1"
      :data-show-list="targetData"
      :page-size="pageSize"
      @check-district="hasCheckSelect"
      @search-word="searchWord"
      @check-disable="checkDisable"
    />
  </div>
</template>

<script>
import MTransferPanel from './models/box'
export default {
  name: 'MTransferPanel',
  components: {
    'm-transfer-panel': MTransferPanel
  },
  props: {
    titles: {
      type: Array,
      default: () => ['待选区', '已选中']
    },
    pageSize: {
      type: Number,
      default: 200
    },
    data: {
      type: Array,
      default: () => []
    },
    value: {
      type: Array,
      default() {
        return []
      }
    },
    filterPlaceholder: {
      type: String,
      default: '请输入搜索内容'
    },
    props: {
      type: Object,
      default() {
        return {
          label: 'label',
          key: 'key'
        }
      }
    },
    format: {
      type: Object,
      default() {
        return {}
      }
    }
  },
  data() {
    return {
      leftChecked: [], // 左边已勾选的数据（待添加到已选区域)
      rightChecked: [], // 右边已勾选的数据（从未选区域中待删除)

      leftkeyword: '',
      rightkeyword: '',

      disablePre: true,
      disableNex: true
    }
  },
  computed: {
    labelProp: {
      get: function() {
        return this.props.label || 'label'
      }
    },
    keyProp() {
      return this.props.key || 'key'
    },
    sourceData() {
      return this.data.filter(item => this.value.indexOf(item[this.props.key]) === -1).filter(query => {
        const label = query[this.labelProp] || query[this.keyProp].toString()
        return label.toLowerCase().indexOf(this.leftkeyword.toLowerCase()) > -1
      })
    },
    targetData() {
      return this.data.filter(item => this.value.indexOf(item[this.props.key]) > -1).filter(query => {
        const label = query[this.labelProp] || query[this.keyProp].toString()
        return label.toLowerCase().indexOf(this.rightkeyword.toLowerCase()) > -1
      })
    }
  },
  methods: {
    searchWord(keyword, operateId) {
      // 过滤掉数据，保留搜索的数据
      if (operateId === 0) {
        this.leftkeyword = keyword
      } else {
        this.rightkeyword = keyword
      }
    },
    // 检查左右按钮可用性
    checkDisable(data, operateId) {
      if (operateId === 0) {
        this.disableNex = !(data.length > 0)
      } else {
        this.disablePre = !(data.length > 0)
      }
    },
    // 未选中区域的选泽
    noCheckSelect(val) {
      this.leftChecked = val
    },
    // 已选中区域的选泽
    hasCheckSelect(val) {
      this.rightChecked = val
    },
    // 添加至已选
    addData() {
      // 1、当前已选数据备份
      const currentValue = this.value.slice()
      // 2、获取当前已选数据集
      const currentSelectData = this.data.filter(item => currentValue.indexOf(item[this.props.key]) > -1)
      // 3、判断当前是否有选中数据，如果有则合并
      let checkedData = []
      if (this.leftChecked && this.leftChecked.length) {
        checkedData = [...this.leftChecked, ...currentSelectData]
      } else {
        checkedData = [...currentSelectData]
      }

      const vals = checkedData.map(item => item[this.keyProp])
      this.$emit('input', vals)
    },
    // 从已选中删除
    deleteData() {
      if (!this.rightChecked.length) return
      // 1、当前已选数据备份
      const currentValue = this.value.slice()
      // 2、获取当前已选数据集
      const currentSelectData = this.data.filter(item => currentValue.indexOf(item[this.props.key]) > -1)

      this.rightChecked.forEach(item => {
        const index = currentSelectData.findIndex(element => String(element[this.keyProp]) === String(item[this.keyProp]))
        if (index > -1) {
          currentSelectData.splice(index, 1)
        }
      })

      const vals = currentSelectData.map(item => item[this.keyProp])
      this.$emit('input', vals)
    },
    clearQuery(which) {
      if (which === 'left') {
        this.$refs.mLeftPanel.searchWord = ''
      } else if (which === 'right') {
        this.$refs.mRightPanel.searchWord = ''
      }
    }
  }
}
</script>

<style lang='scss' scoped>
.m-transfer-main {
  min-width: 600px;
}
.inner-center {
  margin: 0 5px;
}
.opera {
  position: relative;
  width: 100px;
  display: inline-block;
  vertical-align: middle;

  .el-button.is-circle {
    border-radius: 50%;
    padding: 12px;
    display: block;
    margin: 25px auto;
  }
}
</style>
