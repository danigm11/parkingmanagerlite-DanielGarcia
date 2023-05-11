# language: es
Característica: Gestion de navegacion
  

  Escenario: Navegar a lista de usuarios
    Dado un usuario esta en la pagina inicial
    Cuando el usuario hace click sobre el botón de Usuarios
    Entonces se muestran todos los usuarios del sistema

  Escenario: Navegar a lista de sorteos
    Dado un usuario esta en la pagina inicial
    Cuando el usuario hace click sobre el botón de Sorteos
    Entonces se muestran todos los sorteos del sistema

  Escenario: Navegar a formulario de usuarios
    Dado un usuario esta en la lista de usuarios
    Cuando el usuario hace click sobre el botón de crear Usuarios
    Entonces se muestra el formulario de creación de usuarios

  Escenario: Navegar a formulario de sorteos
    Dado un usuario esta en la lista de sorteos
    Cuando el usuario hace click sobre el botón de crear Sorteos
    Entonces se muestra el formulario de creación de sorteos