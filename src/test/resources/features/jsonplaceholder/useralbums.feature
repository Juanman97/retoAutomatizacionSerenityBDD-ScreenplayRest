# new feature
# Tags: optional

Feature: Obtener los albumes de un usuario
  Yo como usuario
  quiero obtener la lista de albumes asociados a mi id
  para conocer cuántos tengo

  Scenario: Obtener la lista de albumes de un usuario
    Given El usuario esta en el recurso web
    When El usuario realiza la consulta para el id 2
    Then El sistema mostrara la lista de albumes relacionados con el id 2