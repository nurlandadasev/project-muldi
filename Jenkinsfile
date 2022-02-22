pipeline {

  environment {
        registry = "nurlandadashev/"
        registryCredential = 'DockerHubCredentials'
        testProjectPort = '8585'
        dockerImage = ''
//         def projectName = "${env.JOB_NAME}".split('/').last()
        def subProjectName = "${params.sub_project_name}"
  }


  agent {
    docker {
      image 'gradle:6-jdk17'
    }
  }

  stages {

                  stage('Checkout subproject') {
                          steps {
                              dir("${subProjectName}") {
                                  git branch: "master",
                                  url: "https://github.com/nurlandadasev/${subProjectName}.git"
                              }
                          }
                      }

                  stage('Build jar file') {
                      steps {
                            echo "Hello "
                            sh './gradlew clean :${subProjectName}:build'
                         }
                    }

                  stage('Build docker image') {
                      steps {
                         script {
                           dockerImage = docker.build(registry + "${subProjectName}:$BUILD_NUMBER", "--build-arg project_name=${subProjectName} .")
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
