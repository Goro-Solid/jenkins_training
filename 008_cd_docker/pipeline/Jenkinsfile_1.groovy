#!/usr/bin/groovy

pipeline {
    agent any

    options {
        disableConcurrentBuilds()
    }

    stages {

        stage("Build Docker Image") {
            steps {
                buildApp()
            }
        }

        stage("Deploy - Dev") {
            steps {
                //deploy('dev')
            }
        }

    }
}


// steps
def buildApp() {
    dir ('008_cd_docker/pipeline' ) {
        pwsh 'docker build `-t hands-on-jenkins/myapp:${BUILD_NUMBER} .'
    }
}

def deploy(environment) {

    def containerName = ''
    def port = ''

    if ("${environment}" == 'dev') {
        containerName = "app_dev"
        port = "8888"
    }
    else {
        println "Environment not valid"
        System.exit(0)
    }

    sh "docker ps -f name=${containerName} -q | xargs --no-run-if-empty docker stop"
    sh "docker ps -a -f name=${containerName} -q | xargs -r docker rm"
    sh "docker run -d -p ${port}:5000 --name ${containerName} hands-on-jenkins/myapp:${BUILD_NUMBER}"

}

