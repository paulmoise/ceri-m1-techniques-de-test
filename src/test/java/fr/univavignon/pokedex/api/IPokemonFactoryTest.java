package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IPokemonFactoryTest {

  private IPokemonFactory pokemonFactory;

  @BeforeEach
  public void setUp() {
    pokemonFactory = new IPokemonFactoryImpl();
//    pokemonFactory = new RocketPokemonFactory();
  }

  @Test
  public void testCreatePokemon() {
    int index = 15;
    int cp = 100;
    int hp = 50;
    int dust = 100;
    int candy = 25;

    String expectedName = "Bulbasaur";
    int expectedAttack = 55;
    int expectedDefense = 35;
    int expectedStamina = 40;
    double expectedIv = 0.75;

    Pokemon expectedPokemon =
        new Pokemon(index, expectedName, expectedAttack, expectedDefense, expectedStamina, cp, hp,
            dust, candy, expectedIv);

    Pokemon actualPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);

    assertNotNull(actualPokemon);

    assertEquals(expectedPokemon.getIndex(), actualPokemon.getIndex());
    assertEquals(expectedPokemon.getName(), actualPokemon.getName());
    assertEquals(expectedPokemon.getCp(), actualPokemon.getCp());
    assertEquals(expectedPokemon.getCandy(), actualPokemon.getCandy());
    assertEquals(expectedPokemon.getStamina(), actualPokemon.getStamina());
    assertEquals(expectedPokemon.getDefense(), actualPokemon.getDefense());
  }
}
