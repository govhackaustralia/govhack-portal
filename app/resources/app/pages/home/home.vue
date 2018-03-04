<!--suppress ALL -->
<div id="homePage">
    <h1>Welcome!</h1>

    <b-form-row>
        <b-form action="">
            <b-col></b-col>
            <b-col><label for="email">Email</label>
                <b-form-input type="email" id="email" name="email" v-model="newUser.email"/>
                <label for="firstName">First Name</label>
                <b-form-input type="text" id="firstName" name="firstName" v-model="newUser.firstName"/>
                <label for="lastName">Last Name</label>
                <b-form-input type="text" id="lastName" name="lastName" v-model="newUser.lastName"/>
                <label for="signupType">Who are you?</label>
                <b-form-select name="signupType" id="signupType" v-model="newUser.type">
                    <option value="PARTICIPANT">Participant</option>
                    <option value="EVENT_ADMIN">Organiser</option>
                    <option value="SPONSOR_ADMIN">Sponsor</option>
                </b-form-select>
                <label for="username">Username</label>
                <b-form-input type="text" id="username" name="username" v-model="newUser.username"/>
                <label for="password">Username</label>
                <b-form-input type="password" id="password" name="password" v-model="newUser.password"/>
                <b-button type="submit" @click.prevent="submit()">Sign up!</b-button>
            </b-col>
            <b-col></b-col>
        </b-form>
    </b-form-row>
</div>