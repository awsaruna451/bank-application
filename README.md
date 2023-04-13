### Running a bank application using docker

### 1. Clone the project from git hub
    git clone https://github.com/awsaruna451/bank-application.git
    
   # Please use main branch

### 2. Build the Docker Image
    To build the Docker image from the root directory of the app use below command to go inside the root directory
#  CD bank-application
    run the following command in the root directory of the bank-application to build the project
    mvn clean install -DskipTests=true


### To build the docker image using the docker-compose use below command
   docker-compose build
### First run the mysql container use below command
   docker-compose up -d bank_application_mysql

### Then run the spring boot application use below command
   docker-compose up -d bank_application_backend

### If you need to run the application without docker remove below configuration url name in application.properties file
    spring.datasource.url = jdbc:mysql://localhost:3306/abc-bank?allowPublicKeyRetrieval=true&useSSL=false




