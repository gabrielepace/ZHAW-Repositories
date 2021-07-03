<template src="./navbar.html"></template>
<style src="./navbar.css"></style>

<script>
    import logo from "../../../assets/logo.png"
    import user from "../../../assets/user-solid.svg"
    import userService from './../../services/userService.js'

    export default {
        data: function () {
            return {
                logo: logo,
                user: user
            }
        },
        computed: {
            showMenu: () => {
                return localStorage.EasyCTS_token !== undefined
            }
        },
        methods: {
            onLogout: function () {
                const onSuccess = () => this.$router.push({name: 'login'});
                userService.logout(onSuccess);
            }
        }
    }
</script>
