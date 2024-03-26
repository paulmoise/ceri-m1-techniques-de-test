package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexTest {

  private IPokedex pokedex;
  private IPokemonMetadataProvider pokemonMetadataProvider;
  private IPokemonFactory pokemonFactory;

  @BeforeEach
  public void setUp() throws PokedexException {
    pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
    pokemonFactory = mock(IPokemonFactory.class);
    pokedex = new IPokedexImpl(pokemonMetadataProvider, pokemonFactory);
    Pokemon pokemon1 = new Pokemon(
        1, // index
        "Bulbasaur", // name
        45, //
        49, //
        49, //
        12, //
        35, //
        5, //
        80, //
        0.5
    );
    pokedex.addPokemon(pokemon1);
  }

  @Test
  public void testSize() throws PokedexException {
    assertEquals(1, pokedex.size());
  }

  @Test
  public void testAddPokemon() throws PokedexException {
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
    IPokedexImpl pokedex1 = new IPokedexImpl(pokemonMetadataProvider, pokemonFactory);
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
    IPokedexImpl pokedex2 = new IPokedexImpl(pokemonMetadataProvider, pokemonFactory);
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
    when(pokemonMetadataProvider.getPokemonMetadata(12)).thenReturn(expectedMetadata);

    PokemonMetadata retrievedMetadata = pokedex.getPokemonMetadata(12);

    assertEquals(expectedMetadata, retrievedMetadata);
  }

  @Test
  public void testGetNonExistingPokemon() {
    int nonExistingId = 1000;

    PokedexException exception = assertThrows(PokedexException.class, () -> {
      pokedex.getPokemon(nonExistingId);
    });

    assertEquals("Invalid ID", exception.getMessage());
  }
}