<template>
  <div>
    <div class="row">
      <div class="col">
        <div
          class="version"
          :style="{ 'background-image': wavePath }"
          @click.self="onBackgroundlick"
        >
          <app-play-pause-player
            :file="audioFilePath"
            class="pp-player"
            ref="player"
          ></app-play-pause-player>
          <div class="version-artist bg-secondary">{{ version.info }}</div>
          <div class="version-created bg-secondary">
            {{ `created ${daysPassedSince()} days ago` }}
          </div>
          <app-play-marker
            :version="version"
            :playerFn="playerFn"
            ref="marker"
          ></app-play-marker>
          <div class="version-duration">{{ trackLength }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PlayPausePlayer from '@/components/versions/PlayPausePlayer'
import PlayMarker from '@/components/versions/PlayMarker'

export default {
  name: 'VersionDisplay',
  props: ['version'],
  data() {
    return {
      showComments: false,
    }
  },
  computed: {
    wavePath() {
      return `url(${this.version.amplispectrogram})`
    },
    trackLength() {
      return this.version.length
    },
    audioFilePath() {
      return this.version.audiofile
    },
  },
  methods: {
    onBackgroundlick(event) {
      const currentTime = (event.layerX * this.$refs.player.duration()) / 700
      this.$refs.player.playAt(currentTime)
    },
    playerFn() {
      return this.$refs.player
    },
    daysPassedSince() {
      const mydate = new Date(this.version.date_created)
      return Math.floor((Date.now() - mydate.getTime()) / (1000 * 60 * 60 * 24))
    },
  },
  components: {
    'app-play-pause-player': PlayPausePlayer,
    'app-play-marker': PlayMarker,
  },
}
</script>

<style scoped>
.version {
  display: flex;
  height: 120px;
  width: 700px;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-end;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  z-index: 1;

  position: relative;
}

.version-info {
  background-color: gray;
  width: 700px;
  height: 24px;
  margin-bottom: 15px;
  flex-flow: row wrap;
  justify-content: flex-start;
  align-items: flex-start;
  border-bottom: solid 1px black;
}

.version-item {
  flex: 0 1 auto;
}

.version-artist,
.version-created {
  padding: 0.3rem 0.8rem;
  background-color: #7ca982;
  color: #fff;
  text-align: center;
  font-size: 1.1rem;
  position: absolute;
  top: 11px;
  left: 76px;
  z-index: 3;
}

.version-created {
  top: 55px;
  font-size: 0.8rem;
}

.pp-player {
  position: absolute;
  top: 5px;
  left: 10px;
}

.version-duration {
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

.add-properties {
  color: #000;
  line-height: 1.1rem;
  font-size: 0.9rem;
}

.add-properties {
  padding-right: 16px;
}

.version-artist,
.pp-player,
.version-duration {
  z-index: 3;
}
</style>
