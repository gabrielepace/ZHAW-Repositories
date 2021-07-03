<template>
  <div class="container">
    <h2>Create Track</h2>
    <CreateTrackForm />
    <h2>Versions</h2>
    <AddVersionsForm :startEmpty="false" />
    <button @click="createTrack" type="button" class="btn btn-primary">
      Create Track
    </button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import AddVersionsForm from '@/components/forms/AddVersionsForm'
import CreateTrackForm from '@/components/forms/CreateTrackForm'
import { sessionMixin } from '@/components/sessions/sessionMixin'
import * as types from '@/store/types.js'

export default {
  mixins: [sessionMixin],
  name: 'add_track',
  components: { AddVersionsForm, CreateTrackForm },

  methods: {
    ...mapActions({
      setCurrentVersion: types.SET_CURRENT_VERSION,
      setCurrentProgress: types.SET_CREATE_TRACK_CURRENT_STATE,
      initStore: types.INIT_STORE,
      deleteTrack: types.DELETE_TRACK,
    }),

    createTrack() {
      this.setCurrentProgress('SUBMIT_TRACK')
    },
  },

  computed: {
    ...mapGetters({
      createTrackProgress: types.CREATE_TRACK_CURRENT_STATE,
      getSessionHash: types.CURRENT_SESSION_HASH,
      currentTrack: types.CURRENT_TRACK,
    }),
  },
  watch: {
    createTrackProgress(progress) {
      console.log(progress)
      switch (progress) {
        case 'TRACK_CREATED':
          this.setCurrentProgress('SUBMIT_VERSIONS')
          break

        case 'NO_VERSIONS_UPLOADED':
          this.deleteTrack(this.currentTrack.id)
          /* falls through */

        case 'ALL_VERSIONS_UPLOADED':
        case 'SUBMIT_TRACK_FAILED':
          console.log('addTrack_completed')
          this.setCurrentProgress('USER_INPUT')
          break
      }
    },
  },
  mounted() {
    this.setCurrentProgress('USER_INPUT')

    if (!this.tracks || this.tracks.length === 0) {
      this.initStore()
    }
  },
}
</script>

<style scoped>
.list-group {
  padding: 2% 0%;
}
</style>
