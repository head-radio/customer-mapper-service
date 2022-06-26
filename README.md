# Spring boot customer service

The projects contain a simple service for creation/retrieve customer using h2 database.

## Download
### Using The Command Line
1. Press the Clone or download button in the top right
2. Copy the URL (link)
3. Open the command line and change directory to where you wish to clone to
4. Type 'git clone' followed by URL in step 2
```bash
$ git clone https://github.com/[user-name]/[repository]
```

### Download Zip File

1. Download this GitHub repository
2. Extract the zip archive
3. Copy/ move to the desired location


## Language information
### Built for
This project is a spring boot based application, written in Java 11 and spring version 2.7.0.

### Principal Dependencies
- spring-boot-starter-web 
- spring-boot-starter-data-jpa
- spring-boot-starter-validation
- h2database
- spring-boot-starter-test

Quick start
-----------
`mvn spring-boot:run`

### cURL for testing
- curl --location --request POST 'http://localhost:8080/customer' --header 'Content-Type: application/json' --data-raw '{"customerId":"custer_test_1", "createdAt" : "2022-06-24"}'
- curl --location --request GET 'http://localhost:8080/customer/custer_test_1'



## Licence
AP License
Copyright
