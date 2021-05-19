<template>
  <div>
    <a-button @click="increment()"> Increment </a-button>
    <p>{{ count }}</p>
    <div v-if="isEven(count)"> Eve </div>
    <div v-else> Odd </div>
    <div v-for="number in evenList">
      <!--      这里也可以用v-show , 区别不会显示在dom里面-->
      {{ number }}
    </div>
    <a-divider orientation="left"> class 绑定 </a-divider>
    <div v-for="number in numbers">
      <div :class="getClass(number)" :title="number">
        {{ number }}
      </div>
    </div>
    <a-divider orientation="left"> input </a-divider>
    <input v-model="value" type="checkbox" value="a" />
    <input v-model="value" type="checkbox" value="b" />
    {{ value }}
    <h1>
      > 需求:使用vue赋值json数据，并显示到页面的输入框中（表单回显）。
      点击提交按钮，改变json数据，验证同时输入框的内容也发生改变。
    </h1>
    插值表达式: {{ user.username }} ,{{ user.password }} <br />
    v-model 双向绑定,输入的值会改变模型的值:<input v-model="user.username" type="text" /> <br />
    v-bind 单向绑定,输入的值不会改变模型的值:<input type="text" :value="user.username" />
    <br />
    v-model 密码:<input v-model="user.password" type="text" /> <br />
    <input type="button" value="按钮(改变模型的值)" @click="fun()" /> <br />
  </div>
</template>

<script>
  export default {
    name: 'Test1',
    data: function () {
      return {
        count: 3,
        numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        value: ['a'],
        user: {
          username: '路飞',
          password: '123456',
        },
      };
    },
    computed: {
      evenList() {
        // return this.numbers.filter((num) => {
        //   return this.isEven(num);
        // });
        //  可以做如下简化
        return this.numbers.filter(this.isEven);
      },
      error() {
        if (this.value.length < 7) {
          return 'Too short';
        }
      },
    },
    methods: {
      fun() {
        alert(this.user.username + '   ' + this.user.password);
        this.user.username = '佐助';
        this.user.password = '666666';
      },

      // 如果什么变量都不输入，那么默认传入的是event参数，参数名随意
      input($evt) {
        this.value = $evt.target.value;
      },
      getClass(number) {
        if (this.isEven(number)) {
          return 'red';
        }
        return 'blue';
      },
      increment() {
        this.count += 1;
      },
      isEven(number) {
        return number % 2 === 0;
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
