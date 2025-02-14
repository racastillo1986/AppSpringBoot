# IMAGEN MODELO
FROM eclipse-temurin:11-jdk

# INFORMAR EL PUERTO DONDE SE EJECUTA EL CONTENEDOR (INFORMATIVO)
EXPOSE 8080

# DEFINIR DIRECTORIO RAIZ DE NUESTRO CONTENEDOR
WORKDIR /root

# COPIAR Y PEGAR ARCHIVOS DENTRO DEL CONTENEDOR
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

# DAR PERMISOS DE EJECUCIÓN AL SCRIPT mvnw
RUN chmod +x /root/mvnw

# INSTALAR dos2unix Y CONVERTIR EL ARCHIVO mvnw
RUN apt-get update && apt-get install -y dos2unix
RUN dos2unix /root/mvnw

# VERIFICAR LOS PERMISOS DEL ARCHIVO mvnw
RUN ls -l /root/mvnw

# VERIFICAR EL CONTENIDO DEL SCRIPT mvnw
RUN cat /root/mvnw

# DESCARGAR LAS DEPENDENCIAS (usando la ruta absoluta de mvnw)
RUN bash -c "/root/mvnw dependency:go-offline"

# COPIAR EL CODIGO FUENTE DENTRO DEL CONTENEDOR
COPY ./src /root/src

# CONSTRUIR NUESTRA APLICACION
RUN /root/mvnw clean install -DskipTests

# LEVANTAR NUESTRA APLICACION CUANDO EL CONTENEDOR INICIE
ENTRYPOINT ["java","-jar","/root/target/SpringDocker-0.0.1-SNAPSHOT.jar"]
