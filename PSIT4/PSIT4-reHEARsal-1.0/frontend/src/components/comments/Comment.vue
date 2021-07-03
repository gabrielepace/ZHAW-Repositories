<template>
  <div class="comment-element">
    <div class="row">
      <div class="col-3 author-pos">
        <h4 class="comment author">{{ comment.who }}</h4>
      </div>
      <p class="comment dateOfPost">{{ postDate }}</p>
    </div>
    <p>{{ comment.text }}</p>
  </div>
</template>

<script>
export default {
  name: 'CommentPost',
  props: { comment: Object },
  computed: {
    postDate() {
      return new Date(this.comment.when).toLocaleString()
    },
  },
}
</script>

<style scoped>
.comment-element {
  font-size: 14px;
  padding: 0em 0em 0em 0.5em;
}
.row {
  height: 2em;
}
.author {
  float: left;
  font-size: 14px;
  font-weight: bold;
}
.content {
  font-size: 14px;
}
</style>
