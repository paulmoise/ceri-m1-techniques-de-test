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


    @Test
    public void testGetPokemonsSortedByAttack() {
        Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", 60, 49, 49, 318, 45, 65, 65, 0.7); // Note: Higher attack than Pikachu
        pokedex.addPokemon(pikachu);
        pokedex.addPokemon(bulbasaur);

        List<Pokemon> sortedByAttack = pokedex.getPokemons(Comparator.comparingInt(Pokemon::getAttack));
        assertEquals(Arrays.asList(pikachu, bulbasaur), sortedByAttack, "Pokemons should be sorted by attack.");
    }

    @Test
    public void testMultipleGetPokemonConsistency() throws PokedexException {
        Pokemon pikachu = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        pokedex.addPokemon(pikachu);

        Pokemon firstRetrieval = pokedex.getPokemon(25);
        Pokemon secondRetrieval = pokedex.getPokemon(25);
        assertSame(firstRetrieval, secondRetrieval, "Multiple retrievals of the same Pokémon should return the same instance.");
    }

    @Test
    public void testAddDuplicatePokemon() {
        Pokemon pikachu1 = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        Pokemon pikachu2 = new Pokemon(25, "Pikachu", 55, 40, 35, 432, 36, 100, 26, 0.76); // Different instance, same index
        pokedex.addPokemon(pikachu1);
        Exception exception = assertThrows(RuntimeException.class, () -> pokedex.addPokemon(pikachu2), "Adding a Pokémon with an existing index should throw an exception.");
        assertTrue(exception.getMessage().contains("Duplicate"), "Exception message should indicate the issue is with a duplicate Pokémon.");
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

        assertEquals(Arrays.asList(bulbasaur, charmander, squirtle), sortedPokemons, "Pokemons should be sorted by name.");
    }




}
