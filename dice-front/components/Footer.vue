<template>
  <div class="footer">
    <p class="copyright">
      © 2014 - present | Haseo Chen | Proudly powered by
      <a href="https://github.com/bihell/Dice" target="_blank">Dice</a>
    </p>
    <div v-highlight v-html="options.blog_footer" class="markdown-body"></div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  computed: {
    options() {
      return this.$store.state.option.data
    }
  }
}
</script>

<style scoped>
.footer {
  padding: 1em 0;
  margin: 0 auto;
  max-width: 850px;
  border-top: 1px solid #e5e5e5;
  text-align: center;
  font-size: 0.9em;
}

a {
  color: #5764c6;
  text-decoration: none;
}
</style>
