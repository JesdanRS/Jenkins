pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

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
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    // jacoco()
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Rutas corregidas usando barras normales
                    def tomcatWeb = 'C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps'
                    def warFile = 'target/simple-java-webapp.war'

                    // Copiar el archivo .war al directorio webapps
                    bat "copy \"${warFile}\" \"${tomcatWeb}\""

                    sleep(time: 10, unit: 'SECONDS')

                    echo "Aplicación desplegada en: http://localhost:8081/simple-java-webapp/"
                }
            }
        }
    }

    post {
        success {
            echo '¡Despliegue exitoso!'
        }
        failure {
            echo '¡El build o despliegue falló!'
        }
        always {
            cleanWs()
        }
    }
}
