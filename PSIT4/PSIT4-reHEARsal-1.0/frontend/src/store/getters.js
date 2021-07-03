import * as types from './types.js'

export default {
  [types.TRACKS]: state => {
    return state.tracks
  },
  [types.TRACKS_SORTED]: state => {
    if (!state.tracks) return []
    return state.tracks.sort((a, b) => a.name.localeCompare(b.name))
  },
  [types.TRACK_BY_ID]: state => id => {
    return state.tracks.find(track => track.id == id)
  },
  [types.CURRENT_TRACK]: state => {
    return state.currentTrack
  },
  [types.CURRENT_SESSION]: state => {
    return state.currentSession
  },
  [types.CURRENT_SESSION_HASH]: state => {
    if (state.currentSession === undefined) {
      return 'undefined'
    }
    return state.currentSession.hash_string
  },
  [types.CURRENT_SESSION_ID]: state => {
    return state.currentSession.id
  },
  [types.CURRENT_VERSION]: state => {
    return state.currentVersion
  },
  [types.LOADING]: state => {
    return state.loading
  },
  [types.SUCCESS]: state => {
    return state.success
  },
  [types.CREATE_TRACK_CURRENT_STATE]: state => {
    return state.createTrackCurrentState
  },
  [types.VERSIONS_OF_CURRENT_TRACK_SORTED]: state => {
    if (!state.currentTrack) return []
    const versions = state.currentTrack.versions
    console.log(state.currentTrack)
    return versions.sort((firstEl, secondEl) => {
      const firstDate = new Date(firstEl.date_created)
      const secondDate = new Date(secondEl.date_created)
      return firstDate.getTime() - secondDate.getTime()
    })
  },
}
