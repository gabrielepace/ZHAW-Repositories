<template>
  <div>
    <div class="list-group">
      <CreateVersionForm
        v-for="(formId, index) in newVersions"
        :key="formId"
        class="list-group-item"
        @close="removeVersion(index)"
        v-bind:id="formId"
        :disabled="!allowUserInput"
      />
      <addVersionElement
        type="button"
        class="list-group-item list-group-item-action"
        v-on:mouseover.native.prevent
        v-on:click.native="addVersion"
        :disabled="!allowUserInput"
      />
    </div>
  </div>
</template>

<script>
import CreateVersionForm from '@/components/forms/CreateVersionForm'
import AddVersionElement from '@/components/general/AddVersionElement'
import { mapGetters, mapActions } from 'vuex'
import * as types from '@/store/types.js'
export default {
  name: 'AddVersionsForm',

  data() {
    return {
      versionFormId: 1,
      newVersions: [],
      iterator: 0,
      minOneUpload: false,
    }
  },

  props: ['startEmpty', 'submit'],

  components: { CreateVersionForm, AddVersionElement },

  methods: {
    ...mapActions({
      setCurrentVersion: types.SET_CURRENT_VERSION,
      setCurrentProgress: types.SET_CREATE_TRACK_CURRENT_STATE,
    }),

    addVersion() {
      this.newVersions.push(this.versionFormId)
      this.versionFormId++
    },

    removeVersion(index) {
      if (this.newVersions.length > 1 || this.startEmpty) {
        this.newVersions.splice(index, 1)
      }
    },
  },

  computed: {
    ...mapGetters({ createTrackProgress: types.CREATE_TRACK_CURRENT_STATE }),

    allowUserInput() {
      return this.createTrackProgress === 'USER_INPUT'
    },
  },

  watch: {
    submit(submit) {
      if (submit) {
        this.setCurrentProgress('SUBMIT_VERSIONS')
      }
    },
    createTrackProgress(progress) {
      switch (progress) {
        case 'SUBMIT_VERSIONS':
          this.formId = this.newVersions[this.iterator]
          console.log('setting current version id to: ' + this.formId)
          this.iterator++
          this.setCurrentVersion({ id: this.formId })
          break

        case 'VERSION_SUCCESS':
          console.log('version_success')
          this.minOneUpload = true
          /* falls through */
        
        case 'VERSION_FAILED':
          // Are there more versions to upload?
          if (this.iterator <= this.newVersions.length - 1) {
            this.setCurrentProgress('SUBMIT_VERSIONS')
          }
          // if not and at least one version was uploaded
          else {
            if (this.minOneUpload) {
              this.setCurrentProgress('ALL_VERSIONS_UPLOADED')
            }
            // if no version was uploaded
            else {
              this.setCurrentProgress('NO_VERSIONS_UPLOADED')
            }
            this.$emit('all_versions_done')
          }
          break

        case 'USER_INPUT':
          this.iterator = 0
          break
      }
    },
  },
  created() {
    if (!this.startEmpty) {
      this.newVersions = [1]
      this.versionFormId = 2
    }
  },
}
</script>

<style scoped></style>
