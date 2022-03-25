# new feature
# Tags: optional

Feature: Modificar un post
    yo como usuario
    quiero poder modificar mi post
    para poder tenerlo actualizado

  Background:
    Given EL usuario se encuentra en el recurso web

  Scenario: Actualizar el titulo de un post
    When El usuario actualiza el titulo del post con id 2 con el nuevo titulo "Titulo Cambiado"
    Then El sistema mostrara el post con el titulo actualizado

  Scenario: Eliminar el post
    When El usuario hace la peticion para eliminar el post con id 2
    Then El codigo de respuesta de la peticion sera 200