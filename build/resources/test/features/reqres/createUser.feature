# new feature
# Tags: optional

Feature: Crear usuarios
  Yo como usuario
  quiero poder crear usuarios en la pagina
  para poder tener mi informacion en mi cuenta

  Scenario: Crear un usuario
    Given El cliente se encuentra en el recurso web
    When El cliente crea un usuario con el nombre "Juan Manuel" y su trabajo "Tester"
    Then El sistema mostrara un codigo de respuesta exitoso y los datos del usuario creado