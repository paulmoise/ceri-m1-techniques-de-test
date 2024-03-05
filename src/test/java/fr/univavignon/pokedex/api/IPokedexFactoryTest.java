package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

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


}
