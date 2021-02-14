# Project Managment Data Agregator

## Build status
- Develop branch ![build status of develop branch](https://travis-ci.org/CoffeeProjects/project-managment-data-agregator.svg?branch=develop)
- Master branch ![build status of master branch](https://travis-ci.org/CoffeeProjects/project-managment-data-agregator.svg?branch=master)

## Build & run
This project is compiled & run with classic Maven commands :
- `mvn clean install`
- `mvn spring-boot:run`
    - DEV profile `-Drun.profiles="dev"`
    - Debug mode `-Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"`
    
During the build, you can add the DB connector library to include in the package, via the following profiles :
- postgresql (default)
- ocrale

This repo includes a file allowing to mount the postgreSQL database via docker, in resources (docker-compose.yml)

## Data model update
Each addition, modification or deletion of an entity must be indicated in a new script in this directory :
```
resources/db/changelog/changes/*.yml
```

Locally, run the makefile designed at the root of the project, to automatically generate these files :
```
make makeMigration MIGRATION_LABEL="feature_name"
```

The makefile will add all the scripts to the changeLog master, so that they are played when the application is run (to modify the database accordingly) :
```
resources/db/db.changelog-master.yml
```
