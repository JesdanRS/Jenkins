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
                    // Ruta de Tomcat webapps
                    def tomcatWeb = 'C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps'
                    
                    // Usar ruta relativa al workspace de Jenkins
                    def warFile = "${WORKSPACE}/target/simple-java-webapp.war"
                    
                    // Verificar si el directorio target existe
                    bat "dir ${WORKSPACE}\\target"
                    
                    // Verificar si el archivo WAR específico existe en la ruta del workspace
                    bat "if exist \"${warFile}\" (echo El archivo WAR existe) else (echo El archivo WAR NO existe)"
                    
                    echo "Intentando copiar: ${warFile}"
                    
                    // Copiar el archivo .war al directorio webapps usando la ruta del workspace
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
