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
    public void testGetPokemonMetadataObjRef() throws PokedexException {
        int index = 0;
        String name = "Bulbizarre";
        int attack = 126;
        int defense = 126;
        int stamina = 90;

        PokemonMetadata expectedMetadata = new PokemonMetadata(index, name, attack, defense, stamina);
        when(metadataProvider.getPokemonMetadata(index)).thenReturn(expectedMetadata);


        PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);

        assertEquals(expectedMetadata, actualMetadata);
    }


    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        int index = 0;
        String name = "Bulbizarre";
        int attack = 126;
        int defense = 126;
        int stamina = 90;

        PokemonMetadata metaData = new PokemonMetadata(index, name, attack, defense, stamina);
        when(metadataProvider.getPokemonMetadata(index)).thenReturn(metaData);


        PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);
        assertNotNull(actualMetadata, "Metadata should not be null");

        assertEquals(0, actualMetadata.getIndex(), "Index should be 0");
        assertEquals("Bulbizarre", actualMetadata.getName(), "Name should be Bulbasaur");
        assertEquals(126, actualMetadata.getAttack(), "Attack should be 118");
        assertEquals(126, actualMetadata.getDefense(), "Defense should be 111");
        assertEquals(90, actualMetadata.getStamina(), "Stamina should be 128");
    }

    @Test
    public void testGetPokemonMetadataInvalidIndex() throws PokedexException {
        int invalidIndex = -1;

        when(metadataProvider.getPokemonMetadata(invalidIndex)).thenThrow(new PokedexException("Invalid index: " + invalidIndex));

        Executable executable = () -> metadataProvider.getPokemonMetadata(invalidIndex);

        assertThrows(PokedexException.class, executable);
    }

    @Test
    public void testGetPokemonMetadataIndexOutBound() throws PokedexException {
        int invalidIndex = -1;

        when(metadataProvider.getPokemonMetadata(invalidIndex)).thenThrow(new PokedexException("Invalid index: " + invalidIndex));

        Executable executable = () -> metadataProvider.getPokemonMetadata(invalidIndex);

        assertThrows(PokedexException.class, executable);
    }


}
