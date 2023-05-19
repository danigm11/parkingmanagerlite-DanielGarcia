# language: es
Característica: Gestion de sorteos
  
  Escenario: Mostrar lista de sorteos
    Dado un usuario esta en la pagina lista de sorteos
    Entonces esta en la pagina de lista de sorteos 

  Escenario: Mostrar formulario de sorteos
    Dado un usuario esta en la pagina creación de sorteos
    Entonces esta en la pagina de creación de sorteos

  Escenario: Navegación a la página lista de sorteos
    Dado un usuario esta en la pagina inicial
    Cuando el usuario hace click sobre el botón de Sorteos
    Entonces esta en la pagina de lista de sorteos

  Escenario: Navegación a formulario de sorteos
    Dado un usuario esta en la pagina lista de sorteos
    Cuando el usuario hace click sobre el botón de formulario sorteos
    Entonces esta en la pagina de creación de sorteos

  Escenario: Comprobar que el formulario de creación de sorteos tiene todos los elementos
    Dado un usuario esta en la pagina creación de sorteos
    Entonces se muestra un campo de descripcion
    Y se muestra un botón de crear sorteo

  Escenario: Crear un sorteo correctamente
    Dado un usuario esta en la pagina creación de sorteos
    Y relleno el campo descripcion con sorteo1
    Y el usuario hace click sobre el botón de crear sorteo
    Entonces esta en la pagina de lista de sorteos
    Y se ha persistido el sorteo en la base de datos