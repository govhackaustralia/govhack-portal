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

    private username = (window as any).USER_NAME;

    created() {

    }

}
