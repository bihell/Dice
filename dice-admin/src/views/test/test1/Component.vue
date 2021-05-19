<template>
  <div>
    <p>chosen是从子组件传过来的</p>
    <num v-for="asdf in numbers" :number="asdf" @chosen="putInArray" />
    <h3>Clicked number</h3>
    <num v-for="asdf in clickedNumber" :number="asdf" />
  </div>
</template>

<script>
  import Num from './Num.vue';
  export default {
    name: 'Test1',
    components: { Num },
    data: function () {
      return {
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        clickedNumber: [],
      };
    },
    computed: {
      error() {
        if (this.value.length < 7) {
          return 'Too short';
        }
      },
    },
    methods: {
      putInArray(number) {
        this.clickedNumber.push(number);
      },
    },
  };
</script>

<style scoped>
  .red {
    color: red;
  }

  .blue {
    color: blue;
  }
</style>
