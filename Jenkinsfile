pipeline {
    agent any
    tools{
        maven 'Maven_3_9_9'
    }
    stages{
        stage('Build Maven'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/KyawtKyawtShweSinn/devop-automation']])
                sh 'mvn clean install'
            }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t kyawtkshwesin/devop-automation:v1.0 .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u kyawtkshwesin -p ${dockerhubpwd}'
                    }
                    sh 'docker push kyawtkshwesin/devop-automation:v1.0'
                }
            }
        }
    }
}