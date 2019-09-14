<template>
  <div id="article">
    <h2 class="article-title text-bold">{{ article.title }}</h2>
    <div class="article-info">
      <p class="article-category">
        <span class="icon-folder"></span>
        {{ article.category | formatCategory }}
      </p>
      <p class="article-date">
        <span class="icon-calendar"></span>
        {{ article.created | time('yyyy-MM-dd') }}
      </p>
      <p class="article-date">
        <span class="icon-eye"></span> {{ article.hits }}
      </p>
      <p class="article-date">
        <span class="icon-bubble2"> {{ article.commentCount }} </span>
      </p>
    </div>
    <div v-highlight class="markdown-body" v-html="article.content"></div>
    <div class="article-tags">
      <label class="label-tags">Tags:</label>
      <span
        v-for="tag in $util.stringToTags(article.tags)"
        :key="tag"
        class="article-tag"
      >
        <nuxt-link :to="{ path: '/tag/' + tag }">#{{ tag }}</nuxt-link>
      </span>
    </div>
    <nav class="markdown-toc toc"></nav>
    <comment v-if="article.allowComment" :article-id="article.id"></comment>
    <big-img :visible.sync="isBigImg" :img="img"></big-img>
  </div>
</template>

<script type="text/ecmascript-6">

import tocbot from 'tocbot'
import Comment from '~/components/Comment.vue'
import BigImg from '~/components/BigImg.vue'

export default {
  head () {
    return { title: `${this.article.title}` }
  },
  components: {
    Comment,
    BigImg
  },
  data () {
    return {
      isBigImg: false,
      img: ''
    }
  },
  computed: {
    article () {
      return this.$store.state.article.detail
    }
  },
  fetch ({ store, params }) {
    return store.dispatch('getArticle', params.id)
  },
  mounted () {
    this.tocInit()
    this.initEvent()
  },
  methods: {
    initEvent () {
      const markdown = document.getElementsByClassName('markdown-body')[0]
      const imgs = markdown.getElementsByTagName('img')
      const _this = this
      for (let i = 0; i < imgs.length; i++) {
        imgs[i].addEventListener('click', (e) => {
          e.stopPropagation()
          _this.isBigImg = true
          _this.img = imgs[i].getAttribute('src')
        })
      }
    },
    tocInit () {
      const headingSelector = 'h1, h2, h3, h4'
      const body = document.getElementsByClassName('markdown-body')
      if (body) {
        const tag = body[0].querySelectorAll(headingSelector)
        tag.forEach(function(el) {
          el.setAttribute('id', el.innerHTML)
        })
      }
      tocbot.init({
        tocSelector: '.markdown-toc',
        contentSelector: '.markdown-body',
        headingSelector: headingSelector
      })
      // 延时显示，防止闪烁
      setTimeout(function() {
        document.getElementsByClassName('markdown-toc')[0].style.opacity = 1
      }, 500)
    }
  }
}
</script>

<style>
@import '~/assets/css/markdown-toc.css';

#article .markdown-body img {
  max-width: 100%;
  margin: 0.5rem auto;
  display: block;
  text-align: center;
  border-radius: 4px;
  transition: all 0.25s;
  opacity: 0.9;
  cursor: zoom-in;
}
</style>

<style scoped>
.markdown-toc {
  position: fixed !important;
  min-width: calc((100% - 1200px) / 2);
  max-width: calc((100% - 1000px) / 2);
  max-height: calc(100% - 120px);
  right: 0px;
  top: 100px;
  opacity: 0;
  transition: all 0.3s;
}

@media screen and (max-width: 1300px) {
  .markdown-toc {
    display: none;
  }
}

.article-title {
  color: #34495e;
  margin: 1.2em 0 0;
  font-size: 2em;
}

.article-info {
}

.article-date {
  color: #50596c;
  display: inline-block;
  margin-left: 8px;
}

.article-category {
  color: #50596c;
  display: inline-block;
  margin-right: 8px;
}

.article-tags {
  margin: 15px 0;
}

.article-tags .label-tags {
  margin-right: 6px;
  font-size: 16px;
  font-weight: 600;
  color: #34495e;
}

.article-tags .article-tag {
  font-weight: bold;
  color: #5764c6;
  margin: 0 0.2em;
}

.article-tags .article-tag a {
  color: unset;
  text-decoration: none;
  transition: all 0.3s;
}

.article-tags .article-tag a:hover {
  color: #999;
}

.article-tags .article-tag:nth-child(1n) {
  color: #6fa3ef;
}

.article-tags .article-tag:nth-child(2n) {
  color: #ff9800;
}

.article-tags .article-tag:nth-child(3n) {
  color: #46c47c;
}

.article-tags .article-tag:nth-child(4n) {
  color: #f9bb3c;
}

.article-tags .article-tag:nth-child(5n) {
  color: #bc99c4;
}

@media screen and (max-width: 600px) {
  .article-title {
    font-size: 1.8em;
  }
}
</style>
