<!--suppress ALL -->
<div id="sponsorPage">
    <h1>Welcome {{username}}!</h1>

    <p>Sponsor details</p>
    <b-form-row>
        <b-form action="">
            <b-col></b-col>
            <b-col>
                <label for="name">Sponsor name</label>
                <b-form-input type="text" id="name" name="name" v-model="sponsorName"/>
                <b-button type="submit" @click.prevent="update()">Update sponsor</b-button>
            </b-col>
            <b-col></b-col>
        </b-form>
    </b-form-row>

</div>