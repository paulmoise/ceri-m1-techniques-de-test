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
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);

        int actualSize = pokedexImpl.size();
        int expectedSize = 0;
        assertEquals(actualSize, expectedSize);
    }

    @Test
    public void testSizeWithAddCall() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);
        pokedexImpl.addPokemon(new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75));

        int actualSize = pokedexImpl.size();
        int expectedSize = 1;
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testAddPokemon() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);

        Pokemon pokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);

        int actualIndex = pokedexImpl.addPokemon(pokemon);
        int expectedIndex = 25;
        assertEquals(actualIndex, expectedIndex);
    }

    @Test
    public void testAddPokemonAndSizeCheck() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);
        Pokemon pokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);

        int actualIndex = pokedexImpl.addPokemon(pokemon);
        int expectedIndex = 25;
        assertEquals(1, pokedexImpl.size());
        assertEquals(actualIndex, expectedIndex);
    }

    @Test
    void testGetPokemonSuccess() throws PokedexException {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);

        Pokemon expectedPokemon = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);
        pokedexImpl.addPokemon(expectedPokemon);

        Pokemon retrievedPokemon = pokedexImpl.getPokemon(25);
        assertEquals(expectedPokemon, retrievedPokemon);
    }

    @Test
    void testGetPokemonFailure() throws PokedexException {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);
        Executable executable = () -> pokedexImpl.getPokemon(-1);
        assertThrows(PokedexException.class, executable);
    }

    @Test
    void givenInvalidIndexExceptionThrows() throws PokedexException {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);
        int invalidIndex = -1;
        assertThrows(PokedexException.class, () -> pokedexImpl.getPokemon(invalidIndex));
    }


    @Test
    void testGetPokemonsReturnsCorrectList() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);

        List<Pokemon> dummyPokemons = Arrays.asList(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7),
                new Pokemon(2, "Ivysaur", 60, 62, 63, 405, 60, 80, 80, 0.7),
                new Pokemon(3, "Venusaur", 80, 82, 83, 525, 80, 100, 100, 1.0)
        );

        for (Pokemon pokemon : dummyPokemons) {
            pokedexImpl.addPokemon(pokemon);
        }

        List<Pokemon> pokemons = pokedexImpl.getPokemons();

        assertNotNull(pokemons, "The returned list should not be null.");
        assertEquals(4, pokemons.size(), "The returned list should contain 4 Pokémon.");
        assertEquals(dummyPokemons, pokemons, "The returned Pokémon list should match the dummy data.");
    }


    @Test
    void testUnmodifiableGetPokemonsList() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);


        List<Pokemon> dummyPokemons = Collections.unmodifiableList(Arrays.asList(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75),
                new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7)));


        List<Pokemon> pokemons = pokedexImpl.getPokemons();
        assertThrows(UnsupportedOperationException.class, () -> dummyPokemons.add(
                new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75)));
    }

    @Test
    void testGetPokemonsSortedAndUnmodifiable() {
        IPokedex pokedexImpl = new IPokedexImpl(metadataProvider, pokemonFactory);

        Pokemon pokemon1 = new Pokemon(1, "Bulbasaur", 45, 49, 49, 318, 45, 65, 65, 0.7);
        Pokemon pokemon2 = new Pokemon(2, "Ivysaur", 60, 62, 63, 405, 60, 80, 80, 0.7);
        Pokemon pokemon3=  new Pokemon(3, "Venusaur", 80, 82, 83, 525, 80, 100, 100, 1.0);
        Pokemon pokemon4 = new Pokemon(25, "Pikachu", 55, 40, 35, 431, 35, 100, 25, 0.75);

        List<Pokemon> sortedPokemons = Arrays.asList(
                pokemon1, pokemon2, pokemon3, pokemon4
        );

        List<Pokemon> unSortedPokemons = Arrays.asList(
                pokemon4, pokemon1, pokemon2, pokemon3
        );

        for (Pokemon pokemon : unSortedPokemons) {
            pokedexImpl.addPokemon(pokemon);
        }

        Comparator<Pokemon> orderByIndex = Comparator.comparingInt(Pokemon::getIndex);

        List<Pokemon> actualList = pokedexImpl.getPokemons(orderByIndex);

        assertEquals(sortedPokemons, actualList, "The returned list of Pokémon should be sorted by index.");
    }

}
