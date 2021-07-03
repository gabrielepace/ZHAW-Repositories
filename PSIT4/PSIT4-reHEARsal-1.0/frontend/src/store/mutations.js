import * as types from './types.js'

export default {
  [types.MUTATE_LOADING]: (state, status) => {
    state.loading = status
  },
  [types.MUTATE_SUCCESS]: (state, status) => {
    state.success = status
  },
  [types.MUTATE_TRACKS]: (state, tracks) => {
    state.tracks = tracks
  },
  [types.MUTATE_ADD_TO_TRACKS]: (state, track) => {
    state.tracks.push(track)
  },
  [types.MUTATE_CURRENT_SESSION]: (state, session) => {
    state.currentSession = {
      id: session.id,
      name: session.name,
      date: session.date,
      description: session.description,
      hash_string: session.hash_string,
      track_count:	session.track_count,
      track_length_sum:	session.track_length_sum
    }
  },
  [types.MUTATE_CURRENT_TRACK]: (state, track) => {
    state.currentTrack = track
  },
  [types.MUTATE_CURRENT_VERSION]: (state, version) => {
    state.currentVersion = version
  },
  [types.MUTATE_PLAYING_VERSION]: (state, id) => {
    state.playingVersionId = id
  },
  [types.MUTATE_CREATE_TRACK_CURRENT_STATE]: (state, newState) => {
    state.createTrackCurrentState = newState
  },
  [types.MUTATE_ERROR]: (state, error) => {
    state.error = error
  },
  [types.MUTATE_EMPTY_INPUT]: (state, isEmpty) => {
    state.emptyInput = isEmpty
  },
  [types.MUTATE_NETWORK_ERROR]: (state, networkErrorOccured) => {
    state.networkError = networkErrorOccured
  },
}
