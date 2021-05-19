<template>
  <!--  <button :style="{ background: background, color: color }"> 因为名字和值一样，所以可以简写为以下格式 -->
  <!-- 如果有很多样式可以把 :disabled="disabled" 加到class里面，如下，注意disabled-button在css里面定义 -->
  <button :style="{ background, color }" :class="{ 'disabled-button': disabled }"> Button </button>
</template>

<script>
  export default {
    props: {
      color: {
        type: String,
      },
      background: {
        type: String,
      },
      disabled: {
        type: Boolean,
      },
    },
  };
</script>

<style scoped>
  .disabled-button {
    opacity: 0.5;
  }

  button {
    padding: 10px 40px;
    font-size: 16px;
    color: black;
    cursor: pointer;
    background: none;
    border: none;
    border-radius: 5px;
  }

  button:hover {
    filter: brightness(125%);
  }

  /* button:disabled { */

  /*  opacity: 0.5; */

  /* } */
</style>
