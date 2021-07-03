<template>
  <div class="card">
    <div class="card-header">
      <button
        class="btn btn-link btn-block text-left"
        type="button"
        @click="showComments = !showComments"
      >
        <svg
          v-if="showComments"
          class="bi bi-chevron-up"
          width="1em"
          height="1em"
          viewBox="0 0 16 16"
          fill="currentColor"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M7.646 4.646a.5.5 0 01.708 0l6 6a.5.5 0 01-.708.708L8 5.707l-5.646 5.647a.5.5 0 01-.708-.708l6-6z"
            clip-rule="evenodd"
          />
        </svg>
        <svg
          v-else
          class="bi bi-chevron-down"
          width="1em"
          height="1em"
          viewBox="0 0 16 16"
          fill="currentColor"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            fill-rule="evenodd"
            d="M1.646 4.646a.5.5 0 01.708 0L8 10.293l5.646-5.647a.5.5 0 01.708.708l-6 6a.5.5 0 01-.708 0l-6-6a.5.5 0 010-.708z"
            clip-rule="evenodd"
          />
        </svg>

        comments
      </button>
    </div>
    <div id="collapse" :class="{ collapse: !showComments }">
      <div class="card-body">
        <div class="current-comments" v-if="!(comments === undefined)">
          <div v-for="comment in comments" :key="comment.id">
            <Comment :comment="comment" />
            <hr class="solid" />
          </div>
        </div>
        <AddComment @posted="postComment" />
      </div>
    </div>
  </div>
</template>

<script>
import Comment from '@/components/comments/Comment'
import AddComment from '@/components/comments/AddComment'
import { mapGetters, mapActions } from 'vuex'
import * as types from '@/store/types.js'
import { ApiClient } from '@/api/server/index'

export default {
  components: { AddComment, Comment },
  props: ['version'],
  data() {
    return {
      comments: undefined,
      showComments: false,
      apiClient: ApiClient.getInstance(),
      expanded: true,
    }
  },

  methods: {
    ...mapActions({ addComment: types.ADD_COMMENT }),

    postComment(author, content) {
      this.addComment({
        who: author,
        text: content,
        trackId: this.version.track,
        versionId: this.version.id,
      })
    },

    async fetchComments() {
      this.comments = await this.apiClient.fetchComments(
        this.version.track,
        this.version.id
      )
    },
  },

  computed: {
    ...mapGetters({
      loading: types.LOADING,
      success: types.SUCCESS,
    }),

    commentPostSuccess() {
      return !this.loading && this.success
    },
  },

  watch: {
    commentPostSuccess(state) {
      if (state) {
        this.fetchComments()
      }
    },
  },

  created() {
    this.fetchComments()
  },
}
</script>

<style scoped>
.card {
  width: 700px;
  margin-top: -1px;
  margin-left: -1px;
  border-radius: 0 !important;
  margin-bottom: 15px;
}
.card-header {
  background-color: #6c757d;
  padding: 0;
  color: #fff;
  border-radius: 0 !important;
}
.card-header .btn-link {
  color: #fff;
  font-size: 0.8rem;
  line-height: 1rem;
}

.card-body {
  padding: 6px;
}
</style>
