<!--suppress ALL -->
<div id="sponsorPage">
    <h1>Welcome {{userDetails.firstName}}!</h1>

    <p>Sponsor details</p>
    <b-form-row>
        <b-form action="">
            <b-col></b-col>
            <b-col>
                <label for="name">Sponsor name</label>
                <b-form-input type="text" id="name" name="name" v-model="sponsorDetails.name"/>
                <b-button type="submit" @click.prevent="update()">Update sponsor</b-button>
            </b-col>
            <b-col></b-col>
        </b-form>
    </b-form-row>

    <hr>

    <h3>Prizes</h3>

    <div v-for="p in sponsorDetails.prizeList">
        {{p.name}}
        <br>
    </div>

    <b-form-row>
        <b-form action="">
            <b-col></b-col>
            <b-col>
                <label for="name">Prize name</label>
                <b-form-input type="text" id="name" name="name" v-model="newPrizeDetails.name"/>
                <b-button type="submit" @click.prevent="addPrize()">Add prize</b-button>
            </b-col>
            <b-col></b-col>
        </b-form>
    </b-form-row>

</div>