import axios from 'axios'

// URLs
const save_url = 'http://localhost:8081/module';
const get_url = 'http://localhost:8081/modules';
const delete_url = 'http://localhost:8081/module/delete/';

export default {
    getModules() {
        let token = localStorage.EasyCTS_token;
        return axios.post(get_url, token,
            {headers: {'Content-Type': 'text/plain'}},
            {crossdomain: true});
    },
    saveModule(moduleData, onSuccess, onFail) {
        moduleData.token = localStorage.EasyCTS_token;
        axios.post(save_url, moduleData, {crossdomain: true})
            .then(() => onSuccess())
            .catch((e) => onFail(e));
    },
    deleteModule(enrollmentId, onSuccess) {
        let url = delete_url + enrollmentId;
        axios.post(url, {crossdomain: true})
            .then(() => onSuccess());
    }
}