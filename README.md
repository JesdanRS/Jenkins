# Proyecto Simple Java con Jenkins y Tomcat

Este es un proyecto básico para aprender a utilizar Jenkins para integración continua y Tomcat como servidor de aplicaciones Java.

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
   - Git Integration (si usas Git)
3. Configura las herramientas en Jenkins:
   - JDK
   - Maven

## Construcción y Despliegue Manual

Para construir y desplegar manualmente el proyecto:

1. Clona o descarga este repositorio
2. Navega hasta el directorio del proyecto
3. Ejecuta: `mvn clean package`
4. Copia el archivo WAR generado (`target/simple-java-webapp.war`) al directorio `webapps` de Tomcat
5. Accede a la aplicación en: `http://localhost:8080/simple-java-webapp/`

## Configuración del Pipeline de Jenkins

1. Crea un nuevo proyecto de tipo Pipeline en Jenkins
2. Configura la fuente del código (Git u otro sistema de control de versiones)
3. Especifica que el script del Pipeline está en el SCM y proporciona la ruta al Jenkinsfile
4. Guarda y ejecuta el pipeline

## Notas Importantes

- El Jenkinsfile incluido asume que tienes configuradas las herramientas 'Maven' y 'JDK' en Jenkins
- Ajusta la ruta de despliegue de Tomcat en el Jenkinsfile según tu configuración
- Para entornos de producción, considera utilizar credenciales seguras para el despliegue

## Recursos Adicionales

- [Documentación de Jenkins](https://www.jenkins.io/doc/)
- [Documentación de Tomcat](https://tomcat.apache.org/tomcat-9.0-doc/index.html)
- [Guía de Maven](https://maven.apache.org/guides/index.html)