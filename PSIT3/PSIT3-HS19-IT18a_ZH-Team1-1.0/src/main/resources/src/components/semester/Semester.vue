<template src="./semester.html"></template>
<style src="./semester.css"></style>

<script>
    import CreateModal from "../createModal/CreateModal";
    import pencil from "../../../assets/pencil-alt-solid.svg"
    import trash from "../../../assets/trash-alt-solid.svg"
    import modalService from './../../services/modulService.js'

    export default {
        components: {
            CreateModal,
        },
        props: {
            ix: Number,
            avg: Number,
            enrollments: Array,
            onEdit: Function,
        },
        data: function () {
            return {
                editItem: {},
                pencil: pencil,
                trash: trash
            }
        },
        methods: {
            editModule: function (element) {
                this.onEdit(element, this.ix);
            },
            onDelete: function (element) {
                const onSuccess = () => this.$router.go();
                modalService.deleteModule(element.enrollmentId, onSuccess);
            }
        }
    }
</script>
