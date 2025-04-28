# Instrucciones para Configurar el Proyecto con Jenkins y Git

Este documento proporciona instrucciones paso a paso para configurar el proyecto Java para trabajar en equipo utilizando Git y Jenkins para integración continua.

## 1. Configuración del Repositorio Git

### Crear y Configurar el Repositorio

1. Crea un repositorio Git en GitHub, GitLab o Bitbucket.
2. Inicializa el repositorio local y sube el código:

```bash
git init
git add .
git commit -m "Commit inicial del proyecto"
git remote add origin [URL_DE_TU_REPOSITORIO]
git push -u origin master
```

3. Comparte la URL del repositorio con tu compañero de equipo.

### Clonar el Repositorio (para tu compañero)

```bash
git clone [URL_DEL_REPOSITORIO]
cd Jenkins
```

## 2. Configuración de Jenkins

### Requisitos Previos

1. Asegúrate de tener Jenkins instalado y en ejecución.
2. Instala los siguientes plugins en Jenkins:
   - Maven Integration
   - Pipeline
   - Git Integration
   - JUnit Plugin
   - Timestamper

### Configurar las Herramientas en Jenkins

1. Ve a "Administrar Jenkins" > "Global Tool Configuration"
2. Configura JDK:
   - Nombre: `JDK`
   - Ruta de instalación: [Ruta a tu instalación de Java]
3. Configura Maven:
   - Nombre: `Maven`
   - Ruta de instalación: [Ruta a tu instalación de Maven]

### Crear el Pipeline en Jenkins

1. En Jenkins, haz clic en "Nueva Tarea"
2. Ingresa un nombre para el proyecto (por ejemplo, "simple-java-webapp")
3. Selecciona "Pipeline" y haz clic en "OK"
4. En la sección "Pipeline":
   - Selecciona "Pipeline script from SCM"
   - En SCM, selecciona "Git"
   - En "Repository URL", ingresa la URL de tu repositorio Git
   - En "Credentials", agrega tus credenciales de Git si es necesario
   - En "Branch Specifier", deja "*/master" o "*/main" según corresponda
   - En "Script Path", ingresa "Jenkinsfile"
5. En la sección "Build Triggers":
   - Marca "Poll SCM"
   - Ingresa un horario (por ejemplo, "H/5 * * * *" para verificar cada 5 minutos)
6. Haz clic en "Guardar"

## 3. Verificar la Configuración de Tomcat

1. Asegúrate de que Tomcat esté instalado y en ejecución.
2. Verifica que la ruta en el Jenkinsfile coincida con tu instalación de Tomcat:

```groovy
def tomcatWeb = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps'
```

3. Si es necesario, actualiza esta ruta en el Jenkinsfile para que coincida con tu instalación.

## 4. Flujo de Trabajo para el Equipo

### Proceso de Desarrollo

1. Antes de comenzar a trabajar, actualiza tu copia local:

```bash
git pull origin master
```

2. Crea una rama para tu nueva característica o corrección:

```bash
git checkout -b feature/nueva-caracteristica
```

3. Realiza tus cambios y pruébalos localmente:

```bash
mvn clean test
```

4. Haz commit de tus cambios:

```bash
git add .
git commit -m "Descripción detallada de los cambios"
```

5. Sube tu rama al repositorio remoto:

```bash
git push origin feature/nueva-caracteristica
```

6. Crea un Pull Request/Merge Request para que tu compañero revise los cambios.

7. Después de la revisión, fusiona los cambios con la rama principal.

### Verificación de la Integración Continua

1. Después de fusionar los cambios, Jenkins detectará automáticamente los cambios y ejecutará el pipeline.

2. Ambos miembros del equipo pueden verificar el estado del pipeline en Jenkins:
   - Accede a Jenkins en tu navegador (típicamente http://localhost:8080)
   - Navega a tu proyecto
   - Revisa el estado de la última ejecución del pipeline

3. Si el pipeline se ejecuta correctamente, la aplicación se desplegará automáticamente en Tomcat.

4. Verifica la aplicación desplegada en: http://localhost:8080/simple-java-webapp/simple

## 5. Solución de Problemas Comunes

### Errores de Compilación o Pruebas

1. Revisa los logs de Jenkins para identificar el problema.
2. Corrige los errores en tu entorno local.
3. Haz commit y push de las correcciones.
4. Jenkins ejecutará automáticamente el pipeline nuevamente.

### Errores de Despliegue

1. Verifica que Tomcat esté en ejecución.
2. Asegúrate de que la ruta de Tomcat en el Jenkinsfile sea correcta.
3. Verifica los permisos de la carpeta webapps de Tomcat.

### Conflictos de Git

1. Actualiza tu rama con los últimos cambios de la rama principal:

```bash
git checkout feature/tu-rama
git pull origin master
```

2. Resuelve los conflictos manualmente.
3. Haz commit de las resoluciones y continúa con el proceso normal.

## Recordatorio

Mantén una comunicación constante con tu compañero de equipo sobre los cambios que estás realizando para minimizar conflictos y asegurar una integración continua exitosa.