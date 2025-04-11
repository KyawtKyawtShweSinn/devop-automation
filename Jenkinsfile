pipeline {
    agent any
    tools{
        maven 'Maven_3_9_9'
    }
    environment {
        IMAGE_NAME = 'kyawtkshwesin/devop-automation'
        IMAGE_TAG = "v${BUILD_NUMBER}"
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
                    sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u kyawtkshwesin -p ${dockerhubpwd}'
                    }
                    sh 'docker push ${IMAGE_NAME}:${IMAGE_TAG}'
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh """
                    sed 's|kyawtkshwesin/devop-automation:.*|${IMAGE_NAME}:${IMAGE_TAG}|' k8s-deployment.yaml > k8s-tmp-deployment.yaml
                    kubectl apply -f k8s-tmp-deployment.yaml
                    kubectl apply -f k8s-service.yaml
                    """
                }
            }
        }
    }
}