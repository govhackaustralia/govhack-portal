<!--suppress ALL -->
<div id="loginPage">
    <h1>Login to continue!</h1>

    <b-form-row>
        <b-form action="">
            <b-col></b-col>
            <b-col>
                <label for="username">Username</label>
                <b-form-input type="text" id="username" name="username" v-model="username"/>
                <label for="password">Password</label>
                <b-form-input type="password" id="password" name="password" v-model="password"/>
                <b-button type="submit" @click.prevent="login()">Login!</b-button>
            </b-col>
            <b-col></b-col>
        </b-form>
    </b-form-row>

</div>