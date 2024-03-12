package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {

    @Test
    void testCreatePokedex() {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);

        IPokedexFactory factory = mock(IPokedexFactory.class);

        IPokedex iPokedex = mock(IPokedex.class);

        when(factory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(iPokedex);

        IPokedex actualPokedex = factory.createPokedex(metadataProvider, pokemonFactory);

        assertNotNull(actualPokedex, "The created Pokedex should not be null.");
        assertEquals(actualPokedex, iPokedex, "The created Pokedex should match the expected Pokedex.");

    }

    @Test
    void testCreatePokedexWithSpecificMetadataAndPokemon() throws PokedexException {
        // Création des mocks pour IPokemonMetadataProvider et IPokemonFactory
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedexFactory factory = mock(IPokedexFactory.class);
        IPokedex pokedex = mock(IPokedex.class);

        // Configuration des comportements des mocks
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        Pokemon bulbasaur = new Pokemon(0, "Bulbizarre", 126, 126, 90, 431, 35, 100, 25, 0.75);


        when(pokemonFactory.createPokemon(0, 431, 35, 100, 25)).thenReturn(bulbasaur);

        when(factory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
        when(pokedex.size()).thenReturn(1);
        when(pokedex.getPokemon(0)).thenReturn(bulbasaur);

        // Création du Pokedex via la factory
        IPokedex actualPokedex = factory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifications
        assertNotNull(actualPokedex, "The created Pokedex should not be null.");
        assertEquals(1, actualPokedex.size(), "The Pokedex should contain exactly one Pokemon.");
        Pokemon retrievedPokemon = actualPokedex.getPokemon(0);
        assertNotNull(retrievedPokemon, "The retrieved Pokemon should not be null.");
        assertEquals("Bulbizarre", retrievedPokemon.getName(), "The name of the retrieved Pokemon should be Bulbizarre.");
        assertEquals(0.75, retrievedPokemon.getIv(), "The IV of the retrieved Pokemon should be 56%.");
    }


}
