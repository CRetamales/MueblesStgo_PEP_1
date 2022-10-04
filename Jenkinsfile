pipeline{
    agent any
    tools {
        gradle 'gradle'
    }
    stages {
        stage('Build JAR File'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master'], [name: '*/develop']], extensions: [], userRemoteConfigs: [[credentialsId: 'Jenkins-Github', url: 'https://github.com/CRetamales/MueblesStgo']]])
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