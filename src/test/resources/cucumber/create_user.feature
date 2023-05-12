# language: es
Característica: Gestion de usuarios
  Escenario: Crear un usuario correctamente
    Dado un administrador esta en el formulario de creación
    Y el correo no esta asignado a otro usuario
    Cuando relleno el campo correo con usuario@correo.com
    Y relleno el campo nombre con David
    Y relleno el campo primer apellido con Hormigo
    Y pulso el botón de crear usuario
    Entonces se muestra la lista de usuarios
    Y se ha persistido el usuario en la base de datos