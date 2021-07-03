<template>
  <div class="row">
    <div class="col-lg-12 col-md-12 col-xs-12">
      <div
        v-for="(success, index) in successes"
        class="alert alert-success"
        role="alert"
        :key="index"
      >
        {{ success }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['successes'],
}
</script>
