pipeline{
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Build JAR File'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master'], [name: '*/develop']], extensions: [], userRemoteConfigs: [[credentialsId: '9fedc6b2-e7f4-4b11-b2ad-8958d09d4ccb', url: 'https://github.com/CRetamales/MueblesStgo']]])
                dir('MSapp/')
                {
                     sh 'gradle build -x test'
                }
            }
        }
        stage('Test'){
            steps{
                //Junit test gradle
                sh 'gradle test'
                //SonarQube
                //No se pudo instalar en un servidor digital ocean
            }
        }
        stage('Build docker image'){
            steps{
                sh 'docker build -t cfretamales/ms_app .'
            }
        }
    }
}