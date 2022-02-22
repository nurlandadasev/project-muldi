pipeline {

  environment {
        registry = "nurlandadashev/"
        registryCredential = 'DockerHubCredentials'
        testProjectPort = '8585'
        dockerImage = ''
        def projectName = "${env.JOB_NAME}".split('/').last()
  }


  agent {
    docker {
      image 'gradle:6-jdk17'
    }
  }

  stages {

                  stage('Checkout test-module') {
                          steps {
                              dir("test-module") {
                                  git branch: "master",
                                  url: 'https://github.com/nurlandadasev/${projectName}.git'
                              }
                          }
                      }

                  stage('Build jar file') {
                      steps {
                            sh 'pwd'
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

                  stage('Run docker image') {
                       steps {
                             script {
                                   dockerImage.run()
                               }
                          }
                     }



    }


}
