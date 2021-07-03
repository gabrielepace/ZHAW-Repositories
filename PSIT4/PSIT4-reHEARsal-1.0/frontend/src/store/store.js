import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations.js'
import getters from './getters.js'
import actions from './actions.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    csrf_token: '',
    loading: false,
    success: false,
    error: false,
    networkError: false,
    emptyInput: false,
    tracks: undefined,

    currentSession: undefined,
    currentTrack: undefined,
    currentVersion: undefined,

    // Possible states: USER_INPUT, SUBMIT_TRACK, SUBMIT_VERSIONS, VERSION_SUCCESS, VERSION_FAILED, TRACK_SUCCESS, TRACK_FAILED
    createTrackCurrentState: 'USER_INPUT',

    playingVersionId: 0,
  },
  mutations,
  getters,
  actions,
})
