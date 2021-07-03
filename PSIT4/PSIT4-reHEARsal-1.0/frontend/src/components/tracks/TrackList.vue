<template>
  <div>
    <p v-if="loading">Loading...</p>
    <div class="no-tracks" v-if="success && !loading && getTracks.length === 0">
      <p>
        This session does not contain any tracks yet.
      </p>
    </div>
    <div v-if="getTracks.length > 0">
      <app-track-list-item
        v-for="(track, index) in getTracks"
        :track="track"
        :showAddButton="index === 0"
        :key="index"
      ></app-track-list-item>
    </div>
  </div>
</template>

<script>
import * as types from '../../store/types.js'
import { mapGetters } from 'vuex'
import TrackListItem from './TrackListItem.vue'

export default {
  computed: {
    ...mapGetters({
      success: types.SUCCESS,
      loading: types.LOADING,
      getTracks: types.TRACKS_SORTED,
    }),
  },
  components: {
    'app-track-list-item': TrackListItem,
  },
}
</script>

<style scoped></style>
