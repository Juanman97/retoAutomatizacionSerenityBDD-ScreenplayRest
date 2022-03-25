# new feature
# Tags: optional

Feature: Obtener la version de un juego por su id
    yo como usuario
    quiero conocer la version de un juego
    para tener mas detalles del juego

  Scenario: obtener la version de un juego
    Given el usuario se encuentra en la api
    When el usuario realiza la consulta para la version del juego con id 5
    Then se visualizara que la version del juego es "silver"