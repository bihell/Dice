<template>
  <div class="card">
    <div class="title">
      <slot name="title" />
    </div>
    <div class="content">
      <slot name="content" />
    </div>
    <div class="description">
      <slot name="description" />
    </div>
  </div>
</template>

<script>
  export default {};
</script>

<style scoped>
  .card {
    max-width: 200px;
    margin: 0 5px;
    cursor: pointer;
    border: 1px solid silver;
    border-radius: 8px;
    box-shadow: 0 1px 3px darkgrey;
    transition: 0.2s;
  }

  .title,
  .content,
  .description {
    padding: 16px;
    text-align: center;
    text-transform: capitalize;
  }

  .title,
  .content {
    border-bottom: 1px solid silver;
  }

  .title {
    font-size: 1.25em;
  }

  .card:hover {
    box-shadow: 0 1px 9px darkgrey;
    transition: 0.2s;
  }
</style>
