<template>
  <div>
    <dice-header />
    <div class="container">
      <transition name="flow" mode="out-in">
        <nuxt />
      </transition>
    </div>
    <dice-scroll-top />
    <dice-footer />
  </div>
</template>

<script>
import defaultConfig from '../config/default-config'
import DiceHeader from '~/components/Header.vue'
import DiceFooter from '~/components/Footer.vue'
import DiceScrollTop from '~/components/ScrollTop.vue'

export default {
  components: {
    DiceHeader,
    DiceFooter,
    DiceScrollTop
  },
  head() {
    return {
      title: `${this.options.meta_title}` || defaultConfig.meta_title,
      titleTemplate:
        '%s - ' + this.options.meta_title || defaultConfig.meta_title,
      meta: [
        {
          hid: 'description',
          name: 'description',
          content:
            this.options.meta_description || defaultConfig.meta_description
        },
        {
          hid: 'keywords',
          name: 'keywords',
          content: this.options.meta_keywords || defaultConfig.meta_keywords
        },
        {
          hid: 'google-site-verification',
          name: 'google-site-verification',
          content:
            this.options.google_site_verification ||
            defaultConfig.google_site_verification
        },
        {
          hid: 'baidu-site-verification',
          name: 'baidu-site-verification',
          content:
            this.options.baidu_site_verification ||
            defaultConfig.baidu_site_verification
        }
      ]
    }
  },
  computed: {
    options() {
      return this.$store.state.option.data
    }
  }
}
</script>

<style>
.container {
  padding: 2em 1em;
  margin: 30px auto 0;
  height: 100%;
  max-width: 980px;
  font-size: 16px;
  position: relative;
  animation: flow;
  animation-duration: 0.5s;
}
</style>
