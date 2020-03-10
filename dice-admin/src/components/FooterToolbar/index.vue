<template>
  <div class="footer-toolbar">
    <div style="float: left">
      <slot name="extra">{{ extra }}</slot>
    </div>
    <div style="float: right">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FooterToolBar',
  props: {
    extra: {
      type: [String, Object],
      default: ''
    }
  }
}
</script>
