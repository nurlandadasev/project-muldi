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
                              def jobBaseName = "${env.JOB_NAME}".split('/').last();
                              echo "Job Name (excl. path): ${jobBaseName}";
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
