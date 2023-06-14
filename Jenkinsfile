pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }

  stages {
    stage('Build') {
      steps {
        sh 'docker build -t tiepnguyenptit/demo-spring-k8s .'
      }
    }
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
    stage('Push') {
      steps {
        sh 'docker push tiepnguyenptit/demo-spring-k8s'
      }
    }
  }
  post {
    always {
      script {
        if (getContext(hudson.FilePath)) {
          sh 'docker logout'
        }
      }
    }
  }
}