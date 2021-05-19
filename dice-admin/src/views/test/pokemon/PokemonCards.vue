<template>
  <div class="cards">
    <Card
      v-for="pokemon in pokemons"
      :class="{ opace: selectedId && pokemon.id !== selectedId }"
      class="card"
      @click="click(pokemon)"
    >
      <template #title> {{ pokemon.name }} # {{ pokemon.id }} </template>
      <template #content>
        <img :src="pokemon.sprite" />
      </template>
      <template #description>
        <div v-for="type in pokemon.types">
          {{ type }}
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
  import Card from './Card.vue';
  export default {
    components: {
      Card,
    },

    props: {
      pokemons: {
        type: Array,
      },
      selectedId: {
        type: Number,
      },
    },

    methods: {
      click(pokemon) {
        this.$emit('pokemonClicked', pokemon);
      },
    },
  };
</script>

<style scoped>
  .cards {
    display: flex;
  }

  .opace {
    opacity: 0.5;
  }

  .card:hover {
    opacity: 1;
  }

  img {
    width: 100%;
  }
</style>
