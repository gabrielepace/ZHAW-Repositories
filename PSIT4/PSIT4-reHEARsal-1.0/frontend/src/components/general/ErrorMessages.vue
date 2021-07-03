<template>
  <div class="row">
    <div class="col-lg-12 col-md-12 col-xs-12">
      <div
        v-for="(error, index) in errors"
        :key="index"
        class="alert alert-danger"
        role="alert"
        @click="onMessageClick(index)"
      >
        {{ error }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    errors: { type: Array, required: true },
    deleteErrorFn: { type: Function, required: true },
  },
  methods: {
    onMessageClick(index) {
      this.deleteErrorFn(index)
    },
  },
}
</script>
