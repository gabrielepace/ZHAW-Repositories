import client from 'client-api'
import mock_client from 'mock-api'

const defaultSessionId = 8
const testTrackId = 1

function createRandomName() {
  return 'xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = (Math.random() * 16) | 0,
      v = c == 'x' ? r : (r & 0x3) | 0x8
    return v.toString(16)
  })
}

describe('client api call', function() {
  test('getTrackById should return Track', async () => {
    let track = await client.getTrackById(testTrackId)
    expect(track.id).toBe(testTrackId)
  })

  test('getTrackById paths should inculde serveraddress', async () => {
    let track = await client.getTrackById(testTrackId)
    expect(track.versions[0].audiofile).toMatch(
      process.env.VUE_APP_SERVER_ADDRESS
    )
  })

  test('fetchTracks paths should inculde serveraddress', async () => {
    let tracks = await client.fetchTracks()
    expect(tracks[0].versions[0].audiofile).toMatch(
      process.env.VUE_APP_SERVER_ADDRESS
    )
  })

  test('addTrack returns a track object', async () => {
    let trackName = createRandomName()
    let track = await client.addTrack(trackName, defaultSessionId)
    expect(track.id).toBeGreaterThan(0)
  })
})

describe('mock client api call', function() {
  test('fetchTracks should return same format as real call', async () => {
    let realTracks = await client.fetchTracks()
    let mockTracks = await mock_client.fetchTracks()

    let properties = [
      'id',
      'name',
      'session',
      'parts',
      'versions',
      ['versions', 0, 'audiofile'],
    ]
    properties.forEach(prop => {
      expect(realTracks[0]).toHaveProperty(prop)
      expect(mockTracks[0]).toHaveProperty(prop)
    })
  })
  test('trackById should returns same Format as real call', async () => {
    let realTrack = await client.getTrackById(testTrackId)
    let mockTrack = await mock_client.getTrackById(testTrackId)

    let properties = [
      'id',
      'name',
      'session',
      'parts',
      'versions',
      ['versions', 0, 'audiofile'],
    ]

    properties.forEach(prop => {
      expect(realTrack).toHaveProperty(prop)
      expect(mockTrack).toHaveProperty(prop)
    })
  })
  test('addTrack should return same success code', async () => {
    let realResponse = await client.addTrack(
      createRandomName(),
      defaultSessionId
    )
    let mockRepsonse = await mock_client.addTrack(
      createRandomName(),
      defaultSessionId
    )

    let properties = ['id', 'name', 'session', 'parts', 'versions']

    properties.forEach(prop => {
      expect(realResponse).toHaveProperty(prop)
      expect(mockRepsonse).toHaveProperty(prop)
    })
  })
  test('addTrack should return same error code', async () => {
    let realResponse = await client.addTrack(createRandomName(), -1)
    let mockRepsonse = await mock_client.addTrack(createRandomName(), -1)

    expect(realResponse).toHaveProperty('id', 0)
    expect(mockRepsonse).toHaveProperty('id', 0)

    let properties = ['name', 'session', 'parts', 'versions']
    properties.forEach(prop => {
      expect(realResponse).not.toHaveProperty(prop)
      expect(mockRepsonse).not.toHaveProperty(prop)
    })
  })
  test('createVersion should return correct success code', async () => {
    let mockRepsonse = await mock_client.createVersion(
      'TEST',
      'path',
      testTrackId
    )

    expect(mockRepsonse).toBe(201)

    mockRepsonse = await mock_client.createVersion('FAIL', 'path', testTrackId)

    expect(mockRepsonse).toBe(-1)
  })
})
