<template>
  <transition name="play" @before-enter="beforeEnter" @enter="enter">
    <div
      v-if="isPlaying || animationRunning"
      class="play-position-marker"
    ></div>
  </transition>
</template>

<script>
export default {
  props: ['version', 'playerFn'],
  data() {
    return {
      showPlayPositionMarker: true,
      animationRunning: false,
      tick: undefined,
    }
  },
  computed: {
    isPlaying() {
      return this.playerFn().isPlaying
    },
  },
  methods: {
    startAnimation() {
      this.showPlayPositionMarker = !this.showPlayPositionMarker
    },
    beforeEnter(el) {
      el.style.left = 0
      this.animationRunning = true
    },
    enter(el, done) {
      const tick = setInterval(() => {
        if (this.playbackHasEnded) {
          clearInterval(tick)
          this.animationRunning = false
          done()
        }
        el.style.left = `${this.calculateLeft()}px`
      }, 500)
      this.tick = tick
    },
    calculateLeft() {
      const duration = this.playerFn().duration()
      const currentTime = this.playerFn().currentTime()
      return Math.floor((700 / duration) * currentTime)
    },
  },
  beforeDestroy() {
    clearInterval(this.tick)
  },
}
</script>

<style scoped>
.play-position-marker {
  width: 1px;
  background-color: red;
  position: absolute;
  left: 0px;
  top: 0px;
  height: 100%;
  z-index: 2;
}
</style>
