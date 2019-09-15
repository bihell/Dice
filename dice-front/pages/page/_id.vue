<template>
  <div id="page">
    <div v-highlight class="markdown-body" v-html="page.content"></div>
    <comment v-if="page.allowComment" :article-id="page.id"></comment>
    <big-img :visible.sync="showDialog" :img="img"></big-img>
  </div>
</template>

<script type="text/ecmascript-6">
import BigImg from '~/components/BigImg.vue'
import Comment from '~/components/Comment.vue'

export default {
  head () {
      return { title: `${this.page.title}` }
  },
  components: {
    BigImg,Comment
  },
  data () {
    return {
      showDialog: false,
      img: ''
    }
  },
  computed: {
      page () {
      return this.$store.state.article.page
    }
  },
  fetch ({ store, params }) {
    return store.dispatch('getPage', params.id)
  },
  mounted () {
    this.mountedEvent()
  },
  methods: {
    mountedEvent () {
      const markdown = document.getElementById('page').getElementsByClassName('markdown-body')[0]
      const imgs = markdown.getElementsByTagName('img')
      const _this = this
      for (let i = 0; i < imgs.length; i++) {
        imgs[i].addEventListener('click', (e) => {
          e.stopPropagation()
          _this.showDialog = true
          _this.img = imgs[i].getAttribute('src')
        })
      }
    }
  }
}
</script>
