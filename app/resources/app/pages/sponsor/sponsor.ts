import Vue from "vue";
import Component from "vue-class-component";

export const BASE_URL = (<any>window).BASE_URL;

import "./sponsor.scss";
import Api from "../../common/api";

@Component({
    name: 'sponsor-page',
    template: require('./sponsor.vue'),
    components: {},
    watch: {}
})
export default class SponsorPage extends Vue {

    private userDetails: {};

    private sponsorName: string;

    created() {
        Api.userDetails()
            .then(x => {
                this.userDetails = x;
            });
    }

    update() {
        Api.updateSponsor({name: this.sponsorName})
            .then(x => {
                console.log(x);
            })
    }

}
