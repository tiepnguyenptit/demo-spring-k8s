pipeline {
  agent any
  tools {
    jdk 'Java 17'
  }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub')
  }

  stages {
    stage('Clone target branch') {
        steps {
            git branch: env.gitlabTargetBranch, url: 'https://$GIT_CREDENTIALS_USR:$GIT_CREDENTIALS_PSW@gitlab.com/mpa1998.mpa/spring-demo-ocs.git'
        }
    }
    stage('Build JAR') {
        steps {
            sh '''#!/bin/bash
            	cd springserver
            	./gradlew clean build
            	'''
        }
    }
    stage('Build') {
      steps {
        sh 'docker build -t mpa1998/springdemo -f springserver/Dockerfile .'
      }
    }
    stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
    stage('Push') {
      steps {
        sh 'docker push mpa1998/springdemo'
      }
    }
    stage('Trigger K8s job') {
        steps {
            build job: "Spring Demo OCS K8s", wait: false, parameters: [
                [$class: 'StringParameterValue', name: 'gitlabTargetBranch', value: env.gitlabTargetBranch]
            ]
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