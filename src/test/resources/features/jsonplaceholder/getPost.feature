# new feature
# Tags: optional

Feature: Obtener un post por su id
  Yo como usuario
  quiero obtener el post asociado a un id
  para conocer su contenido

  Scenario: obtener la informacion de un post por su id
    Given El usuario esta en el recurso web
    When El usuario realiza la consulta para el id 2
    Then Se visualizara que el post tiene como titulo "qui est esse"