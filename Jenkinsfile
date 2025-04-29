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

        stage('Clean') {
            steps {
                bat 'mvn clean'
                echo 'Limpieza completa del proyecto para asegurar una nueva construcción'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn compile'
                echo 'Compilando el proyecto con los cambios más recientes'
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
                echo 'Generando un nuevo archivo WAR con los cambios más recientes'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    // Ruta de Tomcat webapps con formato Windows (barras invertidas)
                    def tomcatWeb = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps'
                    
                    // Usar ruta relativa al workspace de Jenkins con formato Windows (barras invertidas)
                    def warFile = "${WORKSPACE}\\target\\simple-java-webapp.war"
                    
                    // Verificar si el directorio target existe
                    bat "dir ${WORKSPACE}\\target"
                    
                    // Verificar si el archivo WAR específico existe en la ruta del workspace
                    bat "if exist \"${warFile}\" (echo El archivo WAR existe) else (echo El archivo WAR NO existe)"
                    
                    echo "Intentando copiar: ${warFile}"
                    
                    // Eliminar versión anterior del WAR en Tomcat si existe
                    bat "if exist \"${tomcatWeb}\\simple-java-webapp.war\" del \"${tomcatWeb}\\simple-java-webapp.war\""
                    bat "if exist \"${tomcatWeb}\\simple-java-webapp\" rmdir /s /q \"${tomcatWeb}\\simple-java-webapp\""
                    // Copiar el archivo .war al directorio webapps usando la ruta del workspace
                    bat "copy \"${warFile}\" \"${tomcatWeb}\\\""5
                    
                    echo "Nuevo WAR desplegado correctamente"
                    sleep(time: 15, unit: 'SECONDS')
                    
                    echo "Aplicación desplegada en: http://localhost:8080/simple-java-webapp/"
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
