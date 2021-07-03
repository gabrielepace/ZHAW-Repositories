'use strict'

// eslint-disable-next-line no-global-assign
__dirname = '/home/appveyor/projects/psit4-rehearsal-tivmx'
const url = 'http://dev.rehearsal.ch'

module.exports = {
  'Create new Track with 1 Version': function(browser) {
    browser
      .url(url)
      .useXpath()
      .click("//a[contains(text(),'Upload track')]")
      .assert.containsText(
        "//a[contains(text(),'Upload track')]",
        'Upload track'
      )
      .url(url + '/sessions/impractical-freezable/addTrack')
      .click("//input[@id='trackName']")
      .setValue(
        'xpath',
        "//input[@id='trackName']",
        'aNightwatch Test Track with 1 Version'
      )
      .click("//textarea[@id='versionInfoTextArea']")
      .setValue(
        'xpath',
        "//textarea[@id='versionInfoTextArea']",
        'Nightwatch Test Version 1 Rickroll'
      )
      .setValue(
        "//input[@id='customFile']",
        require('path').resolve(__dirname + '/media/test/rickroll.mp3')
      )
      .assert.containsText("(//button[@type='button'])[2]", 'Create Track')
      .end()
  },
  'Create new Track with 2 Versions': function(browser) {
    browser
      .url(url)
      .useXpath()
      .click("//a[contains(text(),'Upload track')]")
      .assert.containsText(
        "//a[contains(text(),'Upload track')]",
        'Upload track'
      )
      .url(url + '/sessions/impractical-freezable/addTrack')
      .click("//input[@id='trackName']")
      .setValue('xpath', "//input[@id='trackName']", 'Baby K')
      .click("//textarea[@id='versionInfoTextArea']")
      .setValue(
        'xpath',
        "//textarea[@id='versionInfoTextArea']",
        'Baby K - Da zero a cento'
      )
      .setValue(
        "//input[@id='customFile']",
        require('path').resolve(__dirname + '/media/test/blueDaBaDee.mp3')
      )
      .useCss()
      .click('.bi-plus')
      .useXpath()
      .click("(//textarea[@id='versionInfoTextArea'])[2]")
      .setValue(
        'xpath',
        "(//textarea[@id='versionInfoTextArea'])[2]",
        'Baby K - Playa'
      )
      .setValue(
        "(//input[@id='customFile'])[2]",
        require('path').resolve(__dirname + '/media/test/scatman.mp3')
      )
      .assert.containsText("(//button[@type='button'])[2]", 'Create Track')
      .end()
  },
  'Show alert not written Track title input': function(browser) {
    browser
      .url(url)
      .useXpath()
      .click("//a[contains(text(),'Upload track')]")
      .assert.containsText(
        "//a[contains(text(),'Upload track')]",
        'Upload track'
      )
      .url(url + '/sessions/impractical-freezable/addTrack')
      .click("(//button[@type='button'])[2]")
      .assert.containsText("(//button[@type='button'])[2]", 'Create Track')
      .waitForElementVisible(
        "//div[@id='add-track-form']/form/div/div/div",
        2000
      )
      .click("//div[@id='add-track-form']/form/div/div/div")
      .pause(1000)
      .end()
  },
  'Show alert not written Track title input, but with written Version': function(
    browser
  ) {
    browser
      .url(url)
      .useXpath()
      .click("//a[contains(text(),'Upload track')]")
      .assert.containsText(
        "//a[contains(text(),'Upload track')]",
        'Upload track'
      )
      .url(url + '/sessions/impractical-freezable/addTrack')
      .click("//textarea[@id='versionInfoTextArea']")
      .setValue(
        'xpath',
        "//textarea[@id='versionInfoTextArea']",
        'Nightwatch Test Version Alert'
      )
      .setValue(
        "//input[@id='customFile']",
        require('path').resolve(__dirname + '/media/test/scatman.mp3')
      )
      .click("(//button[@type='button'])[2]")
      .assert.containsText("(//button[@type='button'])[2]", 'Create Track')
      .waitForElementVisible(
        "//div[@id='add-track-form']/form/div/div/div",
        2000
      )
      .click("//div[@id='add-track-form']/form/div/div/div")
      .pause(1000)
      .end()
  },
  'Get Track': function(browser) {
    browser
      .url(url)
      .useXpath()
      .click("//a[contains(text(),'My tracks')]")
      .url(url + '/sessions/impractical-freezable/')
      .assert.containsText("//a[contains(text(),'My tracks')]", 'My tracks')
      .useCss()
      .assert.containsText(
        'div:nth-child(2) > .track > .track-artist',
        'Baby K'
      )
  },
  'Get Version of Track': function(browser) {
    browser
      .useCss()
      .click('div:nth-child(2) > .track')
      .click('div:nth-child(1) > div > .row .version-artist')
      .assert.containsText(
        'div:nth-child(1) > div > .row .version-artist',
        'Baby K - Da zero a cento'
      )
  },
  'Play & Stop first Version of Track': function(browser) {
    browser
      .useCss()
      .click('div:nth-child(1) > div > .row path')
      .pause(15000)
      .click('.bi-pause-fill')
  },
  'Write comment on first Version of Track': function(browser) {
    browser
      .useCss()
      .click('div:nth-child(1) > .card > .card-header > .btn')
      .assert.containsText(
        'div:nth-child(1) > .card > .card-header > .btn',
        'comments'
      )
      .click('div:nth-child(1) > .card > #collapse input')
      .setValue(
        'css selector',
        'div:nth-child(1) > .card > #collapse input',
        'Test Author'
      )
      .click('div:nth-child(1) > .card > #collapse textarea')
      .setValue(
        'css selector',
        'div:nth-child(1) > .card > #collapse textarea',
        'Test Comment!'
      )
      .assert.containsText(
        'div:nth-child(1) > .card > #collapse .btn',
        'Add Comment'
      )
  },
  'Cleanup created Tracks with Versions and Comments': function(browser) {
    browser
      .url(url + '/sessions/impractical-freezable')
      .useCss()
      .click('div:nth-child(1) > .track')
      .url(url + '/sessions/impractical-freezable/tracks/155')
      .end()
  },
}
