<template>
  <div class="input-wrapper">
    <div class="label">
      <label :for="name">{{ name }}</label>
      <div class="error">
        {{ error }}
      </div>
    </div>
  </div>
  <input :id="name" :value="value" :type="type" @input="input" />
</template>

<script>
  export default {
    props: {
      name: {
        type: String,
        required: true,
      },
      rules: {
        type: Object, // min,required
      },
      value: {
        type: String,
      },
      type: {
        type: String,
      },
    },
    computed: {
      error() {
        return this.validate(this.value);
      },
    },
    methods: {
      validate(value) {
        if (this.rules.required && !value) {
          return 'Required';
        }

        if (this.rules.min && value.length < this.rules.min) {
          return `Minimun length is ${this.rules.min}`;
        }
      },
      input($evt) {
        this.$emit('update', {
          value: $evt.target.value,
          name: this.name,
          valid: this.validate($evt.target.value) ? false : true,
        });
      },
    },
  };
</script>

<style scoped>
  .input-wrapper {
    display: flex;
    flex-direction: column;
  }

  .label {
    display: flex;
    justify-content: space-between;
  }

  .error {
    color: red;
  }

  input {
    padding: 10px;
    margin: 5px 0;
    font-size: 16px;
    color: black;
    cursor: pointer;
    background: none;
    border: 1px solid silver;
    border-radius: 5px;
  }
</style>
