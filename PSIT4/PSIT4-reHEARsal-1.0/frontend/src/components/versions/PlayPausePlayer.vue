<template>
  <div class="toggle-player">
    <audio ref="myAudioTag">
      <source :src="file" />
    </audio>
    <button class="toggle-play-button bg-primary" @click="togglePlayPause">
      <svg
        class="bi bi-play-fill"
        width="2.7em"
        height="2.7em"
        v-if="!isPlaying"
        viewBox="0 0 16 16"
        fill="currentColor"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M11.596 8.697l-6.363 3.692c-.54.313-1.233-.066-1.233-.697V4.308c0-.63.692-1.01 1.233-.696l6.363 3.692a.802.802 0 010 1.393z"
        />
      </svg>
      <svg
        class="bi bi-pause-fill"
        width="2.7em"
        height="2.7em"
        v-else
        viewBox="0 0 16 16"
        fill="currentColor"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M5.5 3.5A1.5 1.5 0 017 5v6a1.5 1.5 0 01-3 0V5a1.5 1.5 0 011.5-1.5zm5 0A1.5 1.5 0 0112 5v6a1.5 1.5 0 01-3 0V5a1.5 1.5 0 011.5-1.5z"
        />
      </svg>
    </button>
  </div>
</template>

<script>
import { serverBus } from '../../main'

export default {
  props: {
    file: {
      type: String,
      default: 'bad_intentions.mp3',
    },
    setCurrentTime: {
      type: Function,
      default: time => {
        return 0
      },
    },
  },
  data() {
    return {
      isPlaying: false,
      myID: null,
    }
  },
  methods: {
    togglePlayPause() {
      this.isPlaying = !this.isPlaying

      if (this.isPlaying) {
        this.play()
      } else {
        this.pause()
      }
    },
    play() {
      this.$refs.myAudioTag.play()
      serverBus.$emit('play-track', this)
    },
    pause() {
      this.$refs.myAudioTag.pause()
    },
    playAt(second) {
      this.isPlaying = true
      this.play()
      this.$refs.myAudioTag.currentTime = second
    },
    currentTime() {
      return this.$refs.myAudioTag.currentTime
    },
    duration() {
      return this.$refs.myAudioTag.duration
    },
    ended() {
      return this.$refs.myAudioTag.ended
    },
    stop() {
      this.isPlaying = false
      this.pause()
    },
  },
  mounted() {
    this.myID = `${this.file}_${Date.now()}`
  },
}
</script>

<style scoped>
.toggle-play-button {
  border: none;
  color: white;
  border-radius: 50%;
  width: 56px;
  height: 56px;
}
</style>
