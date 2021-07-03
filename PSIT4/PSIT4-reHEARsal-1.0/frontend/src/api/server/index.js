import axios from 'axios'

//let _sessionHash
let _instance
let _hash

export class ApiClient {
  constructor(hash) {
    this.hash = hash
    this.initAxios()
  }

  static setHash(hash) {
    _hash = hash
  }

  static getInstance() {
    if (!_instance) {
      if (!_hash) {
        throw 'Error while creating ApiClient instance. _hash needs to be set (statically) before!'
      }
      _instance = new ApiClient(_hash)
    }

    return _instance
  }

  initAxios() {
    axios.defaults.xsrfCookieName = 'csrf_token'
    axios.defaults.xsrfHeaderName = 'X-CSRFToken'
    const sessionHash = this.hash
    axios.defaults.baseURL = `${process.env.VUE_APP_API_ADDRESS}sessions/${sessionHash}`
  }

  addServerAddressToPaths(track) {
    track.versions.forEach(function(version) {
      version.audiofile = process.env.VUE_APP_SERVER_ADDRESS + version.audiofile
      version.amplispectrogram =
        process.env.VUE_APP_SERVER_ADDRESS + version.amplispectrogram
    })
    return track
  }

  // these methods return data, not promises
  async getTrackById(id) {
    if (id === 0) {
      return { id: 0 }
    }
    return axios.get('tracks/' + id).then(response => {
      return this.addServerAddressToPaths(response.data)
    })
  }

  async fetchSession() {
    return (
      axios
        .get()
        // adds the server address to the audiofile and amplispec links
        .then(response => {
          let session = response.data
          session.tracks.forEach(track => {
            track = this.addServerAddressToPaths(track)
          })
          return session
        })
    )
  }

  async fetchTracks() {
    return axios.get('tracks').then(response => {
      response.data.forEach(track => {
        track = this.addServerAddressToPaths(track)
      })
      return response.data
    })
  }

  async fetchCrsfToken() {
    return axios.get('csrf').then(response => response.data.csrf_token)
  }

  // returns new track object or null object
  async addTrack(trackName, sessionId) {
    let bodyFormData = new FormData()
    bodyFormData.set('name', trackName)
    bodyFormData.set('session', sessionId)

    return axios({
      method: 'post',
      url: 'tracks/add',
      data: bodyFormData,
    }).then(response => {
      if (response.data.status > 201) {
        return this.getTrackById(0)
      } else {
        return this.getTrackById(response.data.pk)
      }
    })
  }

  async createComment(trackId, versionId, text, who, sessionId) {
    let bodyFormData = new FormData()
    bodyFormData.set('text', text)
    bodyFormData.set('who', who)
    bodyFormData.set('session', sessionId)

    return axios({
      method: 'post',
      url: 'tracks/' + trackId + '/versions/' + versionId + '/comments/add',
      data: bodyFormData,
    }).then(response => response.data.status)
  }

  async fetchComments(trackId, versionId) {
    return axios
      .get('tracks/' + trackId + '/versions/' + versionId + '/comments')
      .then(response => response.data)
  }

  async createVersion(versionInfo, versionFile, trackId, sessionId) {
    let bodyFormData = new FormData()
    bodyFormData.set('track', trackId)
    bodyFormData.set('audiofile', versionFile)
    bodyFormData.set('info', versionInfo)
    bodyFormData.set('session', sessionId)

    return axios({
      method: 'post',
      url: `/tracks/${trackId}/versions/add`,
      data: bodyFormData,
    }).then(response => response.data.status)
  }

  async deleteTrack(trackId) {
    return axios({
      method: 'post',
      url: `tracks/${trackId}/delete/`,
    })
      .then(response => {
        return response.data.status == 200
      })
      .catch(error => {
        throw `error while retrieving data from tracks/${trackId}/delete/`
      })
  }
}
