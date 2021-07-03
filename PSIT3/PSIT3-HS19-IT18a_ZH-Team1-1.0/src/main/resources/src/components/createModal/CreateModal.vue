<template src="./createModal.html"></template>
<style src="./createModal.css"></style>

<script>
    import modalService from './../../services/modulService.js'
    import {minLength, minValue, required} from 'vuelidate/lib/validators'
    import {validationMixin} from 'vuelidate'

    export default {
        name: 'createModal',
        components: {},
        mixins: [validationMixin],
        props: {
            module: Object,
            onClose: Function
        },
        data() {
            return {
                enrollment: {}
            };
        },
        computed: {
            title: function () {
                return this.module.editItem.name === undefined ? "Modul erfassen" : "Modul editieren";
            }
        },
        validations: {
            enrollment: {
                name: {
                    required
                },
                semester: {
                    required,
                    minValue: minValue(1)
                },
                moduleGroupName: {
                    required,
                    minLength: minLength(1)
                },
                ects: {
                    required,
                    minValue: minValue(1)
                },
            }
        },
        methods: {
            onModalOpen: function () {
                this.enrollment = JSON.parse(JSON.stringify(this.module.editItem));

                if (this.enrollment.marks === undefined) {
                    this.enrollment.marks = [{mark: undefined, weighting: undefined}];
                }
            },
            onModalClose: function () {
                this.$v.enrollment.$reset();
                this.onClose();
            },
            saveModule: function () {
                let moduleData = this.enrollment;
                moduleData.modulegroup = moduleData.moduleGroupName;

                for (let i = 0; i < moduleData.marks.length; i++) {
                    let markObj = moduleData.marks[i];
                    markObj.mark = parseFloat(markObj.mark);
                }

                let callbackSuccess = function () {
                    this.$root.$emit('bv::toggle::modal', 'create-modal');
                    this.$v.enrollment.$reset();
                    this.$router.go();
                }.bind(this);

                let callbackFail = function () {
                    console.log("faild to save module");
                };

                modalService.saveModule(moduleData, callbackSuccess, callbackFail);
            },
            addMark: function () {
                this.enrollment.marks.push({mark: undefined, weighting: undefined});
                this.$forceUpdate();
            }
        }
    };
</script>
