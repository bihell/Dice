<template>
  <div>
    <div v-for="article in articles" :key="article.id" class="article-item">
      <h2 class="article-head text-bold">
        <nuxt-link :to="{ path: '/article/' + article.id }"
          >{{ article.title }}
        </nuxt-link>
      </h2>
      <p class="article-date">
        <span class="icon-folder"></span>
        {{ article.category | formatCategory }}
      </p>
      <p class="article-date text-italic">
        <span class="icon-calendar"></span>
        {{ article.created | time('yyyy-MM-dd') }}
      </p>
      <p class="article-date">
        <span class="icon-eye"></span> {{ article.hits }}
      </p>
      <p class="article-date">
        <span class="icon-bubble2"> {{ article.commentCount }} </span>
      </p>
      <div class="article-tags">
        <label
          v-for="tag in $util.stringToTags(article.tags)"
          :key="tag"
          class="article-tag"
        >
          <nuxt-link :to="{ path: '/tag/' + tag }">#{{ tag }}</nuxt-link>
        </label>
      </div>
      <div
        v-highlight
        class="article-summary markdown-body"
        v-html="article.content"
      ></div>
      <nuxt-link
        class="article-more text-primary"
        :to="{ path: '/article/' + article.id }"
        >Read more
      </nuxt-link>
    </div>
    <div class="front-page">
      <div v-if="currentPage > 1" class="pre text-primary">
        <nuxt-link :to="{ path: '', query: { page: currentPage - 1 } }"
          >← Pre
        </nuxt-link>
      </div>
      <div v-if="currentPage < totalPage" class="next text-primary">
        <nuxt-link :to="{ path: '', query: { page: currentPage + 1 } }"
          >Next →
        </nuxt-link>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  watchQuery: ['page'],
  key: (to) => to.fullPath,
  transition (to, from) {
    return 'move'
  },
  head () {
    return { title: `Blog` }
  },
  computed: {
    articles () {
      return this.$store.state.article.list.data
    },
    totalPage () {
      return this.$store.state.article.list.totalPage
    },
    currentPage () {
      return this.$store.state.article.list.currentPage
    }
  },
  fetch ({ store, query }) {
    return store.dispatch('getArticles', query.page)
  }
}
</script>

<style>
.article-item .markdown-body img {
  max-width: 100%;
  margin: 0.5rem auto;
  display: block;
  text-align: center;
  border-radius: 4px;
  opacity: 0.9;
}
</style>

<style scoped>
.article-item {
  padding: 2em 0 2em;
  border-bottom: 1px solid #ddd;
}

.article-item:first-child {
  margin-top: 20px;
}

.article-head {
  line-height: 1.2;
  font-size: 1.6rem;
  margin: 0;
}

.article-head > a {
  color: #34495e;
  text-decoration: none;
  position: relative;
  overflow: hidden;
}

.article-head > a:before {
  content: '';
  position: absolute;
  z-index: -1;
  height: 2px;
  bottom: 0;
  left: 51%;
  right: 51%;
  background: #5764c6;
  transition-duration: 0.2s;
}

.article-head > a:hover:before {
  left: 0;
  right: 0;
}

.article-item .article-date {
  display: inline-block;
  color: #7f8c8d;
  margin: 5px 5px;
  font-size: 0.9em;
}

.article-item .article-tags {
  font-weight: bold;
}

.article-item .article-tags .article-tag {
  margin: 0 0.2em;
  text-decoration: none;
}

.article-item .article-tags .article-tag a {
  color: unset;
  text-decoration: none;
  transition: all 0.3s;
}

.article-item .article-tags .article-tag a:hover {
  color: #999;
}

.article-item .article-tags .article-tag:nth-child(1n) {
  color: #6fa3ef;
}

.article-item .article-tags .article-tag:nth-child(2n) {
  color: #ff9800;
}

.article-item .article-tags .article-tag:nth-child(3n) {
  color: #46c47c;
}

.article-item .article-tags .article-tag:nth-child(4n) {
  color: #f9bb3c;
}

.article-item .article-tags .article-tag:nth-child(5n) {
  color: #bc99c4;
}

.article-item .article-summary {
  margin-top: 1.5em;
}

.article-item .article-more {
  font-weight: bold;
  font-size: 16px;
  text-decoration: none;
  display: inline-block;
  transition: all 0.3s;
}

.article-item .article-more:hover {
  transform: translateX(10px);
}

.front-page {
  margin: 4em 3em;
  font-size: 15px;
}

.front-page a {
  font-weight: bold;
  color: #5764c6;
  text-decoration: none;
}

.front-page .pre {
  float: left;
  transition-duration: 0.3s;
}

.front-page .next {
  float: right;
  transition-duration: 0.3s;
}

.front-page .pre:hover {
  transform: translateX(-10px);
}

.front-page .next:hover {
  transform: translateX(10px);
}
</style>
