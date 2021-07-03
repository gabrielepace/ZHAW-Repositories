<template>
  <div>
    <app-version-list-item
      v-for="version in getSortedVersions"
      :version="version"
      :key="version.id"
    ></app-version-list-item>
  </div>
</template>

<script>
import * as types from '../../store/types.js'
import { mapGetters, mapActions } from 'vuex'
import VersionListItem from './VersionListItem.vue'
import { serverBus } from '../../main'

export default {
  props: ['trackId'],
  data() {
    return {
      activePlayer: undefined,
    }
  },
  methods: {
    onPlayTrack(playPausePlayer) {
      if (this.activePlayer) {
        if (this.activePlayer.myID != playPausePlayer.myID) {
          this.activePlayer.stop()
        }
      }
      this.activePlayer = playPausePlayer
    },
  },
  computed: {
    ...mapGetters({
      getSortedVersions: types.VERSIONS_OF_CURRENT_TRACK_SORTED,
    }),
  },
  created() {
    serverBus.$on('play-track', player => {
      this.onPlayTrack(player)
    })
  },
  components: {
    'app-version-list-item': VersionListItem,
  },
}
</script>

<style scoped>
.version-list-item {
  padding-bottom: 2em;
}
</style>
