<template>
  <div class="cards">
    <div v-for="starter in starters" class="card">
      <div class="title">
        {{ starter.name }}
      </div>
      <div class="content">
        <img :src="starter.sprite" />
      </div>
      <div class="description">
        <div v-for="type in starter.types">
          {{ type }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  const api = 'https://pokeapi.co/api/v2/pokemon';
  const ids = [1, 4, 7];

  export default {
    data() {
      return {
        starters: [],
      };
    },

    created() {
      this.fetchData();
    },

    methods: {
      async fetchData() {
        // Promise.all 表示同时等待多个异步请求
        const responses = await Promise.all(ids.map((id) => window.fetch(`${api}/${id}`)));
        const data = await Promise.all(responses.map((res) => res.json()));
        this.starters = data.map((datum) => ({
          name: datum.name,
          sprite: datum.sprites.other['official-artwork'].front_default,
          types: datum.types.map((type) => type.type.name),
        }));
      },
    },
  };
</script>

<style scoped>
  .cards {
    display: flex;
  }

  .card {
    max-width: 200px;
    margin: 0 5px;
    cursor: pointer;
    border: 1px solid silver;
    border-radius: 8px;
    box-shadow: 0 1px 3px darkgrey;
    transition: 0.2s;
  }

  .title,
  .content,
  .description {
    padding: 16px;
    text-align: center;
    text-transform: capitalize;
  }

  img {
    width: 100%;
  }

  .title,
  .content {
    border-bottom: 1px solid silver;
  }

  .title {
    font-size: 1.25em;
  }

  .card:hover {
    box-shadow: 0 1px 9px darkgrey;
    transition: 0.2s;
  }
</style>
