# misw4203-202215-Vinilos
Vinilos es una aplicación móvil para android escrita en lenguaje kotlin, la cual se encuentra en proceso de desarrollo. LA aplicación permite a los usuarios acceder a servicios de consulta y creación de información relacionada con música (álbumes, artistas, coleccionistas, etc). <br>
LA aplicación funciona para versiones de Android 21 (Lollipop) o superiores.

## ¿Cómo comenzar?
### Pre-requisitos
* Dispositivo móvil o emulador con versión de android 21+.
* Computador con mínimo 8GB de ram y 10GB de espacio en disco
* Android Studio instalado

### Instalación en dispositivo físico (APK)
Para instalar la aplicación en su dispositivo móvil deberá descargar la última versión del APK, la cual podrá encontrar [aquí](https://github.com/VivianaReyV/misw4203-202215-Vinilos/wiki/Instaladores-(APK)). <br>
Debe descargarlo en su dispositivo móvil y ejecutarlo. Es posible que se le soliciten permisos de instalación ya que no es una aplicación con una firma válida para android aún.

### Correr la aplicación localmente (Android Studio)
Para ver el código de la aplicación de forma local debe clonar el proyecto, lo cual puede hacer accediendo desde la herramienta de conexión git de Android Studio o utilizando el comando `git clone https://github.com/VivianaReyV/misw4203-202215-Vinilos.git` en una terminal de su computador. <br><br>
Una vez clonado el proyecto, puede acceder desde `Android Studio > File > Open` y seleccionar el proyecto clonado. <br>
Es importante que sincronice el proyecto con los archivos gradle y verifique que todas las importaciones sean correctas para la versión de Android Studio que tiene instalado. En caso de tener conflictos, sugerimos que actualice Android Studio a su versión más reciente. <br><br>
Finalmente, podrá iniciar el proyecto conectando un dispostivo físico configurado para depuración o creando un emulador dentro de Android Studio. En caso de tener dudas sobre cómo utilizar el IDE puede acceder a la [documentación oficial de Android Studio](https://developer.android.com/docs?hl=es-419).

### Ejecución de pruebas (Espresso)
Para cada historia de usuario desarrollada durante cada iteración, el equipo prepara una serie de pruebas manuales y pruebas de extremo a extremo automatizadas con [Espresso](https://developer.android.com/training/testing/espresso). En la documentación de cada sprint (wiki) el equipo reporta los resultados de las pruebas y en caso de encontrarse issues se crean y se asignan al backlog del proyecto.<br>
Puede acceder a la evidencia de las pruebas en la sección Resultados estrategias de pruebas de cada sprint (ej: [Resultados estrategia de pruebas Sprint 1](https://github.com/VivianaReyV/misw4203-202215-Vinilos/wiki/Resultados-Estrategia-de-Pruebas-Sprint1)). <br><br>

Para ejecutar de manera local las pruebas automatizadas con Espresso, puede acceder en el código del proyecto a `androidTest` y encontrar separados por clases las pruebas (ej: `AlbumCreationTest.kt`). Puede ejecutar las pruebas dando clic derecho sobre el archivo y seleccionando `Run nombre_archivo`.

## Documentación
En el desarrollo del proyecto se utilizan prácticas y patrones recomendados, como lo es el diseño MVVM con patrones Repository y Service Adapter.<br>
Puede acceder a información detallatada del proyecto, como la arquitectura, diseños y avance en cada sprint de desarrollo, desde la [wiki del repositorio](https://github.com/VivianaReyV/misw4203-202215-Vinilos/wiki).

### BackEnd
El Backend de la aplicación se encuentra en [este repositorio](https://github.com/jeysonvr/BackVynils/tree/heroku-integration), el cual ha sido un fork del repositorio proporcionado por [The software design labs](https://github.com/TheSoftwareDesignLab/BackVynils/tree/heroku-integration).

Se encuentra desplegado en heroku bajo el dominio [https://back-vynils-14.herokuapp.com/](https://back-vynils-14.herokuapp.com/).
> **_NOTA:_** Puede encontrar el listado de colecciones de postman disponibles [aquí](https://github.com/jeysonvr/BackVynils/tree/heroku-integration/collections).

### Workflow
Puede leer más acerca del flujo de trabajo y las acciones configuradas con Github Actions [aquí](https://github.com/VivianaReyV/misw4203-202215-Vinilos/wiki/Flujo-de-trabajo). <br>
Se espera que los Pull Request se asocien a un issue dentro de su descripción. Al utilizar las palabras clave de github el issue será automáticamente cerrado. Los Pull Request han sido configurados con una plantilla que incluye los campos requeridos.
Las palabras clave disponibles para relacionar un pull request a un issue son `close, closes, closed, fix, fixes, fixed, resolve, resolves, resolved`.
> Ejemplo de uso: This PR resolves #46
