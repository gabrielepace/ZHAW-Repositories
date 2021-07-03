<template>
  <div id="add-track-form">
    <form>
      <div class="form-group row">
        <label for="trackName" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
          <input
            :class="['form-control', { 'is-invalid': formIsInvalid }]"
            id="trackName"
            v-model="trackName"
          />
          <div class="invalid-feedback">
            Please enter a name for your track
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import * as types from '@/store/types.js'
export default {
  name: 'CreateTrackForm',
  data() {
    return {
      trackName: '',
      trackSubmitted: false,
      done: false,
    }
  },

  methods: {
    ...mapActions({
      addTrack: types.ADD_TRACK,
      setCurrentProgress: types.SET_CREATE_TRACK_CURRENT_STATE,
    }),
    createTrack() {
      console.log('creating_track')
      this.trackSubmitted = false
      this.addTrack(this.trackName)
    },
  },

  computed: {
    // loads allTracks and getter from store.js
    ...mapGetters({
      currentTrack: types.CURRENT_TRACK,
      loading: types.LOADING,
      createTrackProgress: types.CREATE_TRACK_CURRENT_STATE,
    }),
    error() {
      if (!this.currentTrack) return false
      return this.trackSubmitted && this.currentTrack.id === 0 && !this.loading
    },
    success() {
      if (!this.currentTrack) return false
      return (
        (this.trackSubmitted && this.currentTrack.id !== 0 && !this.loading) ||
        this.done
      )
    },
    formIsInvalid() {
      return this.trackName === '' && this.trackSubmitted
    },
  },

  watch: {
    success(success) {
      if (success) {
        console.log('track creation success!')
        this.done = true
        this.setCurrentProgress('TRACK_CREATED')
      }
    },
    error(error) {
      if (error) {
        console.log('track creation failed')
        this.setCurrentProgress('SUBMIT_TRACK_FAILED')
      }
    },
    createTrackProgress(currentState) {
      if (currentState === 'SUBMIT_TRACK') {
        if (this.trackName !== '') {
          this.createTrack()
        } else {
          this.setCurrentProgress('SUBMIT_TRACK_FAILED')
        }
        this.trackSubmitted = true
      }
      if (currentState === 'USER_INPUT') {
        this.done = false
      }
    },
  },
}
</script>

<style scoped></style>
