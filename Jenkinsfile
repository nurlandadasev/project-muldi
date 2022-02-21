pipeline {

  environment {
        registry = "nurlandadashev/"
        registryCredential = 'DockerHubCredentials'
        port = '8585'
        dockerImage = ''
        def projectName = "${env.JOB_NAME}".split('/').last()
  }


  agent {
    docker {
      image 'gradle:6-jdk17'
    }
  }

  stages {

                  stage('Build jar file') {
                      steps {
                            sh './gradlew clean :${projectName}:build'
                         }
                    }

                  stage('Build docker image') {
                      steps {
                         script {
                           dockerImage = docker.build(registry + "${projectName}:$BUILD_NUMBER", "--build-arg project_name=${projectName} .")
                             }
                         }
                    }


                   stage('Push docker image') {
                        steps{
                             script {
                                  docker.withRegistry('', registryCredential) {
                                     dockerImage.push()
                              }
                          }
                     }
                  }

                  tage('Run docker-compose'){
                                   steps{
                                      script{
                                          sh 'REPO=' + registry + projectName + ' VERSION=' + '$BUILD_NUMBER' + ' PORT=' + port + ' docker-compose up -d'
                                      }
                                   }
                              }



    }


}
