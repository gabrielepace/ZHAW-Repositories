<template>
  <div>
    <div class="row justify-content-end">
      <div class="col-sm-1">
        <div class="remove-button float-right" v-on:click="$emit('close')">
          <svg
            class="bi bi-x-circle-fill"
            width="1em"
            height="1em"
            viewBox="0 0 16 16"
            fill="currentColor"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              d="M16 8A8 8 0 110 8a8 8 0 0116 0zm-4.146-3.146a.5.5 0 00-.708-.708L8 7.293 4.854 4.146a.5.5 0 10-.708.708L7.293 8l-3.147 3.146a.5.5 0 00.708.708L8 8.707l3.146 3.147a.5.5 0 00.708-.708L8.707 8l3.147-3.146z"
              clip-rule="evenodd"
            />
          </svg>
        </div>
      </div>
    </div>
    <form>
      <div class="form-group row">
        <label for="customFile" class="col-sm-2 col-form-label"
          >Audio File</label
        >
        <div class="col-sm-10">
          <div class="custom-file">
            <input
              type="file"
              :class="[
                'custom-file-input',
                { 'is-invalid': audioFileInputIsInvalid },
              ]"
              id="customFile"
              @change="processFile($event)"
              :disabled="disabled"
            />
            <label class="custom-file-label" for="customFile">{{
              vFile.name
            }}</label>
          </div>
        </div>
      </div>
      <div class="form-group row">
        <label for="versionInfoTextArea" class="col-sm-2 col-form-label"
          >Version Info</label
        >
        <div class="col-sm-10">
          <textarea
            v-model="vInfo"
            :class="[
              'form-control',
              { 'is-invalid': versionInfoInputIsInvalid },
            ]"
            id="versionInfoTextArea"
            :disabled="disabled"
          ></textarea>
          <div class="invalid-feedback">
            Add some information about this version
          </div>
        </div>
      </div>
    </form>
    <div class="uploading" v-show="uploading">
      <div class="d-flex justify-content-center">
        <div class="spinner-border" role="status">
          <span class="sr-only">Loading...</span>
        </div>
      </div>
    </div>
    <div class="upload-success" v-if="versionUploaded">
        <ResponseIcon type="success" showTime="700" />
    </div>
    <div class="upload-error" v-if="error">
      <ResponseIcon type="error" showTime="700" />
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import * as types from '@/store/types.js'
import ResponseIcon from '@/components/forms/ResponseIcon'
export default {
  name: 'CreateVersionForm',
  data() {
    return {
      vInfo: '',
      vFile: '',
      done: false,
      submitted: false,
      uploadAttempted: false,
    }
  },
  components: { ResponseIcon },
  props: {
    id: Number,
  },
  methods: {
    ...mapActions({
      createVersion: types.ADD_VERSION,
      setCurrentProgress: types.SET_CREATE_TRACK_CURRENT_STATE,
    }),

    processFile() {
      this.vFile = event.target.files[0]
    },

    submitVersion() {
      console.log('submitting version ' + this.id)
      this.createVersion({
        versionInfo: this.vInfo,
        versionFile: this.vFile,
        sessionId: this.currentSessionId,
      })
      this.uploadAttempted = true
    },

    edit() {
      this.disabled = false
    },
  },
  computed: {
    ...mapGetters({
      currentVersion: types.CURRENT_VERSION,
      createTrackProgress: types.CREATE_TRACK_CURRENT_STATE,
      loading: types.LOADING,
      success: types.SUCCESS,
      currentSessionId: types.CURRENT_SESSION_ID,
    }),
    audioFileInputIsInvalid() {
      return this.vFile === '' && this.submitted
    },
    versionInfoInputIsInvalid() {
      return this.vInfo === '' && this.submitted
    },
    formIsValid() {
      return this.vFile !== '' && this.vInfo !== ''
    },
    uploading() {
      return (
        this.submitted && this.loading && !this.done
      )
    },
    versionUploaded() {
      return (
        (
          this.uploadAttempted &&
          !this.loading &&
          this.success) ||
          this.done
      )
    },
    error() {
      return (
        this.submitted &&
        !this.loading &&
        !this.success &&
        this.uploadAttempted
      )
    },
    disabled() {
      return this.createTrackProgress !== 'USER_INPUT'
    }
  },
  watch: {
    currentVersion(version) {
      if (version.id === this.id) {
        if (this.formIsValid) {
          this.done = false
          this.uploadAttempted = false
          this.submitVersion()
        } else {
          this.setCurrentProgress('VERSION_FAILED')
        }
        this.submitted = true
      }
    },
    versionUploaded(uploaded) {
      if (uploaded) {
        console.log('version upload successfull ' + this.id)
        this.done = true
        this.setCurrentProgress('VERSION_SUCCESS')
      }
    },
    error(error) {
      if (error) {
        console.log('version upload failed ' + this.id)
        this.setCurrentProgress('VERSION_FAILED')
      }
    },
  },
}
</script>

<style scoped>
.remove-button {
  padding: 0em 0em 1em 0em;
}

.spinner-border {
  display: flex;
  position: absolute;
  top: 50%;
}

.upload-success {
  display: flex;
  position: absolute;
  top: 48%;
  left: 48%;
  border-radius: 50%;
  width: 50px;
  height: 50px;
}

.upload-error {
  display: flex;
  position: absolute;
  top: 48%;
  left: 48%;
  border-radius: 50%;
  width: 50px;
  height: 50px;
}

.bi-exclamation {
  display: flex;
  position: absolute;
  top: 20%;
}

.bi-check {
  display: flex;
  position: absolute;
  top: 25%;
  left: 18%;
}
</style>
