pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Ajusta estas rutas según tu configuración de Tomcat
                    def tomcatWeb = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps'
                    def warFile = 'target\\simple-java-webapp.war'
                    
                    // Para Windows
                    bat "copy ${warFile} ${tomcatWeb}"
                }
            }
        }
    }
    
    post {
        success {
            echo 'Despliegue exitoso!'
        }
        failure {
            echo 'El build o despliegue falló!'
        }
    }
}