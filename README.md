# Challenge ONE | Java | Back-end | Hotel Alura

<p align="center" >
     <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

<p align="left">
    <img src="https://img.shields.io/badge/STATUS-TERMINADO-green">
</p>

<p align="left">
    <img src="https://img.shields.io/github/stars/Randyfermin?style=social" alt="GitHub Org's stars">
</p>

### índice

- [Descripción del proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologias-utilizadas)
- [Importante](#importante)
- [Download](#download)
- [Editor IDE Eclipse](#editor-ide-eclipse)
- [Caracteres Especiales en el proyecto](#caracteres-especiales-en-el-proyecto)
- [Base de Dados](#base-de-dados)
- [Plugin WindowBuilder](#plugin-windowBuilder)
- [Características de la aplicación y demostración](#Características-de-la-aplicación-y-demostración)
- [Personas Desarrolladores del Proyecto](#personas-desarrolladores)
- [Licencia](#licencia)

---
## Descripción del proyecto:
<p align="justify">
    
    Este proyecto fue desarrollado con el proposito de cumplir con el challenge creado por:
    Alura + Oracle Next Education en la ruta de aprendizaje BackEnd.

    Challenge: Crea tu propia aplicación Desktop con conexión a la Base de Datos.
    
    Funcionalidades: 
    - Módulo de acceso para usuarios registrados en la Base de Datos.
    - Módulo de registro de huespedes. (Editar y Eliminar huesped).
    - Módulo de control de reservas de habitaciones. (Editar y Eliminar reservas).

</p>

</br>

## 🖥️ Tecnologías Utilizadas:

- Java
- Eclipse
- Biblioteca JCalendar
- MySql
- Plugin WindowBuilder </br>

---
## ⚠️ Importante! ⚠️

☕ Se recomienda el uso de Java versión 8 o superior para compatibilidad. </br></br>
📝 Recomendamos usar el editor de Eclipse para compatibilidad con la Interfaz Gráfica. </br></br>
🎨 La interfaz contiene dos métodos importantes:
- setResizable(false): determina el tamaño de la ventana, y a través del parámetro <strong>false</strong>, la pantalla no se puede maximizar;
- setLocationRelativeTo(null): determina la ubicación de la ventana, y a través del parámetro <strong>null</strong> la mantiene centrada en la pantalla.

---

## 🔍 ¡Analizando nuestro repositorio!

### Este es el repositorio base de nuestro proyecto, en el encontrarás:
#### 🔹 src/views: carpeta con toda la interfaz gráfica de las pantallas necesarias para desarrollar el programa;
#### 🔹 src/imagenes: carpeta con imágenes que puedes usar en tu proyecto. Siéntete libre de usar otros, si lo deseas;
#### 🔹 src/sql-files: carpeta con el archivo del tipo SQL donde para la creación de la Base de Datos, tablas y datos primarios para el funcionamiento del programa;
#### 🔹 src/dao: carpeta con todas las clase de accesos a la Base de Datos (Data Access Object);
#### 🔹 src/factory: carpeta con las clases que se encargan de administrar las conexiones a la Base de Datos Mysql;
#### 🔹 src/modelo: carpeta con todas las clases modelos de las tablas en la Base de Datos;
#### 🔹 src/Utils: carpeta con todas las clases de utilería de soporte al programa; 
</br>

## ⬇️ Download
- [Codigo fuente del proyecto](https://github.com/Randyfermin/challenge-one-alura-hotel-latam.git)
- [Archivo ZIP](https://github.com/Randyfermin/challenge-one-alura-hotel-latam/archive/refs/heads/repositorio-base.zip)


</br></br>

## 📝 Editor IDE Eclipse

### ¿Cómo importar mi proyecto a Eclipse?

1 - Una vez dentro del Editor al lado izquierdo, haz clic en el <em>Files</em> que está en el menú en la parte superior, elige la opción <em>Open Projects from File System</em>.

<p align="center" >
     <img width="400" heigth="400" src="https://user-images.githubusercontent.com/101413385/173164237-1db32d79-2b35-433f-817c-ec3fa30899fc.png">
</p>

Luego haz click en <em>Directory</em> y ubica el directorio del proyecto "clonado" o "extraído" en tu computadora. Haz click en <em>Finish</em> para completar la importación.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/173110215-f9451a5e-a9eb-4056-aec8-6eb3e3601e53.png">
</p>

2 - La segunda forma de importar es en <em>File</em> en la opción <em>Import</em>. O a través del <strong>Project Explorer</strong> haz clic en el campo vacío con el botón derecho del mouse y elijas la opción <strong>Import</strong>.

<p align="center" >
     <img width="400" heigth="400" src="https://user-images.githubusercontent.com/101413385/173111357-2ec928ac-5a3d-4f7c-ba84-8906d84bfd08.png">
</p>

<p align="center" >
     <img width="400" heigth="400" src="https://user-images.githubusercontent.com/101413385/169431325-23a2e3cb-85a3-4298-8e60-64dfa58e2e6f.png">
</p>

Si te decides por el <strong>Import</strong>, se abrirá la ventana correspondiente. Haz clic en la opción <em>Existing Projects Into Workspace</em> y en el botón <em>Next</em>.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/169431890-27f40955-27d8-4b4d-82df-d3507f85de6c.png">
</p>

Luego haz clic en el botón <em>Browse</em> y busca el proyecto en el directorio local.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/169432246-a769555c-daf9-490e-a0c7-908f7e5de967.png">
</p>

## 📅 JCalendar

Tras realizar la importación a tu editor, es necesario instalar la librería <strong>JCalendar</strong>, de lo contrário, el proyecto presentará un error y no será posible abrir la ventana de <strong>Reservas</strong>. </br>

Para instalar, se necesita descargar el paquete a través de ese enlace: 
🔹 [Link para el JCalendar](https://toedter.com/jcalendar/)

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/169592420-7ea798d4-b7d2-4fd6-a03c-75f0fbc2e4bc.png">
</p>

El siguiente paso es extraer los archivos a una carpeta e importar los archivos desde la carpeta <strong>lib</strong> a una carpeta local e importar los archivos a Eclipse.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173167366-664a8876-a5c9-45d1-880d-650fbd6f8b07.png">     
</p>

Archivos de carpeta <strong>lib</strong>:

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173699122-00cc4055-2098-4bba-8d3b-3bd252013116.png">
</p>

Haz click con el botón derecho encima del proyecto ubicado en el <strong>Package Explorer</strong>, elijas la opción <em>Build Path</em> y <em>Configure Build Path</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173167991-44b111ca-c2d6-4d83-b225-33eb2657448f.png">
</p>

El proyecto tendrá un mensaje de error que indica que la ruta de la biblioteca no existe en tu computadora. Haz click en <em>Libraries</em>, luego en <em>Classpath</em> seleccione el archivo JCalendar, elije la opción <em>Remove</em> y <em>Apply and Close</em>.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/173167817-ddbdedc5-c4a6-4f6d-bd86-f86a153d6b88.png">
</p>

Para importar el <strong>Jcalendar</strong> desde tu computadora, después de haber "extraído" los archivos de la descarga, pulsa en <em>ClassPath</em> y luego en <em>Add External JARS</em> agrega uno por uno  o selecciona todos de una vez y haz click en <em>Apply and Close</em>.

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/169596029-a9fa21ef-8d90-45a6-8aa2-be2d911d4074.png">
</p>

Por lo tanto, el proyecto debe contener los siguientes archivos después de las importaciones:

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/101413385/173695620-7a4893e9-b9a1-44d6-8b70-c94a85ea891e.png">
</p>


## 🚧 Caracteres Especiales en el proyecto 

#### Al clonar o descargar el proyecto y tener instalado el JCalendar, tendrás esta presentación al ejecutar el proyecto en Eclipse:

<p align="center" >
     <img width="700" heigth="700" src="https://user-images.githubusercontent.com/91544872/189419249-06b539da-7cf2-4d40-a711-618a5c872096.png">
</p>

### ⚠️ Descargué el proyecto pero los caracteres especiales no funcionan:

Si tu proyecto tiene errores como los de la imagen, siga los pasos a continuación:

<p align="center" >
     <img width="600" heigth="600" src="https://user-images.githubusercontent.com/91544872/189419556-20b67f67-003c-47ac-a0ae-02cf814a6ccb.png">
</p>

Ve a <em>Window</em> y haz clic en <em>Preferences</em>.

<p align="center" >
     <img width="200" heigth="200" src="https://user-images.githubusercontent.com/101413385/173693126-8e2fec8b-91b1-4007-bbc5-010bb454f440.png">
</p>

Luego haz clic en <em>General</em> y en <em>Workspace</em>. Si tu <em>Text File Enconding</em> no está con el <strong>Default (UTF-8)</strong>, haz clic en <em>Other</em>, y eliges la opción <strong>UTF-8</strong>, y finaliza haciendo clic <em>Apply and Close</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173691963-1e3d966e-5162-4393-9232-d5d395d5440f.png">
</p>

## 📊 Base de Dados

### ¿Cómo importar MySqlConnector al proyecto?

Es la misma ruta descrita para importar el <strong>JCalendar</strong>, haga clic con el botón derecho en el proyecto, <em>Build Path</em>, <em>Configure Build Path</em>, <em>Libraries</em>, <em>Add External JARs</em>. Para encontrar la ruta de este archivo <strong>.jar</strong>, ve al disco duro de tu computadora e ingresa a la carpeta  <em>Archivos de Programas (x86)</em>.

- Archivos de Programas (x86):
<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169394-a67b528a-c8b7-4f7a-b374-a1da81b1cc5d.png">
</p>

- MySQL
<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169551-b32a9978-3a05-4b6e-b077-f586d0c41e31.png">
</p>

- Connector J 8.0:

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169584-48e21a03-e70f-4a65-9197-448a3645f526.png">
</p>

- My SQL Connector Java:
<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173169737-5f93868e-df8f-4177-90ba-faf9570758ac.png">
</p>

### Modelado de tablas:

Tablas:<strong>Usuarios</strong>, <strong>Reservas</strong>, <strong>Huéspedes</strong> y 
<strong>Tipos de Reservas</strong>. 
La tabla de huéspedes debe contener la clave externa <em>(foreign key)</em> <strong>reserva_ID</strong>, 
y la tabla de reservas debe contener la clave externa <em>(foreign key)</em> <strong>tipo_ocupacionId</strong>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/48891637/236731657-2a861907-66b8-427c-9303-acb52f85bf4a.png">
</p>

## 🗔 Plugin WindowBuilder

En el menú de <strong>Eclipse</strong>, ir a la pestaña <em>Help</em> y seleccione la opción <em>Eclipse Marketplace</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173254683-a4d27b04-3994-4c7b-bf70-6e1b665a5452.png">
</p>

En la barra de búsqueda, ingresa <strong>window builder</strong> y haz click en <em>Go</em>. Después de la búsqueda, selecciona la primera opción y haz click en <em>Install</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173254936-b1ed41ba-af1b-47ae-bf75-00de4a22ff19.png">
</p>

En la siguiente ventana, selecciona el botón <em>Confirm</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173255237-94789460-0544-4dd3-b00d-2738b83bd575.png">
</p>

Haz click en la opción para aceptar los Términos de Uso, y para finalizar haz clic en <em>Finish</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173255298-05ac80f1-3e93-4097-90af-7fc8a4fa1611.png">
</p>

Después de la instalación, será necesario reiniciar el <strong>Eclipse</strong>.

### ¿Cómo abrir cada ventana de diseño?

Una vez que completes el desafío, si quieres explorar el <em>Window Builder</em> y agregar nuevas ventanas, o cambiar el diseño de las existentes, haz clic en el archivo <strong>.java</strong>, y luego en <em>Open With</em> y finalmente en <em>Window Builder Editor</em>.

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173681973-5699a164-b66d-49f5-87c1-5436dd425457.png">
</p>

La pestaña de <em>Design</em> se abrirá por encima de la zona del <em>Console</em>. ¡Aquí puedes liberar al artista que llevas dentro de ti!

<p align="center" >
     <img width="500" heigth="500" src="https://user-images.githubusercontent.com/101413385/173682648-4c371224-fe4b-4e57-a3ee-9298a4d44554.png">
</p>

## Características de la aplicación y demostración

### LogIn Usuario
https://user-images.githubusercontent.com/48891637/236731148-55fe9678-d5be-4cbe-9318-9f58acd38875.mp4

### Registrar Reservas y Huesped
https://user-images.githubusercontent.com/48891637/236731282-4e0ce879-5b8c-4c27-9495-6c13e62e4e31.mp4

### Busqueda
https://user-images.githubusercontent.com/48891637/236731338-cbe278e5-2bcf-4606-ad1c-d27966b31398.mp4

### Editar
https://user-images.githubusercontent.com/48891637/236731383-0840c181-84b0-4d5f-b678-f2d82cc1ecf5.mp4

https://user-images.githubusercontent.com/48891637/236731415-8d598ea5-8e19-48d8-aff9-e73af07e7f0e.mp4

### Eliminar
https://user-images.githubusercontent.com/48891637/236731467-e54e4f33-ee10-47aa-9d8d-e3edd4f71a2d.mp4

## Personas Desarrolladores del Proyecto

[<img src="https://avatars.githubusercontent.com/u/48891637?v=4" width=115>
<br>
<sub>
Randolfo Fermin
</sub>](https://github.com/Randyfermin)

## Licencia
[JavaConvertidor Liencia MIT ](https://github.com/Randyfermin/JavaConvertidor/blob/main/License.txt)


🧡 <strong>Oracle</strong></br>
<a href="https://www.linkedin.com/company/oracle/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

💙 <strong>Alura Latam</strong></br>
<a href="https://www.linkedin.com/company/alura-latam/mycompany/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
