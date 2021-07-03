<template>
  <div id="app-messages">
    <app-error-messages
      :errors="errorMessages"
      :deleteErrorFn="deleteErrorMessage"
    ></app-error-messages>
    <app-success-messages :successes="successMessages"></app-success-messages>
  </div>
</template>

<script>
import ErrorMessages from '@/components/general/ErrorMessages'
import SuccessMessages from '@/components/general/SuccessMessages'
import types from '@/store/types'
import { serverBus } from '@/main'
import { mapState } from 'vuex'
export default {
  data() {
    return {
      errorMessages: [],
      successMessages: [],
    }
  },
  components: {
    'app-error-messages': ErrorMessages,
    'app-success-messages': SuccessMessages,
  },
  methods: {
    addErrorMessage(message) {
      this.errorMessages.push(message)
    },
    addSuccessMessage(message) {
      const index = this.successMessages.push(message) - 1
      setTimeout(index => {
        this.deleteSuccessMessage(index)
      }, 600)
    },
    deleteErrorMessage(index) {
      this.errorMessages.splice(index, 1)
    },
    deleteSuccessMessage(index) {
      this.successMessages.splice(index, 1)
    },
  },
  created() {
    serverBus.$on('add-error-message', message => this.addErrorMessage(message))
    serverBus.$on('add-success-message', message =>
      this.addSuccessMessage(message)
    )
  },
}
</script>

<style scoped>
#app-messages {
  margin-top: 15px;
}
</style>
