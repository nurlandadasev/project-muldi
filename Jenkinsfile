pipeline {

  environment {
        registry = "nurlandadashev/test"
        registryCredential = 'DockerHubCredentials'
        testProjectPort = '8585'
        dockerImage = ''
  }


  agent {
    docker {
      image 'gradle:6-jdk17'
    }
  }

  stages {

                  stage('Build jar file') {
                      steps {
                              checkout('https://github.com/nurlandadasev/${env.JOB_BASE_NAME}.git')
                              sh './gradlew clean build'
                         }
                    }

                  stage('Build docker image') {
                      steps {
                         script {
                           dockerImage = docker.build registry + ":$BUILD_NUMBER"
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


                  stage('Run docker image') {
                       steps {
                             script {
                                   dockerImage.run()
                               }
                          }
                     }

         

    }


}
