<template>
  <div class="row">
    <div class="col-lg-6 col-md-8 col-xs-12">
      <app-track-list></app-track-list>
    </div>
  </div>
</template>
<script>
import * as types from '../store/types.js'
import { mapActions, mapGetters } from 'vuex'
import TrackList from '../components/tracks/TrackList.vue'
import { sessionMixin } from '../components/sessions/sessionMixin'

export default {
  mixins: [sessionMixin],
  data() {
    return {
      activeComponent: 'app-track-list',
    }
  },
  methods: {
    ...mapActions({
      loadTracks: types.LOAD_TRACKS_ASYNC,
      initStore: types.INIT_STORE,
    }),
  },
  computed: {
    ...mapGetters({ tracks: types.TRACKS }),
  },
  mounted() {
    this.initStore()
  },
  components: {
    'app-track-list': TrackList,
  },
}
</script>

<style scoped></style>
