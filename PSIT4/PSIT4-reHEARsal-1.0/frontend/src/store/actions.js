import * as types from './types.js'
import { serverBus } from '../main'
import { ApiClient } from '../api/server/index'

let apiClient = () => {
  return ApiClient.getInstance()
}

export default {
  [types.SET_CURRENT_TRACK]: ({ commit }, track) => {
    commit(types.MUTATE_CURRENT_TRACK, track)
  },
  [types.SET_CURRENT_TRACK_BY_ID]: ({ state, commit }, trackId) => {
    const result = state.tracks.find(track => track.id == trackId)
    if (!result) {
      throw new Error(
        `Error while setting current track: trackId ${trackId} could not be found!`
      )
    }
    commit(types.MUTATE_CURRENT_TRACK, result)
  },
  [types.SET_CURRENT_VERSION]: ({ commit }, version) => {
    commit(types.MUTATE_CURRENT_VERSION, version)
  },
  [types.SET_PLAYING_VERSION]: ({ commit }, id) => {
    commit(types.MUTATE_PLAYING_VERSION, id)
  },
  [types.LOAD_TRACKS_ASYNC]: ({ commit }) => {
    commit(types.MUTATE_LOADING, true)
    apiClient()
      .fetchSession()
      .then(data => {
        commit(types.MUTATE_TRACKS, data.tracks)
        commit(types.MUTATE_LOADING, false)
      })
      .catch(error => {
        if (error.message === 'Network Error') {
          commit(types.MUTATE_NETWORK_ERROR, true)
          serverBus.$emit(
            'add-error-message',
            'error while loading tracks from server!'
          )
        }
      })
  },
  [types.ADD_COMMENT]: ({ commit, state }, values) => {
    const currentSessionId = state.currentSession.id
    if (values.text.length === 0) {
      commit(types.MUTATE_EMPTY_INPUT, true)
    }
    commit(types.MUTATE_SUCCESS, false)
    commit(types.MUTATE_LOADING, true)
    apiClient()
      .createComment(
        values.trackId,
        values.versionId,
        values.text,
        values.who,
        currentSessionId
      )
      .then(comment => {
        if (comment === 201) {
          commit(types.MUTATE_SUCCESS, true)
        }
        if (Object.keys(comment).length !== 1) {
          commit(types.MUTATE_EMPTY_INPUT, false)
        }
        commit(types.MUTATE_LOADING, false)
      })
  },
  [types.ADD_TRACK]: ({ commit, state }, trackName) => {
    commit(types.MUTATE_LOADING, true)
    apiClient()
      .addTrack(trackName, state.currentSession.id)
      .then(track => {
        if (track.id === 0) {
          commit(types.MUTATE_ERROR, true)
          serverBus.$emit(
            'add-error-message',
            'A Track with this name allready exists'
          )
        } else {
          commit(types.MUTATE_ADD_TO_TRACKS, track)
          commit(types.MUTATE_SUCCESS, true)
        }
        console.log('setting currentTrack')
        console.log(track)
        commit(types.MUTATE_CURRENT_TRACK, track)
        commit(types.MUTATE_LOADING, false)
      })
  },
  [types.SET_DEFAULT_STATE]: ({ commit }) => {
    commit(types.MUTATE_SUCCESS, false)
    commit(types.MUTATE_LOADING, false)
  },
  [types.ADD_VERSION]({ commit, state }, values) {
    commit(types.MUTATE_LOADING, true)
    const trackId = state.currentTrack.id
    apiClient()
      .createVersion(values.versionInfo, values.versionFile, trackId)
      .then(response => {
        if (response === 201) {
          commit(types.MUTATE_SUCCESS, true)
          commit(types.MUTATE_ERROR, false)
        } else {
          commit(types.MUTATE_SUCCESS, false)
          commit(types.MUTATE_ERROR, true)
          serverBus.$emit('add-error-message', 'error while adding version!')
        }
        commit(types.MUTATE_LOADING, false)
      })
  },
  [types.SET_CREATE_TRACK_CURRENT_STATE]({ commit }, newState) {
    commit(types.MUTATE_CREATE_TRACK_CURRENT_STATE, newState)
  },
  [types.SET_CURRENT_SESSION]: ({ commit }, hash) => {
    console.log('Session hash was changed')

    ApiClient.setHash(hash)
    commit(types.MUTATE_LOADING, true)
    commit(types.MUTATE_SUCCESS, false)
    commit(types.MUTATE_ERROR, false)
    apiClient()
      .fetchSession()
      .then(session => {
        commit(types.MUTATE_CURRENT_SESSION, session)
        commit(types.MUTATE_SUCCESS, true)
      })
      .catch(error => {
        serverBus.$emit(
          'add-error-message',
          'Session ' + hash + ' does not exist'
        )
        commit(types.MUTATE_ERROR, true)
        commit(types.MUTATE_LOADING, false)
      })
  },
  [types.INIT_STORE]: ({ commit }) => {
    commit(types.MUTATE_LOADING, true)
    return apiClient()
      .fetchSession()
      .then(session => {
        commit(types.MUTATE_CURRENT_SESSION, session)
        commit(types.MUTATE_TRACKS, session.tracks)
        commit(types.MUTATE_LOADING, false)
      })
  },
  async [types.DELETE_TRACK]({ commit }, trackId) {
    try {
      const result = await apiClient().deleteTrack(trackId)
      return result
    } catch (error) {
      console.log(
        `error while executing DELETE_TRACK action in store: ${error}`
      )
    }
  },
  [types.DELETE_TRACK_FROM_STORE]({ commit, state }, trackId) {
    const tracks = state.tracks
    const index = tracks.findIndex(track => {
      return track.id === trackId
    })
    if (index !== -1) {
      tracks.splice(index, 1)
      commit(types.MUTATE_TRACKS, tracks)
    }
  },
}
