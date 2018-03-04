import Vue from "vue";
import Component from "vue-class-component";

export const BASE_URL = (<any>window).BASE_URL;

import "./login.scss";
import Api from "../../common/api";

@Component({
    name: 'login-page',
    template: require('./login.vue'),
    components: {},
    watch: {}
})
export default class LoginPage extends Vue {

    private username: string = "";
    private password: string = "";

    created() {

    }

    login() {
        Api.doLogin(this.username, this.password)
            .then(x => {
                if (x.success && x.success === true) {

                }
            })
    }

}
