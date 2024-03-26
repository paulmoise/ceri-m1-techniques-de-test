package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexTest {

  private IPokemonMetadataProvider metadataProvider;
  private IPokemonFactory pokemonFactory;
  private IPokedex pokedex;

  @BeforeEach
  public void setUp() {
    metadataProvider = mock(IPokemonMetadataProvider.class);
    pokemonFactory = mock(IPokemonFactory.class);
    pokedex = new IPokedexImpl(metadataProvider, pokemonFactory);
  }

  @Test
  public void testSizeInitiallyZero() {
    assertEquals(0, pokedex.size(), "Initial Pokedex size should be zero.");
  }

  @Test
  public void testAddPokemonIncreasesSize() {
    pokedex.addPokemon(new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75));
    assertEquals(1, pokedex.size(), "Pokedex size should increase after adding a Pokemon.");
  }

  @Test
  public void testAddPokemonReturnsCorrectIndex() {
    Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    int index = pokedex.addPokemon(pikachu);
    assertEquals(25, index, "Added Pokemon should return correct index.");
  }

  @Test
  public void testGetPokemonReturnsCorrectly() throws PokedexException {
    Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    Pokemon bulbasaur = new Pokemon(14, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7);
    pokedex.addPokemon(pikachu);
    pokedex.addPokemon(bulbasaur);

    Pokemon retrievedPokemon = pokedex.getPokemon(25);
    Pokemon retrievedPokemon2 = pokedex.getPokemon(14);
    assertEquals(pikachu, retrievedPokemon, "Retrieved Pokemon should match the added one.");
    assertEquals(bulbasaur, retrievedPokemon2, "Retrieved Pokemon should match the added one.");
  }

  @Test
  public void testGetPokemonWithInvalidIndexThrowsException() {
    int invalidIndex = -1;
    assertThrows(PokedexException.class, () -> pokedex.getPokemon(invalidIndex),
        "Accessing Pokemon with invalid index should throw exception.");
  }

  @Test
  public void testGetPokemonWithInvalidIndexOutOfRangeThrowsException() {
    int invalidIndex = 151;
    assertThrows(PokedexException.class, () -> pokedex.getPokemon(invalidIndex),
        "Accessing Pokemon with invalid index should throw exception.");
  }

  @Test
  public void testGetNonExistingPokemonIndexThrowsException() {
    int invalidIndex = 20;
    assertThrows(PokedexException.class, () -> pokedex.getPokemon(invalidIndex),
        "There is no Pokemon with this index");
  }

  @Test
  public void testGetPokemonsReturnsAllAddedPokemons() {
    List<Pokemon> expectedPokemons = Arrays.asList(
        new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
        new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7)
    );
    expectedPokemons.forEach(pokedex::addPokemon);
    List<Pokemon> actualPokemons = pokedex.getPokemons();
    assertEquals(expectedPokemons.size(), actualPokemons.size(),
        "The number of returned Pokemons should match the number added.");
    assertTrue(actualPokemons.containsAll(expectedPokemons),
        "The returned Pokemons should match the added Pokemons.");
  }


  @Test
  public void testGetPokemonsSortedSimple() {
    Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7);
    pokedex.addPokemon(pikachu);
    pokedex.addPokemon(bulbasaur);

    List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getIndex));
    assertEquals(Arrays.asList(bulbasaur, pikachu), sortedPokemons,
        "Pokemons should be sorted by index.");
  }


  @Test
  public void testGetPokemonsSortedByAttack() {
    Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 60, 49, 49, 318, 45, 65, 65,
        0.7); // Note: Higher attack than Pikachu
    pokedex.addPokemon(pikachu);
    pokedex.addPokemon(bulbasaur);

    List<Pokemon> sortedByAttack = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getAttack));
    assertEquals(Arrays.asList(pikachu, bulbasaur), sortedByAttack,
        "Pokemons should be sorted by attack.");
  }

  @Test
  public void testMultipleGetPokemonConsistency() throws PokedexException {
    Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    pokedex.addPokemon(pikachu);

    Pokemon firstRetrieval = pokedex.getPokemon(25);
    Pokemon secondRetrieval = pokedex.getPokemon(25);
    assertSame(firstRetrieval, secondRetrieval,
        "Multiple retrievals of the same Pokémon should return the same instance.");
  }

  @Test
  public void testAddDuplicatePokemon() {
    Pokemon pikachu1 = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
    Pokemon pikachu2 = new Pokemon(25, "Pikachu", 55, 40, 35, 432, 36, 100, 26,
        0.76); // Different instance, same index
    pokedex.addPokemon(pikachu1);
    Exception exception = assertThrows(RuntimeException.class, () -> pokedex.addPokemon(pikachu2),
        "Adding a Pokémon with an existing index should throw an exception.");
    assertTrue(exception.getMessage().contains("Duplicate"),
        "Exception message should indicate the issue is with a duplicate Pokémon.");
  }


  @Test
  public void testGetPokemonsSortedByName() {
    Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7);
    Pokemon charmander = new Pokemon(4, "Charmander", 52, 43, 39, 309, 39, 60, 50, 0.7);
    Pokemon squirtle = new Pokemon(7, "Squirtle", 48, 65, 44, 314, 44, 50, 64, 0.7);

    pokedex.addPokemon(squirtle);
    pokedex.addPokemon(charmander);
    pokedex.addPokemon(bulbasaur);

    Comparator<Pokemon> byName = Comparator.comparing(Pokemon::getName);

    List<Pokemon> sortedPokemons = pokedex.getPokemons(byName);

    assertEquals(Arrays.asList(bulbasaur, charmander, squirtle), sortedPokemons,
        "Pokemons should be sorted by name.");
  }


  @Test
  public void testAddPokemon() {
    Pokemon pokemon2 = new Pokemon(
        2, // index
        "Ivysaur", // name
        60, // cp
        62, // hp
        63, // dust
        15, // candy
        45, // capture
        6, // attack
        85, // defense
        0.6 // stamina
    );
    assertEquals(2, pokedex.addPokemon(pokemon2));
  }

  @Test
  public void testGetPokemon() throws PokedexException {
    Pokemon actual = new Pokemon(
        12, // index
        "Charizard", // name
        78, // cp
        84, // hp
        85, // dust
        21, // candy
        55, // capture
        7, // attack
        90, // defense
        1.7 // stamina
    );
    pokedex.addPokemon(actual);
    Pokemon expected = pokedex.getPokemon(12);
    assertEquals(actual, expected);
  }

  @Test
  public void testGetPokemons() throws PokedexException {
    IPokedexImpl pokedex1 = new IPokedexImpl(metadataProvider, pokemonFactory);
    List<Pokemon> pokemonList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Pokemon pokemon = new Pokemon(
          i + 1,
          "Bulbasaur",
          45,
          49,
          49,
          12,
          35,
          5,
          80,
          0.5
      );
      pokemonList.add(pokemon);
      pokedex1.addPokemon(pokemon);
    }
    assertEquals(pokemonList, pokedex1.getPokemons());
  }

  @Test
  public void testGetPokemonsSorted() throws PokedexException {
    List<Pokemon> pokemonList2 = new ArrayList<>();
    IPokedexImpl pokedex2 = new IPokedexImpl(metadataProvider, pokemonFactory);
    Pokemon pokemon1 = new Pokemon(
        1, // index
        "Charmander", // name
        39, // cp
        40, // hp
        41, // dust
        12, // candy
        45, // capture
        4, // attack
        65, // defense
        0.6 // stamina
    );
    pokemonList2.add(pokemon1);
    Pokemon pokemon2 = new Pokemon(
        2, // index
        "Charmeleon", // name
        58, // cp
        62, // hp
        63, // dust
        15, // candy
        45, // capture
        6, // attack
        85, // defense
        0.6 // stamina
    );
    pokemonList2.add(pokemon2);
    Pokemon pokemon3 = new Pokemon(
        3, // index
        "Charizard", // name
        78, // cp
        84, // hp
        85, // dust
        21, // candy
        55, // capture
        7, // attack
        90, // defense
        1.7 // stamina
    );
    pokemonList2.add(pokemon3);
    pokedex2.addPokemon(pokemon1);
    pokedex2.addPokemon(pokemon2);
    pokedex2.addPokemon(pokemon3);

    // Utilisation du comparateur PokemonComparators.NAME
    Comparator<Pokemon> comparator = PokemonComparators.NAME;
    pokemonList2.sort(comparator);
    List<Pokemon> sortedPokemons = pokedex2.getPokemons(comparator);
    assertEquals(pokemonList2, sortedPokemons);

    // Utilisation du comparateur PokemonComparators.INDEX
    comparator = PokemonComparators.INDEX;
    pokemonList2.sort(comparator);
    sortedPokemons = pokedex2.getPokemons(comparator);
    assertEquals(pokemonList2, sortedPokemons);

    // Utilisation du comparateur PokemonComparators.CP
    comparator = PokemonComparators.CP;
    pokemonList2.sort(comparator);
    sortedPokemons = pokedex2.getPokemons(comparator);
    assertEquals(pokemonList2, sortedPokemons);
  }


  @Test
  public void testGetPokemonMetadata() throws PokedexException {
    PokemonMetadata expectedMetadata = new PokemonMetadata(12, "Bulbasaur", 45, 49, 49);
    when(metadataProvider.getPokemonMetadata(12)).thenReturn(expectedMetadata);

    PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(12);

    assertEquals(expectedMetadata, retrievedMetadata);
  }

}
