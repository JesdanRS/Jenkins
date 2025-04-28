# Proyecto Simple Java con Jenkins y Tomcat

Este es un proyecto básico para aprender a utilizar Jenkins para integración continua y Tomcat como servidor de aplicaciones Java. Este proyecto está diseñado para trabajo en equipo, permitiendo que varios desarrolladores colaboren utilizando Git y Jenkins para la integración continua.

## Estructura del Proyecto

Este proyecto sigue la estructura estándar de Maven:

```
simple-java-webapp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ejemplo/
│   │   │           └── SimpleServlet.java
│   │   └── webapp/
│   │       ├── index.html
│   │       └── WEB-INF/
│   │           └── web.xml
│   └── test/
├── pom.xml
├── Jenkinsfile
└── README.md
```

## Requisitos Previos

Para utilizar este proyecto, necesitarás tener instalado:

1. **Java JDK 8** o superior
2. **Maven** para la gestión de dependencias y construcción
3. **Tomcat** como servidor de aplicaciones
4. **Jenkins** para la integración continua

## Configuración de Tomcat

1. Descarga e instala [Apache Tomcat](https://tomcat.apache.org/download-90.cgi) (versión 9.x recomendada)
2. Configura un usuario con roles de manager-script en `tomcat-users.xml`:

```xml
<tomcat-users>
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="admin" password="password" roles="manager-gui,manager-script"/>
</tomcat-users>
```

## Configuración de Jenkins

1. Descarga e instala [Jenkins](https://www.jenkins.io/download/)
2. Instala los siguientes plugins en Jenkins:
   - Maven Integration
   - Pipeline
   - Git Integration
   - JUnit Plugin
   - Timestamper
3. Configura las herramientas en Jenkins:
   - JDK (con el nombre "JDK" en la configuración global)
   - Maven (con el nombre "Maven" en la configuración global)

## Construcción y Despliegue Manual

Para construir y desplegar manualmente el proyecto:

1. Clona o descarga este repositorio
2. Navega hasta el directorio del proyecto
3. Ejecuta: `mvn clean package`
4. Copia el archivo WAR generado (`target/simple-java-webapp.war`) al directorio `webapps` de Tomcat
5. Accede a la aplicación en: `http://localhost:8080/simple-java-webapp/`

## Configuración del Pipeline de Jenkins

1. Crea un nuevo proyecto de tipo Pipeline en Jenkins
2. Configura la fuente del código:
   - Selecciona Git como sistema de control de versiones
   - Ingresa la URL de tu repositorio Git compartido
   - Configura las credenciales si es necesario
3. En la sección "Pipeline", selecciona "Pipeline script from SCM"
4. Especifica que el script del Pipeline está en el SCM y asegúrate que la ruta sea "Jenkinsfile"
5. Configura el trigger para que se ejecute automáticamente cuando haya cambios en el repositorio:
   - Marca la opción "Poll SCM" y configura un horario (por ejemplo, "H/5 * * * *" para verificar cada 5 minutos)
6. Guarda la configuración y ejecuta el pipeline manualmente la primera vez

## Trabajando en Equipo con Git y Jenkins

### Configuración del Repositorio Git

1. Crea un repositorio Git compartido (en GitHub, GitLab, Bitbucket, etc.)
2. Sube el código inicial al repositorio:
   ```bash
   git init
   git add .
   git commit -m "Commit inicial"
   git remote add origin [URL_DE_TU_REPOSITORIO]
   git push -u origin master
   ```
3. Comparte la URL del repositorio con tu compañero de equipo

### Flujo de Trabajo para el Equipo

1. Cada miembro del equipo debe clonar el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   ```

2. Para cada nueva característica o corrección, crear una rama:
   ```bash
   git checkout -b feature/nueva-caracteristica
   ```

3. Realizar cambios, probarlos localmente y hacer commits:
   ```bash
   git add .
   git commit -m "Descripción detallada de los cambios"
   ```

4. Subir los cambios al repositorio remoto:
   ```bash
   git push origin feature/nueva-caracteristica
   ```

5. Crear un Pull Request/Merge Request para que el otro miembro del equipo revise los cambios

6. Después de la revisión, fusionar los cambios con la rama principal

7. Jenkins detectará automáticamente los cambios y ejecutará el pipeline

### Verificación de la Integración Continua

1. Después de cada push o merge a la rama principal, Jenkins:
   - Detectará los cambios automáticamente
   - Ejecutará el pipeline definido en el Jenkinsfile
   - Compilará el código
   - Ejecutará las pruebas
   - Desplegará la aplicación en Tomcat si todo está correcto

2. Ambos miembros del equipo pueden verificar el estado del pipeline en Jenkins

3. Si el pipeline falla, el equipo debe revisar los logs en Jenkins para identificar y corregir el problema

## Notas Importantes

- El Jenkinsfile incluido asume que tienes configuradas las herramientas 'Maven' y 'JDK' en Jenkins
- Ajusta la ruta de despliegue de Tomcat en el Jenkinsfile según tu configuración
- Para entornos de producción, considera utilizar credenciales seguras para el despliegue
- Mantén comunicación constante con tu compañero de equipo sobre los cambios que estás realizando
- Realiza pull regularmente para mantener tu copia local actualizada y evitar conflictos

## Recursos Adicionales

- [Documentación de Jenkins](https://www.jenkins.io/doc/)
- [Documentación de Tomcat](https://tomcat.apache.org/tomcat-9.0-doc/index.html)
- [Guía de Maven](https://maven.apache.org/guides/index.html)