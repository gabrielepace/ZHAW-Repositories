import { mapGetters } from 'vuex'
import * as types from '../../store/types'

export const sessionMixin = {
  computed: {
    ...mapGetters({ getSessionHash: types.CURRENT_SESSION_HASH }),
  },
  created() {
    if (
      !this.getSessionHash ||
      this.$route.params.hash != this.getSessionHash
    ) {
      this.$store.dispatch(types.SET_CURRENT_SESSION, this.$route.params.hash)
    }
  },
}
