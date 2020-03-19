<template>
  <div class="app-container">
    <upload />
  </div>
</template>

<script>
import Upload from '@/components/Upload/Upload'

export default {
  components: {
    Upload
  }
}
</script>

<style scoped>
.media-list .block {
  padding: 12px;
  text-align: center;
  border-left: 1px solid #eff2f6;
  border-right: 1px solid #eff2f6;
  display: inline-block;
  box-sizing: border-box;
  vertical-align: top;
}
</style>
