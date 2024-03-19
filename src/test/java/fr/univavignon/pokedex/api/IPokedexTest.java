package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
        pokedex.addPokemon(pikachu);
        Pokemon retrievedPokemon = pokedex.getPokemon(25);
        assertEquals(pikachu, retrievedPokemon, "Retrieved Pokemon should match the added one.");
    }

    @Test
    public void testGetPokemonWithInvalidIndexThrowsException() {
        int invalidIndex = -1;
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(invalidIndex), "Accessing Pokemon with invalid index should throw exception.");
    }

    @Test
    public void testGetPokemonsReturnsAllAddedPokemons() {
        List<Pokemon> expectedPokemons = Arrays.asList(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7)
        );
        expectedPokemons.forEach(pokedex::addPokemon);
        List<Pokemon> actualPokemons = pokedex.getPokemons();
        assertEquals(expectedPokemons.size(), actualPokemons.size(), "The number of returned Pokemons should match the number added.");
        assertTrue(actualPokemons.containsAll(expectedPokemons), "The returned Pokemons should match the added Pokemons.");
    }

    @Test
    public void testGetPokemonsSorted() {
        Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7);
        pokedex.addPokemon(pikachu);
        pokedex.addPokemon(bulbasaur);

        List<Pokemon> sortedPokemons = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getIndex));
        assertEquals(Arrays.asList(bulbasaur, pikachu), sortedPokemons, "Pokemons should be sorted by index.");
    }
}
