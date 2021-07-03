<template>
  <div>
    <VersionDisplay :version="version" />
    <CommentSection :version="version" />
  </div>
</template>

<script>
import VersionDisplay from '@/components/versions/VersionDisplay'
import CommentSection from '@/components/comments/CommentSection'
import { serverBus } from '@/main'

export default {
  props: ['version'],
  components: {
    VersionDisplay,
    CommentSection,
  },
}
</script>

<style scoped></style>
