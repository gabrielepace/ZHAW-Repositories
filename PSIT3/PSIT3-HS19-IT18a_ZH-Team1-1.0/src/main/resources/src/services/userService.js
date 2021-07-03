import axios from 'axios'

// URLs
const login_url = 'http://localhost:8081/login';
const logout_url = 'http://localhost:8081/login?logout';
const register_url = 'http://localhost:8081/register';

export default {
    login(formData, onSuccess, onFail) {
        this.makeCall(login_url, formData, onSuccess, onFail);
    },
    register(formData, onSuccess, onFail) {
        this.makeCall(register_url, formData, onSuccess, onFail);
    },
    makeCall(url, data, onSuccess, onFail) {
        axios.post(url, data, {crossdomain: true})
            .then((res) => {
                localStorage.EasyCTS_token = res.data;
                onSuccess();
            })
            .catch((e) => onFail(e.response.data.message));
    },
    logout(onSuccess) {
        let token = localStorage.EasyCTS_token;
        axios.put(logout_url, token, {headers: {'Content-Type': 'text/plain'}}, {crossdomain: true})
            .then(() => {
                localStorage.removeItem('EasyCTS_token');
                onSuccess();
            })
            .catch((e) => console.log(e));
    }
}