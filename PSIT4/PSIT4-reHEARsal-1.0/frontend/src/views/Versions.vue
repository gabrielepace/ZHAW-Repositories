<template>
  <div>
    <div class="row top-row">
      <div class="col">
        <nav class="breadcrumb-header" aria-label="breadcrumb">
          <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <router-link :to="`/sessions/${getSessionHash}/`">
                <svg
                  class="bi bi-chevron-left"
                  width="1em"
                  height="1em"
                  viewBox="0 0 16 16"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill-rule="evenodd"
                    d="M11.354 1.646a.5.5 0 010 .708L5.707 8l5.647 5.646a.5.5 0 01-.708.708l-6-6a.5.5 0 010-.708l6-6a.5.5 0 01.708 0z"
                    clip-rule="evenodd"
                  />
                </svg>
                Versions of Track:
                {{ getCurrentTrack ? getCurrentTrack.name : '' }}
              </router-link>
            </li>
          </ol>
        </nav>
      </div>
      <div class="delete-track-button">
        <button @click="deleteTrackClicked" class="btn btn-danger">
          Delete Track
        </button>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-6 col-md-8 col-xs-12">
        <app-version-list :trackId="this.$route.params.id"></app-version-list>
      </div>
    </div>
  </div>
</template>

<script>
import * as types from '../store/types.js'
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'
import VersionList from '../components/versions/VersionList.vue'
import { serverBus } from '../main'
import { sessionMixin } from '../components/sessions/sessionMixin'

export default {
  mixins: [sessionMixin],
  computed: {
    ...mapGetters({
      getCurrentTrack: types.CURRENT_TRACK,
      getSessionHash: types.CURRENT_SESSION_HASH,
    }),
  },
  methods: {
    ...mapActions({
      deleteTrack: types.DELETE_TRACK,
      removeTrackFromStore: types.DELETE_TRACK_FROM_STORE,
      initStore: types.INIT_STORE,
      setCurrentTrackById: types.SET_CURRENT_TRACK_BY_ID,
    }),
    deleteTrackClicked() {
      const trackId2Delete = this.getCurrentTrack.id
      this.deleteTrack(trackId2Delete)
        .then(trackWasDeleted => {
          if (trackWasDeleted) {
            serverBus.$emit(
              'add-success-message',
              `track: ${this.getTrackById.name} was successfully deleted`
            )
            this.removeTrackFromStore(trackId2Delete)
            this.$router.push(`/sessions/${this.getSessionHash}/`)
          } else {
            serverBus.$emit(
              'add-error-message',
              'error while deleting track status code was not 200!'
            )
          }
        })
        .catch(error => {
          serverBus.$emit('add-error-message', error)
        })
    },
  },
  components: {
    'app-version-list': VersionList,
  },
  mounted() {
    if (!this.getCurrentTrack) {
      console.log('initializing store')
      this.initStore().then(() =>
        this.setCurrentTrackById(this.$route.params.id)
      )
    }
  },
}
</script>

<style scoped>
.breadcrumb-header {
  margin-top: 10px;
}

.top-row {
  position: relative;
}

.delete-track-button {
  position: absolute;
  top: 15px;
  left: 590px;
}
</style>
