import axios from "axios";

declare const BASE_URL: string;

export default class Api {

    public static async createUser(userDetails: any) {
        return axios.put('/portal/api/user', userDetails)
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
                return x.data;
            })
            .catch(y => {
                console.error(y);
            })
    }

}
