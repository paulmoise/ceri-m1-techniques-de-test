package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    public IPokemonMetadataProvider metadataProvider;
    public IPokemonFactory pokemonFactory;


    @BeforeEach
    public void setUp() {
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
    }


    @Test
    public void testSize() {
        IPokedex pokedexImpl = mock(IPokedex.class);
        when(pokedexImpl.size()).thenReturn(1);

        int actualSize = pokedexImpl.size();
        int expectedSize = 1;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testSizeWithAddCall() {
        IPokedex pokedexImpl = mock(IPokedex.class);
        pokedexImpl.addPokemon(new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75));
        when(pokedexImpl.size()).thenReturn(1);

        int actualSize = pokedexImpl.size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddPokemon() {
        IPokedex pokedexImpl = mock(IPokedex.class);
        Pokemon pokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        when(pokedexImpl.addPokemon(pokemon)).thenReturn(0);

        int actualIndex = pokedexImpl.addPokemon(pokemon);
        int expectedIndex = 0;
        assertEquals(actualIndex, expectedIndex);
    }

    @Test
    public void testAddPokemonAndSizeCheck() {
        IPokedex pokedexImpl = mock(IPokedex.class);
        Pokemon pokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        when(pokedexImpl.addPokemon(pokemon)).thenReturn(0);
        when(pokedexImpl.size()).thenReturn(1);

        int actualIndex = pokedexImpl.addPokemon(pokemon);
        int expectedIndex = 0;
        assertEquals(1, pokedexImpl.size());
        assertEquals(actualIndex, expectedIndex);
    }

    @Test
    void testGetPokemonSuccess() throws PokedexException {
        IPokedex pokedex = mock(IPokedex.class);
        Pokemon expectedPokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        pokedex.addPokemon(expectedPokemon);
        when(pokedex.getPokemon(0)).thenReturn(expectedPokemon);

        Pokemon retrievedPokemon = pokedex.getPokemon(0);
        assertEquals(expectedPokemon, retrievedPokemon);
    }

    @Test
    void testGetPokemonFailure() throws PokedexException {
        IPokedex pokedex = mock(IPokedex.class);
        Executable executable = () -> pokedex.getPokemon(-1); // Invalid index.
        doThrow(new PokedexException("Invalid Index ")).when(pokedex).getPokemon(-1);

        assertThrows(PokedexException.class, executable);
    }

    @Test
    void givenInvalidIndexExceptionThrows() throws PokedexException {
        IPokedex pokedex = mock(IPokedex.class);
        int invalidIndex = -1;
        doThrow(new PokedexException("Invalid Index " + invalidIndex)).when(pokedex).getPokemon(invalidIndex);

        assertThrows(PokedexException.class, () -> pokedex.getPokemon(invalidIndex));
    }


    @Test
    void testGetPokemonsReturnsCorrectList() {
        IPokedex pokedex = mock(IPokedex.class);

        List<Pokemon> dummyPokemons = Arrays.asList(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7),
                new Pokemon(2, "Ivysaur", 60, 62, 63, 405, 60, 80, 80, 0.7),
                new Pokemon(3, "Venusaur", 80, 82, 83, 525, 80, 100, 100, 1.0)
        );

        when(pokedex.getPokemons()).thenReturn(dummyPokemons);

        List<Pokemon> pokemons = pokedex.getPokemons();

        assertNotNull(pokemons, "The returned list should not be null.");
        assertEquals(4, pokemons.size(), "The returned list should contain 4 Pokémon.");
        assertEquals(dummyPokemons, pokemons, "The returned Pokémon list should match the dummy data.");
    }


    @Test
    void testUnmodifiableGetPokemonsList() {
        IPokedex pokedex = mock(IPokedex.class);


        // Return an unmodifiable list to mimic real behavior
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(Arrays.asList(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7))));

        // Attempt to modify the returned list should throw UnsupportedOperationException
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertThrows(UnsupportedOperationException.class, () -> pokemons.add(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75)));
    }

    @Test
    void testGetPokemonsSortedAndUnmodifiable() {
        // Mock the IPokedex interface
        IPokedex pokedex = mock(IPokedex.class);



        // Sort the list by Pokémon index for comparison purposes
        List<Pokemon> sortedPokemons = Arrays.asList(
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7),
                new Pokemon(2, "Ivysaur", 60, 62, 63, 405, 60, 80, 80, 0.7),
                new Pokemon(3, "Venusaur", 80, 82, 83, 525, 80, 100, 100, 1.0),
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75)
        );

        // Define a comparator for sorting by Pokémon index
        Comparator<Pokemon> orderByIndex = Comparator.comparingInt(Pokemon::getIndex);

        // Configure the mock to return the unsorted list, then sort it using the comparator
        when(pokedex.getPokemons(orderByIndex)).thenReturn(sortedPokemons);

        // Call the method under test with the comparator
        List<Pokemon> pokemons = pokedex.getPokemons(orderByIndex);

        // Verify the returned list is sorted
        assertEquals(sortedPokemons, pokemons, "The returned list of Pokémon should be sorted by index.");

        // Verify that the list is unmodifiable by attempting to modify it
        assertThrows(UnsupportedOperationException.class, () -> pokemons.add(new Pokemon(4, "Charmander", 39, 52, 43, 309, 39, 60, 50, 0.6)),
                "The list should be unmodifiable.");

    }

}
