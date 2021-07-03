import Vue from 'vue'
import VueRouter from 'vue-router'
import InvalidHash from '../views/InvalidHash.vue'
import Tracks from '../views/Tracks.vue'
import AddTrack from '../views/AddTrack.vue'
import Versions from '../views/Versions.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: InvalidHash, name: 'invalidHash' },
  { path: '/test', component: Tracks },
  { path: '/sessions/:hash', component: Tracks },
  { path: '/sessions/:hash/tracks/:id', name: 'versions', component: Versions },
  { path: '/sessions/:hash/addTrack', name: 'addTrack', component: AddTrack },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

router.beforeEach((to, from, next) => {
  if (!to.params.hash || to.params.hash == 'undefined') {
    if (to.name != 'invalidHash') {
      next({ name: 'invalidHash' })
    }
  }

  next()
})

export default router
