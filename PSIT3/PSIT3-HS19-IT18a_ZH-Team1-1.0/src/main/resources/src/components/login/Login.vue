<template src="./login.html"></template>
<style src="./login.css"></style>

<script>
    import userService from './../../services/userService.js'
    import {validationMixin} from 'vuelidate'
    import {email, minLength, required} from 'vuelidate/lib/validators'
    import MessageBox from "../messageBox/MessageBox.vue";

    export default {
        name: 'register',
        mixins: [validationMixin],
        components: {MessageBox},
        mounted: function () {
            let token = localStorage.EasyCTS_token;
            token ? this.$router.push({name: 'overview'}) : '';
        },
        data() {
            return {
                form: {
                    mail: null,
                    password: null,
                },
                message: {
                    text: "",
                    state: "danger"
                }
            }
        },
        validations: {
            form: {
                mail: {
                    required,
                    email
                },
                password: {
                    required,
                    minLength: minLength(7)
                }
            }
        },
        methods: {
            onLogin: function () {
                const onSuccess = () => this.$router.push({name: 'overview'});
                const onFail = (msg) => this.message.text = msg;

                let formData = {username: this.form.mail, password: this.form.password};
                this.resetMessage();
                userService.login(formData, onSuccess, onFail);
            },
            onRegister: function () {
                this.resetMessage();
                this.$router.push({name: 'register'});
            },
            resetMessage: function () {
                this.message.text = '';
            }
        }
    }
</script>
