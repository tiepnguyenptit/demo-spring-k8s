pipeline {
  agent any
  tools {
    jdk 'jdk-17'
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }

  stages {
    stage('Build JAR') {
        steps {
            sh 'chmod +x mvnw'
            sh 'mvn clean install'
        }
    }
    stage('Build') {
      steps {
        sh 'docker build -t demo-spring-k8s:0.0.1 .'
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