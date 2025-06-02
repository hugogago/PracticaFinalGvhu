# Proyecto Java

Este es un proyecto de Java que requiere ciertas configuraciones para funcionar correctamente. A continuación, se detallan los pasos necesarios para configurar el entorno y ejecutar el proyecto.

## Requisitos

1. **Java SE 22**: Asegúrate de tener instalada la versión 22 de Java SE. Puedes descargarla desde [aquí](https://www.oracle.com/java/technologies/javase-jdk22-downloads.html).

2. **Librerías**: Necesitarás las siguientes librerías en la carpeta `/lib` del proyecto:
   - [MySQL Connector](https://dev.mysql.com/downloads/connector/j/)
   - [jBCrypt 0.4](https://www.mindrot.org/projects/jBCrypt/)

   Descarga las librerías y colócalas en la carpeta `/lib`.

3. **Base de Datos**: Necesitarás importar el archivo SQL que contiene los scripts para crear las tablas en tu base de datos. Asegúrate de tener un servidor MySQL en funcionamiento y crea una base de datos para el proyecto.

   - Importa el archivo SQL utilizando un cliente MySQL o la línea de comandos.

## Configuración del Entorno

1. **Archivo `.env`**: El proyecto incluye un archivo llamado `.envDefault`. Debes renombrarlo a `.env` y modificarlo con la información de conexión a tu base de datos. Asegúrate de que el archivo contenga las siguientes variables:

Reemplaza `nombre_de_tu_base_de_datos`, `tu_usuario` y `tu_contraseña` con los valores correspondientes a tu configuración de base de datos.

2. **Configuración en Eclipse**: Abre el proyecto en Eclipse y asegúrate de que las librerías en la carpeta `/lib` estén correctamente configuradas en el classpath del proyecto.

## Ejecución

Una vez que hayas configurado el entorno y la base de datos, puedes ejecutar el proyecto desde Eclipse. Asegúrate de que no haya errores de compilación y que todas las librerías estén correctamente importadas.

