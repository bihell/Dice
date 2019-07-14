<template>
  <div ref="scrollTop" class="scroll-top" @click="toTop">
    <span class="icon-arrow-up" style="font-size: 28px"></span>
  </div>
</template>

<script type="text/ecmascript-6">
let timer = null
let isTop = true
let osTop = null

export default {
  mounted () {
    // 顶部图标的显示与隐藏
    const scrollTop = this.$refs.scrollTop
    window.onscroll = function() {
      if ((document.documentElement.scrollTop || document.body.scrollTop) > 200) {
        scrollTop.classList.add('opacity')
      } else {
        scrollTop.classList.remove('opacity')
      }
      // 鼠标操作时取消滚动
      if (!isTop) {
        clearInterval(timer)
      }
      isTop = false
    }
  },
  methods: {
    // 原生js实现滑动滚动到顶端
    toTop () {
      timer = setInterval(function() {
        osTop = document.documentElement.scrollTop || document.body.scrollTop
        const iSpeed = Math.floor(-osTop / 50) // 减速滚动
        // let iSpeed = -6.5 // 固定速度滚动
        document.documentElement.scrollTop = document.body.scrollTop = osTop + iSpeed
        isTop = true
        if (osTop === 0) {
          clearInterval(timer)
        }
      }, 1)
    }
  }
}
</script>

<style scoped>
.scroll-top {
  text-align: center;
  cursor: pointer;
  opacity: 0;
  position: fixed;
  box-sizing: border-box;
  right: 2em;
  bottom: 45px;
  width: 40px;
  height: 40px;
  padding: 5px;
  border-radius: 20px;
  transform: translate(80px, 0);
  transition: all 0.3s ease;
  box-shadow: 0 0 3px 0 rgba(0, 0, 0, 0.12), 0 3px 3px 0 rgba(0, 0, 0, 0.24);
}

.fa-arrow-up {
  width: 30px;
  height: 30px;
  display: block;
  background-size: contain;
}

.opacity {
  opacity: 1;
  transform: translate(0, 0);
}
</style>
