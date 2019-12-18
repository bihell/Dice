<template>
  <el-form
    ref="form"
    class="page-form"
    :class="className"
    :model="data"
    :rules="rules"
    :label-width="labelWidth + 'px'"
    :style="{width: width + 'px'}"
  >
    <template v-for="(item, index) in getConfigList()">

      <el-form-item
        v-if="!item.hide"
        :key="index"
        :prop="item.prop"
        :label="item.label"
        :class="item.className"
      >
        <!-- solt -->
        <template v-if="item.type === 'slot'">
          <slot :name="'form-' + item.prop" />
        </template>
        <!-- 普通输入框 -->
        <el-input
          v-if="item.type === 'input' || item.type === 'password'"
          v-model.trim="data[item.prop]"
          :type="item.type"
          :disabled="item.disabled"
          :placeholder="getPlaceholder(item)"
          @change="handleEvent(item.event)"
        />
        <!-- 文本输入框 -->
        <el-input
          v-if="item.type === 'textarea'"
          v-model="data[item.prop]"
          :type="item.type"
          :disabled="item.disabled"
          :placeholder="getPlaceholder(item)"
          :autosize="item.autosize || {minRows: 2, maxRows: 10}"
          @change="handleEvent(item.event)"
        />
        <!-- 计数器 -->
        <el-input-number
          v-if="item.type === 'inputNumber'"
          v-model="data[item.prop]"
          size="small"
          :min="item.min"
          :max="item.max"
          @change="handleEvent(item.event)"
        />
        <!-- 选择框 -->
        <el-select
          v-if="item.type === 'select'"
          v-model="data[item.prop]"
          :disabled="item.disabled"
          :clearable="item.clearable"
          :filterable="item.filterable"
          :placeholder="getPlaceholder(item)"
          :multiple="item.multi"
          @change="handleEvent(item.event)"
        >
          <el-option
            v-for="(childItem, childIndex) in item.listInfo"
            :key="childIndex"
            :label="childItem.label"
            :value="childItem.value"
          />
        </el-select>
        <!-- 日期选择框 -->
        <el-date-picker
          v-if="item.type === 'date'"
          v-model="data[item.prop]"
          :type="item.dateType"
          :picker-options="item.TimePickerOptions"
          :clearable="item.clearable"
          :disabled="item.disabled"
          :value-format="item.valueFormat ? item.valueFormat : ''"
          :format="item.format ? item.format : 'yyyy-MM-dd'"
          :placeholder="getPlaceholder(item)"
          @change="handleEvent(item.event)"
        />
        <template v-if="item.type === 'radio'">
          <el-radio-group v-model="data[item.prop]" :disabled="item.disabled" @change="handleEvent(item.event)">
            <el-radio
              v-for="(childItem, childIndex) in item.listInfo"
              :key="childIndex"
              v-model="data[item.prop]"
              :label="childItem.value"
            >
              {{ childItem.label }}
            </el-radio>
          </el-radio-group>
        </template>
        <template v-if="item.type === 'checkbox'">
          <el-checkbox-group v-model="data[item.prop]" :disabled="item.disabled" @change="handleEvent(item.event)">
            <el-checkbox
              v-for="(childItem, childIndex) in item.listInfo"
              :key="childIndex"
              :label="childItem.value"
            >
              {{ childItem.label }}
            </el-checkbox>
          </el-checkbox-group>
        </template>
      </el-form-item>
    </template>
  </el-form>
</template>

<script>
export default {
  name: 'MForm',
  props: {
    // 自定义类名
    className: {
      type: String,
      default: ''
    },
    // 表单数据
    data: {
      type: Object,
      default: () => {}
    },
    // 相关字段
    fieldList: {
      type: Array,
      default: () => []
    },
    // 验证规则
    rules: {
      type: Object,
      default: () => []
    },
    // label宽度
    labelWidth: {
      type: String,
      default: '100'
    },
    // eslint-disable-next-line vue/require-default-prop
    refObj: {
      type: Object
    },
    // form表单容器宽度
    width: {
      type: String,
      default: '500'
    }
  },
  data() {
    return {
    }
  },
  watch: {
    data: {
      handler: function(val) {
        // 将form实例返回到父级
        this.$emit('update:refObj', this.$refs.form)
      },
      deep: true // 深度监听
    }
  },
  mounted() {
    // 将form实例返回到父级
    this.$emit('update:refObj', this.$refs.form)
  },
  methods: {
    // 获取字段列表
    getConfigList() {
      return this.fieldList.filter(item => !item.hidden)
    },
    // 得到placeholder的显示
    getPlaceholder(row) {
      let placeholder
      if (row.type === 'input' || row.type === 'textarea') {
        placeholder = '请输入' + row.label
      } else if (row.type === 'select' || row.type === 'time' || row.type === 'date') {
        placeholder = '请选择' + row.label
      } else {
        placeholder = row.label
      }
      return placeholder
    },
    // 绑定的相关事件
    handleEvent(evnet) {
      this.$emit('handleEvent', evnet)
    }
  }
}
</script>

<style lang="scss" scoped>
/deep/ .el-select--medium {
  width: 100%;
}
</style>
