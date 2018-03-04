import Vue from "vue";
import Component from "vue-class-component";

export const BASE_URL = (<any>window).BASE_URL;

import "./sponsor.scss";
import Api from "../../common/api";
import {UserDetails} from "../../declarations/userDetails";
import {SponsorDetails} from "../../declarations/sponsorDetails";
import {PrizeDetails} from "../../declarations/prizeDetails";

@Component({
    name: 'sponsor-page',
    template: require('./sponsor.vue'),
    components: {},
    watch: {}
})
export default class SponsorPage extends Vue {

    private userDetails: UserDetails = <UserDetails>{};
    private sponsorDetails: SponsorDetails = <SponsorDetails>{};
    private newPrizeDetails: PrizeDetails = <PrizeDetails>{};

    created() {
        Api.userDetails()
            .then(x => {
                this.userDetails = x;
            });

        Api.sponsorDetails()
            .then(x => {
                this.sponsorDetails = x
            })
    }

    update() {
        Api.updateSponsor(this.sponsorDetails)
            .then(x => {
                this.sponsorDetails = x;
            })
    }

    addPrize() {
        Api.addPrize({...this.newPrizeDetails, sponsor: this.sponsorDetails.id})
            .then(x => console.log(x));
    }

}
