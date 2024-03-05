package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.mockito.Mockito.*;


public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        metadataProvider = mock(IPokemonMetadataProvider.class);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata expectedMetadata = new PokemonMetadata(25, "Pikachu", 55, 40, 35);
        when(metadataProvider.getPokemonMetadata(25)).thenReturn(expectedMetadata);

        PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(25);

        assertEquals(expectedMetadata, actualMetadata);
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        int invalidIndex = -1;
        when(metadataProvider.getPokemonMetadata(invalidIndex)).thenThrow(new PokedexException("Invalid index: " + invalidIndex));

        Executable executable = () -> metadataProvider.getPokemonMetadata(invalidIndex);

        assertThrows(PokedexException.class, executable);
    }
}
