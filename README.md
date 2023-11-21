# Proyecto-Bases-Datos

El proyecto se centra en el desarrollo de un programa en Java con una interfaz JavaFX destinada a simplificar la gestión de bases de datos MySQL. La interfaz proporciona al usuario una experiencia intuitiva al acceder, modificar y crear bases de datos, con el objetivo de mejorar la eficiencia y facilitar la interacción con el sistema de gestión de bases de datos. 

# Estructura del programa
El programa cuenta con 7 carpetas diferentes, 4 de ellas son las dependencias necesarias para que tanto la interfaz grafica como el conector JDBC funcionen, en otra de estas se encuentran los 16 archivos FMXL que representan cada ventana de la interfaz gráfica, en la siguiente carpeta se encuentran los respectivos 16 controladores de los archivos FMXL en los que se encuentran todos los métodos que permiten que el programa funcione correctamente, y finalmente en la ultima carpeta se encuentra el programa principal el cual es el encargado de iniciar la ejecución de la interfaz. 
## Instalación

Sigue estos pasos para configurar y ejecutar el proyecto localmente. Asegúrate de tener [Visual Studio Code](https://code.visualstudio.com/) instalado.

1. **Clona el Repositorio:**
    ```bash
    git clone https://github.com/tuusuario/turepositorio.git
    ```

2. **Configuración de la Base de Datos:**
    - Asegúrate de tener un servidor de base de datos MySQL instalado.
    - Crea una base de datos y asegurate de configurar las credenciales.

3. **Configuración de Proyecto en Visual Studio Code:**
    - Abre el proyecto en Visual Studio Code.
    - Instala las extensiones recomendadas (Para Scene Builder). 

4. **Ejecutar la Aplicación:**
    - Ejecuta la aplicación desde Visual Studio Code.

## Requisitos

Asegúrate de tener instalados los siguientes componentes:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX](https://openjfx.io/)
- [Scene Builder](https://gluonhq.com/products/scene-builder/)
- [JDBC Driver para MySQL](https://dev.mysql.com/downloads/connector/j/)

## Documentación

Consulta el [documento de funcionamiento](Informe%20Proyecto.docx) para obtener detalles adicionales sobre el funcionamiento del programa.

Diccionario de metodos y clases [documento de funcionamiento](Diccionario%20de%20clases.docx).

## Contribuir

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una rama para tu función: `git checkout -b feature/nueva-funcion`.
3. Haz tus cambios y haz commit: `git commit -m 'Añadir nueva función'`.
4. Haz push a la rama: `git push origin feature/nueva-funcion`.
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia [Nombre de la Licencia]. Ver el archivo `LICENSE` para más detalles.
