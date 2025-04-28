pipeline {
    agent any
    
    tools {
        maven 'Maven'
        jdk 'JDK'
    }
    
    // Configuración para notificaciones y trabajo en equipo
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        timeout(time: 60, unit: 'MINUTES')
        timestamps()
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                // Usar bat para Windows en lugar de sh
                bat 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                // Usar bat para Windows en lugar de sh
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    // Publicar resultados de cobertura si se implementa en el futuro
                    // jacoco()
                }
            }
        }
        
        stage('Package') {
            steps {
                // Empaquetar la aplicación
                bat 'mvn package -DskipTests'
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
                    
                    // Esperar a que Tomcat despliegue la aplicación
                    sleep(time: 10, unit: 'SECONDS')
                    
                    echo "Aplicación desplegada en: http://localhost:8080/simple-java-webapp"
                }
            }
        }
    }
    
    post {
        success {
            echo 'Despliegue exitoso!'
            // Aquí se pueden agregar notificaciones por correo o Slack
        }
        failure {
            echo 'El build o despliegue falló!'
            // Aquí se pueden agregar notificaciones por correo o Slack
        }
        always {
            // Limpiar el workspace después de cada ejecución
            cleanWs()
        }
    }
}