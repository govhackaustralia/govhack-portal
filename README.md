# Govhack portal

##Note
This is doc is v0.01. I'll add more details in the coming days, but should be enough to get you started with the project -- Andy

###How to run

####Get gradle
Install `gradle` on your computer... On Mac `brew install gradle` on other OS: https://gradle.org/install/


####Get Postgres
- Install `postgres`, either via `brew install postgresql` or by downloading the install from https://www.postgresql.org/download/
- Create a Database called `govhack_portal` and a user called `govhack` with access to that database (user doesn't need password)

If you have issues setting up Postgres on mac, this is a good tutorial (it's old, but still applies): https://www.moncefbelyamani.com/how-to-install-postgresql-on-a-mac-with-homebrew-and-lunchy - once you're setup, make sure you're running postgres as a service, if you installed with brew run `brew services restart postgresql`

Also, before you continue, make sure you can successfully login to the database with `psql -U govhack -d govhack_portal` 

####Prepare the database
Go to the main folder of this app and run 
```
gradle dropAll update
```
This will prepare your database (after installing all the dependencies it needs)

####Run the app!
Go to the main folder of the app run
```
gradle bootRun
``` 

(in another terminal window)

- Go to `./app` and run `npm install` to install all the npm dependencies
- Run `npm run watch` to start a webpack watch server or `npm run build` to build the app.

#### Enjoy
If everything went well, you should be able to access http://localhost:9088/portal