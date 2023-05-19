# language: es
Característica: Gestion de usuarios
  
  Escenario: Navegación a la lista de usuarios
    Dado un usuario esta en la pagina inicial
    Cuando el usuario hace click sobre el botón de Usuarios
    Entonces esta en la pagina de lista de usuarios

  
  Escenario: Comprobar que el formulario de creación de usuarios tiene todos los elementos
    Dado un usuario esta en la pagina creación de usuarios
    Entonces se muestra un campo de correo electrónico
    Y se muestra un campo de nombre
    Y se muestra un campo de primer apellido
    Y se muestra un campo de segundo apellido
    Y se muestra un botón de creación

  Escenario: Crear un usuario correctamente
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com no esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con David
    Y relleno el campo primer apellido con Hormigo
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de lista de usuarios
    Y se ha persistido el usuario en la base de datos

  Escenario: Crear un usuario sin nombre
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com no esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y no relleno el campo nombre
    Y relleno el campo primer apellido con Hormigo
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de creación de usuarios
    Y no se ha persistido el usuario en la base de datos

  Escenario: Crear un usuario sin apellido
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com no esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con David
    Y no relleno el campo primer apellido
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de creación de usuarios
    Y no se ha persistido el usuario en la base de datos

  Escenario: Crear un usuario sin email
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com no esta asignado a otro usuario
    Cuando no relleno el campo correo electrónico
    Y relleno el campo nombre con David
    Y relleno el campo primer apellido con Hormigo
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de creación de usuarios
    Y no se ha persistido el usuario en la base de datos

  Escenario: Crear un usuario con email repetido
    Dado un usuario esta en la pagina creación de usuarios
    Y el correo usuario@correo.com si esta asignado a otro usuario
    Cuando relleno el campo correo electrónico con usuario@correo.com
    Y relleno el campo nombre con David
    Y relleno el campo primer apellido con Hormigo
    Y el usuario hace click sobre el botón de crear usuario
    Entonces esta en la pagina de creación de usuarios
    Y no se ha persistido el usuario en la base de datos

  
  