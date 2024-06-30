pipeline {
    agent any

    stages {
        stage('Build Image') {
            steps {
                script {
                    echo 'Building the Docker image...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        try {
                            // Use the full path to Docker executable
                            bat '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe" build -t nanatwn/demo-app:jma-2.0 .'
                            bat 'echo %PASS% | "C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe" login -u %USER% --password-stdin'
                            bat '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe" push nanatwn/demo-app:jma-2.0'
                        } catch (Exception e) {
                            error "Docker build or push failed: ${e.message}"
                        }
                    }
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

