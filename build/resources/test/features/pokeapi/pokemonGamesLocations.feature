# new feature
# Tags: optional

Feature: Obtener los datos de una ubicación de los juegos de pokemon
  yo como usuario
  quiero conocer los datos de una ubicacion dentro del juego
  para saber en donde me encuentro

  Scenario: obtener el nombre de una ubicacion dentro del juego
    Given el usuario se encuentra en la api pokeapi
    When el usuario realiza la consulta para la ubicacion dentro del juego con id 2
    Then se visualizara que el nombre de la ubicacion "eterna-city"