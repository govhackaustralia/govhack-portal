import axios from "axios";

declare const BASE_URL: string;

export default class Api {

    public static async createUser(userDetails: any) {
        return axios.put('/portal/api/user/create', userDetails)
            .then(x => {
                return x.data;
            })
            .catch(y => {
                console.error(y);
            })
    }

    public static async userDetails() {
        return axios.get('/portal/api/user')
            .then(x => {
                return x.data;
            })
            .catch(y => {
                console.error(y);
            })
    }

    public static async updateSponsor(data: any) {
        return axios.post('/portal/api/sponsor', data)
            .then(x => {
                return x.data;
            })
            .catch(y => {
                console.error(y);
            })
    }

    public static async doLogin(username: string, password: string) {
        return axios({
            url: '/portal/do-login',
            data: `username=${username}&password=${password}`,
            method: 'POST',
            headers: {
                'Content-type': 'application/x-www-form-urlencoded'
            }
        })
            .then(x => {
                console.log(x.headers);
                console.log(x);
                document.cookie = x.headers['Set-Cookie'];
                return x.data;
            })
            .catch(y => {
                console.error(y);
            })
    }

}
