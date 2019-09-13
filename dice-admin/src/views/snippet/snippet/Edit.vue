<template>
  <snippet-form :title="title" :action="action"></snippet-form>
</template>

<script>
import SnippetForm from './Form.vue'

export default {
  name: 'SnippetEdit',

  components: { SnippetForm },

  data() {
    return {
      action: '更新'
    }
  },

  computed: {
    title() {
      return '编辑代码段 &nbsp;<span class="is-italic">' + this.$store.state.labelSnippets.active.title + '</span>'
    }
  }
}
</script>
