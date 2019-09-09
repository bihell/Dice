<template>
  <snippet-form :title="title" :action="action"></snippet-form>
</template>

<script>
import SnippetForm from './Form.vue'

export default {
  name: 'SnippetNew',

  components: { SnippetForm },

  data() {
    return {
      title: 'New snippet',
      action: 'create'
    }
  }
}
</script>
