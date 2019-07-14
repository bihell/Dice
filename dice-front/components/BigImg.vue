<template>
  <transition name="fade">
    <div v-show="dialogVisible" class="dialog">
      <transition name="slide-down">
        <div v-show="dialogVisible" v-click-outside="hide" class="dialog-body">
          <div class="dialog-content">
            <slot></slot>
            <img
              v-if="img"
              v-click-outside="hide"
              :src="img"
              alt=""
              class="close"
              @click="hide"
            />
          </div>
          <div class="dialog-foot">
            <slot name="foot"></slot>
          </div>
        </div>
      </transition>
    </div>
  </transition>
</template>

<script>
export default {
  props: {
    visible: Boolean,
    img: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      back: {
        height: '',
        overflow: ''
      }
    }
  },
  computed: {
    dialogVisible() {
      if (this.visible) this.lockBody()
      else this.unLockBody()
      return this.visible
    }
  },
  methods: {
    lockBody() {
      this.back.height = document.body.style.height
      this.back.overflow = document.body.style.overflow
      document.body.style.height = '100%'
      document.body.style.overflow = 'hidden'
    },
    unLockBody() {
      if (typeof window === 'undefined') return
      document.body.style.height = this.back.height
      document.body.style.overflow = this.back.overflow
    },
    hide() {
      this.unLockBody()
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style>
.dialog {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: #4d4d4d;
  z-index: 999;
}

.dialog .dialog-body {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: transparent;
  border-radius: 0.5rem;
  box-shadow: 0px 8px 46px rgba(0, 0, 0, 0.08), 0px 2px 6px rgba(0, 0, 0, 0.03);
}

.dialog .dialog-body .dialog-content {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}

.dialog .dialog-body .dialog-content img {
  display: block;
  margin: 0 auto;
  max-width: 90%;
  max-height: 90%;
  cursor: zoom-out;
}

@media screen and (min-width: 600px) {
  .dialog-body {
    width: 24rem;
    top: 4rem;
    left: calc(50% - 12rem);
  }
}
</style>
