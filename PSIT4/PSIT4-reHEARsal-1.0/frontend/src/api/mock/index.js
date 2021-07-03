import tracks from './data/tracks'
import track from './data/trackById'

const fetch = (mockData, time = 0) => {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(mockData)
    }, time)
  })
}

export default {
  fetchTracks() {
    return fetch(tracks, 1000) // wait 0.5s before returning the tracks
  },
  getTrackById(id) {
    if (id === 0) {
      return { id: 0 }
    }
    return fetch(track, 1000)
  },
  addTrack(trackName, sessionId) {
    if (sessionId < 0) {
      setTimeout(1000)
      return { id: 0 }
    }
    return fetch(track, 1000)
  },
  async createVersion(versionInfo, versionFile, trackId) {
    let status_code = 201
    if (versionInfo === 'FAIL') {
      status_code = -1
    }
    setTimeout(() => {
      return status_code
    }, 1000)
  },
  createTestSession() {
    return undefined
  },
}
