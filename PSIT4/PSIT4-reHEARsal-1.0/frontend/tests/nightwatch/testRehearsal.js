'use strict'

const url = 'http://dev.rehearsal.ch'
module.exports = {
  'Test reHEARsal WebApp HomePage': function(browser) {
    browser
      .url(url)
      .waitForElementVisible('body')
      .assert.titleContains('frontend')
      .useXpath()
      .assert.containsText("//h1[contains(.,'reHEARsal')]", 'reHEARsal')
      .assert.containsText(
        "//p[contains(.,'Please send an email to sales@rehearsal.ch to acquire an account')]",
        'Please send an email to sales@rehearsal.ch to acquire an account'
      )
      .status()
  },
  'Test reHEARsal WebApp Links': function(browser) {
    browser
      .url(url)
      .assert.titleContains('frontend')
      .click("//a[contains(text(),'My tracks')]")
      .url(url + '/sessions/impractical-freezable/')
      .assert.containsText("//a[contains(text(),'My tracks')]", 'My tracks')
      .click("//a[contains(@href, '/addTrack')]")
      .url(url + '/sessions/impractical-freezable/addTrack')
      .assert.containsText(
        "//a[contains(text(),'Upload track')]",
        'Upload track'
      )
      .end()
  },
}
