<template src="./register.html"></template>
<style src="./register.css"></style>

<script>
    import userService from './../../services/userService.js'
    import {validationMixin} from 'vuelidate'
    import {email, minLength, required, sameAs} from 'vuelidate/lib/validators'
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
                    confirm: null,
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
                },
                confirm: {
                    required,
                    minLength: minLength(7),
                    sameAsPassword: sameAs('password')
                }
            }
        },
        methods: {
            onRegister: function () {
                const onSuccess = () => this.$router.push({name: 'overview'});
                const onFail = (msg) => this.message.text = msg;

                let formData = {
                    username: this.form.mail,
                    password: this.form.password,
                    passwordConfirmation: this.form.confirm
                };
                userService.register(formData, onSuccess, onFail);
            },
            onLogin: function () {
                this.$router.push('login');
            }
        }
    }
</script>
