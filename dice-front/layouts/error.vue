<template>
  <div class="error">
    <h1>{{ statusCode }}</h1>
    <h3>{{ message }}</h3>
  </div>
</template>

<script>
export default {
  props: {
    error: {
      type: Error,
      default: null
    }
  },
  head() {
    return {
      title: this.error.statusCode
    }
  },
  computed: {
    statusCode() {
      return (this.error && this.error.statusCode) || 500
    },
    message() {
      return this.error.message || `<%= messages.client_error %>`
    }
  }
}
</script>

<style scoped>
.error {
  margin: 120px auto;
}

h1 {
  font-size: 64px;
  text-align: center;
}

h3 {
  font-weight: 600;
  text-align: center;
}
</style>
