def gv

pipeline {
    agent any

    environment {
        DOCKER_PATH = "\"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe\""  // Update with your actual Docker path
    }

    tools {
        maven 'maven-3.9'  // Assuming Maven 3.9 is configured in Jenkins tools
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"  // Load your Groovy script file
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    gv.buildJar()
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    gv.buildImage()
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }

    post {
        failure {
            echo "Pipeline failed. Check logs for details."
        }
    }
}
