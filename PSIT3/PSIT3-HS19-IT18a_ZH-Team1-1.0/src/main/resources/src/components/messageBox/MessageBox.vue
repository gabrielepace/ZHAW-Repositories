<template src="./messageBox.html"></template>
<style src="./messageBox.css"></style>

<script>
    export default {
        props: {
            message: {
                type: String,
                default: 'primary'
            },
            state: String,
            countdown: {
                type: Boolean,
                default: false
            },
        },
        data() {
            return {
                dismissSecs: 10,
                dismissCountDown: 0,
                showDismissibleAlert: true
            }
        },
        mounted: function () {
            this.showAlert();
        },
        methods: {
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            showAlert() {
                this.dismissCountDown = this.dismissSecs
            }
        }
    };
</script>
