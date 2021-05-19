<template>
  <BasicModal v-bind="$attrs" title="功能开发中" :helpMessage="['提示1', '提示2']">
    功能开发中
  </BasicModal>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicModal } from '/@/components/Modal';
  export default defineComponent({
    components: { BasicModal },
  });
</script>
