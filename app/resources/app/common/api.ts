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

}
