<template>
  <a
    :id="`snippet-collapse-${index}`"
    class="card-header-icon"
    @click="collapseCardContent"
  >
    <i v-if="collapse" class="el-icon-arrow-right"></i>
    <i v-if="!collapse" class="el-icon-arrow-down"></i>
  </a>
</template>

<script>

export default {
  name: 'CollapsibleControls',

  props: {
    index: { type: Number, default: undefined },
    id: { type: String, default: undefined }
  },

  data() {
    return {
      collapse: false
    }
  },

  methods: {
    collapseCardContent(e) {
      e.preventDefault()

      if (this.collapse) {
        this.$root.$el.querySelector(this.id + ' .card-content').classList.remove('collapsed')
      } else {
        this.$root.$el.querySelector(this.id + ' .card-content').classList.add('collapsed')
      }
      this.collapse = !this.collapse
    }
  }
}
</script>
