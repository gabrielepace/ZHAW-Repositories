<template>
  <div>
    <div
      class="track"
      :style="{ 'background-image': wavePathOfNewestVersion }"
      @click.self="trackClicked"
    >
      <div class="track-artist bg-secondary">{{ track.name }}</div>
      <button
        class="round-button bg-primary"
        v-if="showAddButton"
        @click.prevent="addTrackClicked"
      >
        <svg
          class="bi bi-plus"
          width="1.5em"
          height="1.5em"
          viewBox="0 0 16 16"
          fill="currentColor"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M8 3.5a.5.5 0 01.5.5v4a.5.5 0 01-.5.5H4a.5.5 0 010-1h3.5V4a.5.5 0 01.5-.5z"
            clip-rule="evenodd"
          />
          <path
            fill-rule="evenodd"
            d="M7.5 8a.5.5 0 01.5-.5h4a.5.5 0 010 1H8.5V12a.5.5 0 01-1 0V8z"
            clip-rule="evenodd"
          />
        </svg>
      </button>
      <div class="track-duration">{{ trackLength }}</div>
    </div>
    <div class="track-info bg-secondary">
      <div class="track-item add-properties">
        <strong>versions:</strong> {{ versionCount }}
      </div>
      <div class="track-item add-properties">|</div>
      <div class="track-item add-properties">
        <strong>comments:</strong> {{ commentCount }}
      </div>
    </div>
  </div>
</template>

<script>
import * as types from '../../store/types.js'
import { mapActions } from 'vuex'
export default {
  props: ['track', 'showAddButton'],
  methods: {
    ...mapActions({ setCurrentTrack: types.SET_CURRENT_TRACK }),
    trackClicked() {
      this.$router.push({ name: 'versions', params: { id: this.track.id } })
      this.setCurrentTrack(this.track)
    },
    addTrackClicked() {
      this.$router.push({ name: 'addTrack' })
    },
  },
  computed: {
    versionCount() {
      return this.track.versions.length
    },
    commentCount() {
      let commentCounter = 0
      this.track.versions.forEach(version => {
        if (!version.comments) {
          commentCounter += 0
        } else {
          commentCounter += version.comments.length
        }
      })
      return commentCounter
    },
    newestVersion() {
      if (this.track.versions.length > 0) {
        return this.track.versions[this.track.versions.length - 1]
      } else {
        return null
      }
    },
    wavePathOfNewestVersion() {
      if (this.newestVersion) {
        return `url(${this.newestVersion.amplispectrogram})`
      } else {
        return ''
      }
    },
    trackLength() {
      if (this.newestVersion) {
        return this.newestVersion.length
      } else {
        return "00'00"
      }
    },
  },
}
</script>

<style scoped>
.round-button {
  border: none;
  color: white;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  position: absolute;
  top: 0px;
  right: 12px;
}

.track {
  display: flex;
  height: 120px;
  width: 700px;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-end;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;

  position: relative;
}

.track-info {
  display: flex;
  width: 700px;
  margin-bottom: 15px;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: center;
  font-size: 0.8rem;
}

.add-properties {
  padding: 2px 4px 2px 0;
}

.track-info .add-properties:first-of-type {
  margin-left: 6px;
}

.track:hover {
  opacity: 0.7;
}

.track:hover .track-artist {
  background-color: #fff;
}

.track-item {
  flex: 0 1 auto;
}

.track-artist {
  height: 30px;
  padding: 0 9px;
  background-color: #7ca982;
  color: #fff;
  text-align: center;
  line-height: 30px;
  font-size: 1rem;
  position: absolute;
  top: 11px;
  left: 12px;
}

.track-title {
  top: 34px;
  font-size: 1.1rem;
}

.track-duration {
  height: 18px;
  font-size: 13px;
  background-color: #fff;
  color: #000;
  line-height: 18px;
  text-align: center;
  position: absolute;
  bottom: 4px;
  left: 658px;
  padding: 0 3px;
}
</style>
