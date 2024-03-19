package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class IPokedexFactoryTest {

    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokedexFactory pokedexFactory;
    @BeforeEach
    void setUp() {
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);

        pokedexFactory = new IPokedexFactoryImpl();
    }

    @Test
    void testCreatePokedex() {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedexFactoryImpl factory = new IPokedexFactoryImpl();

//        IPokedexFactory factory = mock(IPokedexFactory.class);

        IPokedex actualPokedex = factory.createPokedex(metadataProvider, pokemonFactory);

        assertNotNull(actualPokedex, "The created Pokedex should not be null.");

    }


    @Test
    void testCreatePokedexUsesDependenciesCorrectly() throws PokedexException {

        PokemonMetadata expectedMetadata = new PokemonMetadata(0, "Bulbasaur", 45, 49, 45);
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(expectedMetadata);

        Pokemon expectedPokemon = new Pokemon(0, "Bulbasaur", 45, 49, 45, 100, 100, 100, 0, 1);
        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(expectedPokemon);

        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        assertNotNull(pokedex, "Pokedex should not be null.");

        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata, "Metadata should not be null.");
        assertEquals("Bulbasaur", metadata.getName(), "The name should be Bulbasaur.");
        verify(metadataProvider).getPokemonMetadata(0);

        Pokemon pokemon = pokedex.createPokemon(0, 100, 100, 100, 1);
        assertNotNull(pokemon, "Pokemon should not be null.");
        assertEquals("Bulbasaur", pokemon.getName(), "The name should be Bulbasaur.");
        verify(pokemonFactory).createPokemon(0, 100, 100, 100, 1);
    }




}
