<template>
  <div>
    <div class="col comment-form">
      <input type="text" v-model="author" placeholder="What's your name?" />
      <textarea
        type="text"
        v-model="content"
        placeholder="Enter your comment here..."
      ></textarea>
      <button class="btn btn-primary btn-sm" @click="postComment">
        Add Comment
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      author: '',
      content: '',
    }
  },

  methods: {
    postComment() {
      console.log('Emitting')
      this.$emit('posted', this.author, this.content)
      this.author = ''
      this.content = ''
    },
  },
}
</script>

<style scoped>
.comment-form {
  padding-bottom: 0.5em;
}
* {
  padding: 0;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
  font-size: 14px;
}

textarea {
  width: 100%;
  margin: 8px 0px;
  border: 0px solid;
  height: 70px;
  font-family: Verdana, Helvetica, sans-serif;
  padding: 5px;
  box-shadow: 2px 2px 2px 2px #dddddd;
}

input {
  border: 0px solid #ccc;
  font-family: Verdana, Helvetica, sans-serif;
  padding: 5px;
  margin: 10px 10px 0px 0px;
  box-border: 1px 1px 1px 1px #dddddd;
}
</style>
