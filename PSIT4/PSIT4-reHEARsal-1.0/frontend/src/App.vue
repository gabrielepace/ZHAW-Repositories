<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <router-link
              :to="`/sessions/${getSessionHash}/`"
              class="nav-link"
              active-class="active"
              >rehearsal</router-link>
          </li>
          <li class="nav-item active">
            <router-link
              :to="`/sessions/${getSessionHash}/`"
              class="nav-link"
              active-class="active"
              >My tracks</router-link>
          </li>
          <li class="nav-item">
            <router-link
              :to="`/sessions/${getSessionHash}/addTrack`"
              class="nav-link"
              active-class="active"
              >Upload track</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <div class="container-fluid">
      <app-messages></app-messages>
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
import Messages from './components/general/Messages'
import { mapGetters } from 'vuex'
import * as types from './store/types'

export default {
  components: {
    'app-messages': Messages,
  },
  computed: {
    ...mapGetters({ getSessionHash: types.CURRENT_SESSION_HASH }),
  },
}
</script>
<style scoped></style>
