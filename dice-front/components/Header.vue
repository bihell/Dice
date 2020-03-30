<template>
  <div v-fix class="header">
    <div class="header-container">
      <nuxt-link class="logo" :to="'/'">
        <img src="/logo.png" width="26px" height="26px" />
        <span class="text-primary" style="margin-left: 10px">{{
          options.blog_name || 'Dice'
        }}</span>
      </nuxt-link>
      <ul class="tab link-list">
        <li v-for="(list, index) in links" :key="index" class="tab-item">
          <nuxt-link :to="{ path: list.path }" exact>{{ list.name }}</nuxt-link>
        </li>
        <li class="tab-item">
          <a href="/feed.xml">RSS订阅</a>
        </li>
        <li class="tab-item">
          <a href="https://bigdata.bihell.com">大数据&利器</a>
        </li>
        <li class="tab-item">
          <a href="https://space.bilibili.com/88900889">B站Vlog</a>
        </li>
      </ul>
      <div class="header-menu">
        <div class="header-menu-icon" @click="toggle">
          <span class="icon-th-list" style="font-size: 24px"></span>
        </div>
        <div class="header-menu-list" :class="{ open: menuOpen }">
          <ul>
            <li
              v-for="(list, index) in links"
              :key="index"
              class="header-menu-item text-primary"
            >
              <a @click="to(list.path)">{{ list.name }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <a
      href="https://github.com/bihell/Dice"
      class="github-corner"
      target="_blank"
      aria-label="View source on GitHub"
      ><svg
        width="60"
        height="60"
        viewBox="0 0 250 250"
        style="fill:#151513; color:#fff; position: absolute; top: 0; border: 0; right: 0;"
        aria-hidden="true"
      >
        <path d="M0,0 L115,115 L130,115 L142,142 L250,250 L250,0 Z"></path>
        <path
          d="M128.3,109.0 C113.8,99.7 119.0,89.6 119.0,89.6 C122.0,82.7 120.5,78.6 120.5,78.6 C119.2,72.0 123.4,76.3 123.4,76.3 C127.3,80.9 125.5,87.3 125.5,87.3 C122.9,97.6 130.6,101.9 134.4,103.2"
          fill="currentColor"
          style="transform-origin: 130px 106px;"
          class="octo-arm"
        ></path>
        <path
          d="M115.0,115.0 C114.9,115.1 118.7,116.5 119.8,115.4 L133.7,101.6 C136.9,99.2 139.9,98.4 142.2,98.6 C133.8,88.0 127.5,74.4 143.8,58.0 C148.5,53.4 154.0,51.2 159.7,51.0 C160.3,49.4 163.2,43.6 171.4,40.1 C171.4,40.1 176.1,42.5 178.8,56.2 C183.1,58.6 187.2,61.8 190.9,65.4 C194.5,69.0 197.7,73.2 200.1,77.6 C213.8,80.2 216.3,84.9 216.3,84.9 C212.7,93.1 206.9,96.0 205.4,96.6 C205.1,102.4 203.0,107.8 198.3,112.5 C181.9,128.9 168.3,122.5 157.7,114.1 C157.9,116.9 156.7,120.9 152.7,124.9 L141.0,136.5 C139.8,137.7 141.6,141.9 141.8,141.8 Z"
          fill="currentColor"
          class="octo-body"
        ></path></svg></a
    ><style>
      .github-corner:hover .octo-arm {
        animation: octocat-wave 560ms ease-in-out;
      }
      @keyframes octocat-wave {
        0%,
        100% {
          transform: rotate(0);
        }
        20%,
        60% {
          transform: rotate(-25deg);
        }
        40%,
        80% {
          transform: rotate(10deg);
        }
      }
      @media (max-width: 500px) {
        .github-corner:hover .octo-arm {
          animation: none;
        }
        .github-corner .octo-arm {
          animation: octocat-wave 560ms ease-in-out;
        }
      }
    </style>
  </div>
</template>

<script type="text/ecmascript-6">
import _ from 'underscore'

const staticMenu = [
    { path: '/', name: '首页' },
    { path: '/category', name: '分类' },
    { path: '/tag', name: '标签' },
    { path: '/archive', name: '归档' }
]

export default {
  directives: {
    fix: {
      inserted(el) {
        let beforeScrollTop = document.documentElement.scrollTop ||
          window.pageYOffset ||
          window.scrollY ||
          document.body.scrollTop
        window.addEventListener('scroll', _.throttle(() => {
          const afterScrollTop = document.documentElement.scrollTop ||
            window.pageYOffset ||
            window.scrollY ||
            document.body.scrollTop
          const delta = afterScrollTop - beforeScrollTop
          if (delta === 0) return false
          delta > 0
            ? el.classList.add('fixed')
            : el.classList.remove('fixed')
          setTimeout(() => {
            beforeScrollTop = afterScrollTop
          }, 0)
        }, 200))
      },
      unbind() {
        window.onscroll = null
      }
    }
  },
  data() {
    return {
      links: [],
      menuOpen: false
    }
  },
  computed: {
    options() {
      return this.$store.state.option.data
    },
      pageMenu() {
          return this.$store.state.article.pageMenu
      }
  },
    mounted() {
        this.initMenu()
    },
  methods: {
      initMenu() {
          const links = staticMenu
          this.pageMenu.forEach(menu => {
              links.push({ path: '/page/' + menu.id, name: menu.title })
          })
          this.links = links
      },
    toggle() {
      this.menuOpen = !this.menuOpen
    },
    to(url, query) {
      this.$router.push({ path: url, query })
      this.toggle()
    }
  }
}
</script>

<style scoped>
a {
  color: #234e77;
  text-decoration: none;
}

.header {
  position: fixed;
  background-color: rgba(255, 255, 255, 0.98);
  box-shadow: 0 0 3px rgba(14, 14, 14, 0.26);
  border: none;
  top: 0;
  left: 0;
  width: 100%;
  height: 60px;
  z-index: 200;
  transition: all 0.8s;
}

.fixed {
  transform: translateY(-100%);
}

.header-container {
  max-width: 1200px;
  height: 40px;
  margin: 0 auto;
  padding: 10px 30px;
  font-size: 0.9rem;
  position: relative;
}

.header-container .logo {
  display: flex;
  align-items: center;
  float: left;
  margin: 5px 0;
  font-weight: 600;
  font-size: 1.5em;
}

.header-container .tab {
  list-style: none;
  float: right;
  padding: 0;
  margin: 5px 0;
  border-bottom: none;
}

.header-container .tab .tab-item {
  display: inline-block;
  margin: 0 8px;
}

.header-container .tab .tab-item a {
  border-bottom: 0.1rem solid transparent;
  display: block;
  margin: 0;
  padding: 0.4rem 0.2rem 0.3rem 0.2rem;
  color: #50596c;
  text-decoration: none;
}

.header-container .tab .tab-item a.active {
  border-bottom-color: #234e77;
  color: #234e77;
}

@media screen and (max-width: 600px) {
  .link-list {
    display: none;
  }
}

.link-list .tab-item {
  display: inline-block;
  margin: 0 8px;
}

@media screen and (min-width: 600px) {
  .header-menu {
    display: none;
  }
}

.header-container .header-menu-icon {
  display: flex;
  align-items: center;
  float: right;
  height: 40px;
  line-height: 40px;
}

.header-container .header-menu-list {
  max-height: 0;
  position: fixed;
  left: 0;
  top: 52px;
  z-index: 99;
  width: 100%;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.98);
  -webkit-transition: max-height 0.3s ease;
  transition: max-height 0.3s ease;
}

.header-container .open {
  max-height: 300px;
  transition-delay: 0.1s;
}

.header-container .header-menu-list > ul {
  margin: 0;
  padding: 0;
}

.header-container .header-menu-list .header-menu-item {
  position: relative;
  list-style: none;
  text-align: center;
  padding: 10px 0;
}

.header-container .header-menu-list .header-menu-item:after {
  content: '';
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  border-bottom: 1px solid #e5e5e5;
  -webkit-transform: scaleY(0.5);
  transform: scaleY(0.5);
}
</style>
