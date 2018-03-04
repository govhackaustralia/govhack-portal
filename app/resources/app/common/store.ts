import {BareActionContext, getStoreBuilder} from "vuex-typex"
import Api from "./api";

export {rootStore, app}

export interface AppState {

}

export interface RootState {
    app: AppState
}

let initialAppState: AppState = {

};
const rootStore = getStoreBuilder<RootState>();
const appMod = rootStore.module<AppState>("app", initialAppState);

const getters = {
};

const mutations = {
};

const actions = {
};

const app = {...getters, ...mutations, ...actions};