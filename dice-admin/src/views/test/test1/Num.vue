<template>
  <button :class="getClass(number)" @click="handleClick()">
    {{ number }}
  </button>
</template>

<script>
  export default {
    props: {
      number: {
        type: Number,
        required: true,
      },
    },
    methods: {
      handleClick() {
        this.$emit('chosen', this.number);
        console.log(this.number);
      },
      getClass(number) {
        if (this.isEven(number)) {
          return 'red';
        }
        return 'blue';
      },
      isEven(number) {
        return number % 2 === 0;
      },
    },
  };
</script>

<style scoped></style>
