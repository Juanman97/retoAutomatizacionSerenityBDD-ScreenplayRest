# new feature
# Tags: optional

Feature: Actualizar el titulo de un post
    yo como usuario
    quiero poder cambiar el titulo de un post
    para poder actualizar su informacion

  Scenario: Modificar el titulo de un post
    Given El usuario esta en el recurso web indicando el nuevo titulo "Titulo Cambiado"
    When El usuario actualiza el titulo del post con id 2
    Then El sistema mostrara el post con el titulo actualizado