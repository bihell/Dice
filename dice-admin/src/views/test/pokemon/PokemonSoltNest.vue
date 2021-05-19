<template>
  <PokemonCards :pokemons="starters" :selected-id="selectedId" @pokemonClicked="fetchEvolutions" />

  <PokemonCards :pokemons="evolutions" />
</template>

<script>
  import PokemonCards from './PokemonCards.vue';

  const api = 'https://pokeapi.co/api/v2/pokemon';
  const STARTER_IDS = [1, 4, 7, 10, 13, 16, 19];

  export default {
    components: {
      PokemonCards,
    },
    data() {
      return {
        starters: [],
        evolutions: [],
        selectedId: null,
      };
    },

    async created() {
      this.starters = await this.fetchData(STARTER_IDS);
    },

    methods: {
      async fetchEvolutions(pokemon) {
        this.selectedId = pokemon.id;
        this.evolutions = await this.fetchData([pokemon.id + 1, pokemon.id + 2]);
        console.log(this.evolutions);
      },

      async fetchData(ids) {
        // Promise.all 表示同时等待多个异步请求
        const responses = await Promise.all(ids.map((id) => window.fetch(`${api}/${id}`)));
        const data = await Promise.all(responses.map((res) => res.json()));
        return data.map((datum) => ({
          name: datum.name,
          sprite: datum.sprites.other['official-artwork'].front_default,
          types: datum.types.map((type) => type.type.name),
          id: datum.id,
        }));
      },
    },
  };
</script>

<style scoped></style>
