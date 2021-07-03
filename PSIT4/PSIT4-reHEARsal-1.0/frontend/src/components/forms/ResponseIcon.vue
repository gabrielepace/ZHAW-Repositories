<template>
  <div>
    <transition name="fade">
      <div v-if="showIcon">
        <div class="upload success" v-if="type === 'success'">
          <span class="sign bg-success">
            <svg
              class="bi bi-check d-flex justify-content-center"
              width="2em"
              height="2em"
              viewBox="0 0 16 16"
              fill="white"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                fill-rule="evenodd"
                d="M13.854 3.646a.5.5 0 010 .708l-7 7a.5.5 0 01-.708 0l-3.5-3.5a.5.5 0 11.708-.708L6.5 10.293l6.646-6.647a.5.5 0 01.708 0z"
                clip-rule="evenodd"
              />
            </svg>
          </span>
        </div>
        <div class="upload error" v-if="type === 'error'">
          <span class="sign bg-danger d-flex justify-content-center">
            <svg
              class="bi bi-exclamation"
              width="2em"
              height="2em"
              viewBox="0 0 16 16"
              fill="white"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M7.002 11a1 1 0 112 0 1 1 0 01-2 0zM7.1 4.995a.905.905 0 111.8 0l-.35 3.507a.552.552 0 01-1.1 0L7.1 4.995z"
              />
            </svg>
          </span>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'ResponseIcon',
  props: ['type', 'showTime'],
  data() {
    return {
      showIcon: false,
    }
  },
  created() {
    this.showIcon = true
    setTimeout(() => (this.showIcon = false), this.showTime)
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.fade-leave-active {
  animation: fade-out 0.4s;
}

@keyframes fade-out {
  0% {
    opacity: 100%;
  }
  100% {
    opacity: 0%;
  }
}

.sign {
  display: flex;
  position: absolute;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  background: darkseagreen;
}

.bi-exclamation {
  display: flex;
  position: absolute;
  top: 20%;
  left: 15%;
}

.bi-check {
  display: flex;
  position: absolute;
  top: 25%;
  left: 18%;
}
</style>
