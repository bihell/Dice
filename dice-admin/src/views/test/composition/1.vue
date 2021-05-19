<template>
  <button @click="increment">
    {{ count }}
  </button>
  <button @click="increase('a')">
    {{ numbers.a }}
  </button>
  <button @click="increase('b')">
    {{ numbers.b }}
  </button>
  <button @click="a++">
    {{ a }}
  </button>
  <button @click="b++">
    {{ b }}
  </button>
  {{ total }}
  <div v-for="msg in history">
    {{ msg }}
  </div>
  {{ msg2 }}
</template>

<script>
  import { useNumbers } from './numbers';

  export default {
    setup() {
      const { a, b, count, history, increase, total, increment, numbers } = useNumbers();

      return {
        history,
        total,
        increment,
        count,
        numbers,
        increase,
        a,
        b,
      };
    },
    // 老的option api可以混合使用
    data() {
      return {
        msg2: 'ok!',
      };
    },
    mounted() {
      console.log('mounted');
    },
  };
</script>

<style scoped></style>
