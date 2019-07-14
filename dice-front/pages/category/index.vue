<template>
  <div>
    <div class="category-list">
      <a
        v-for="category in categories"
        :key="category.id"
        class="category text-bold"
        @click="goAnchor('category-' + category.name)"
      >
        {{ category.name }}({{ category.count }})
      </a>
    </div>
    <div class="category-content">
      <div class="divider"></div>
      <div
        v-for="category in categories"
        :key="category.id"
        class="category-item"
      >
        <div :id="'category-' + category.name" class="category-title">
          {{ category.name }}
        </div>
        <ul class="category-ul">
          <li
            v-for="article in category.articles"
            :key="article.id"
            class="article-title"
          >
            <nuxt-link
              :to="{ path: '/article/' + article.id }"
              class="text-primary"
              >{{ article.title }}
            </nuxt-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  head () {
    return { title: `分类` }
  },
  computed: {
    categories () {
      return this.$store.state.category.data
    }
  },
  fetch ({ store }) {
    return store.dispatch('getCategories')
  },
  methods: {
    goAnchor (id) {
      this.$util.goAnchor(id)
    }
  }
}
</script>

<style scoped>
.category-list {
  margin-top: 40px;
  margin-bottom: 30px;
  text-align: center;
}

.category-list a:hover {
  text-decoration: underline;
}

.category {
  color: #34495e;
  margin: 0.3rem 0.1rem;
  padding: 0.1rem 0.3rem;
}

.category-content {
  margin: 20px auto;
  width: 75%;
}

.category-item {
  padding: 0.5em 0;
}

.category-title {
  margin-right: 6px;
  font-size: 24px;
  font-weight: 600;
  color: #34495e;
}

.category-title:before {
  content: '#';
  margin-right: 5px;
  color: #5764c6;
  font-size: 1.2em;
  font-weight: 700;
}

.article-title a {
  text-decoration: none;
  font-size: 1.1em;
}
</style>
