<template>
  <el-drawer
    title="评论列表"
    :visible.sync="visible"
    direction="rtl"
    style="outline: none"
  >
  </el-drawer>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'CommentDrawer',
  props: {
    visible: {
      type: Boolean,
      required: false,
      default: false
    }},
  data() {
    return {
      commentDialog: false
    }
  }
}
</script>
