def buildJar() {
    echo 'Building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "Building the Docker image..."
    // Assuming Docker path is defined in the Jenkins environment
    def dockerPath = env.DOCKER_PATH ?: "docker"  // Default to 'docker' command if DOCKER_PATH is not set

    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        try {
            sh "${dockerPath} build -t nanatwn/demo-app:jma-2.0 ."
            sh "echo $PASS | ${dockerPath} login -u $USER --password-stdin"
            sh "${dockerPath} push nanatwn/demo-app:jma-2.0"
        } catch (Exception e) {
            error "Docker build or push failed: ${e.message}"
        }
    }
}

def deployApp() {
    echo 'Deploying the application...'
    // Add deployment steps as needed
}

return this





