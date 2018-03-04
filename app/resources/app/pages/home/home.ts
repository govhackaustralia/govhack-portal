import Vue from "vue";
import Component from "vue-class-component";

export const BASE_URL = (<any>window).BASE_URL;

import "./home.scss";
import Api from "../../common/api";

@Component({
    name: 'home-page',
    template: require('./home.vue'),
    components: {},
    watch: {}
})
export default class HomePage extends Vue {

    private newUser: any = {
        email: "",
        firstName: "",
        lastName: "",
        username: "",
        password: ""
    };

    created() {

    }

    submit() {
        Api.createUser(this.newUser)
            .then(user => {
                console.log(user);

            })
    }

}
