<template src="./overview.html"></template>
<style src="./overview.css"></style>

<script>
    import CreateModal from "../createModal/CreateModal";
    import Semester from "../semester/Semester";
    import Modulegroup from "../modulegroup/Modulegroup";
    import modulService from "../../services/modulService";

    export default {
        name: 'overview',
        components: {
            CreateModal,
            Semester,
            Modulegroup
        },
        data() {
            return {
                editItem: {},
                semesters: [],
                modulegroups: []
            }
        },
        mounted: function () {
            let token = localStorage.EasyCTS_token;
            !token ? this.$router.push({name: 'login'}) : this.loadData();
        },
        methods: {
            loadData: function () {
                let that = this;
                modulService.getModules().then(response => {
                    that.semesters = this.getSemesterData(response.data.semesterDTOList);
                    that.modulegroups = this.getModulegroupData(response.data.moduleGroupList);
                });
            },
            onEdit: function (element, semester) {
                this.editItem = element;
                this.editItem.semester = semester;
            },
            onClose: function () {
                this.editItem = {};
            },
            // functions to calc avg of each element
            getSemesterData: function (list) {
                list.forEach(semester => {
                    semester.enrollments.forEach(item => {
                        item.avg = this.calcModuleAvg(item);
                    });
                    semester.avg = this.calcListAvg(semester.enrollments);
                });

                return list;
            },
            getModulegroupData: function (mgList) {
                let groups = [];
                let modules = this.getAllModules();
                for (let i = 0; i < mgList.length; i++) {
                    let name = mgList[i];
                    let moduleList = modules.filter(module => module.moduleGroupName === name);
                    let avg = this.calcListAvg(moduleList);
                    groups.push({name: name, avg: avg});
                }

                return groups;
            },
            getAllModules: function () {
                let moduleList = [];
                for (let i = 0; i < this.semesters.length; i++) {
                    moduleList = moduleList.concat(this.semesters[i].enrollments);
                }
                return moduleList;
            },
            // calc functions
            calcListAvg: function (list) {
                let total = 0, ects = 0;
                for (let i = 0; i < list.length; i++) {
                    let el = list[i];
                    if (!el.dispensed) {
                        ects += el.ects;
                        total += el.avg * el.ects;
                    }
                }
                let avg = (total / ects);
                return avg || 0;
            },
            calcModuleAvg: function (module) {
                let total = 0, totalWeighting = 0;
                for (let i = 0; i < module.marks.length; i++) {
                    let el = module.marks[i];
                    totalWeighting += el.weighting;
                    total += el.mark * el.weighting;
                }
                return (total / totalWeighting) || 0;
            }
        }
    };
</script>
