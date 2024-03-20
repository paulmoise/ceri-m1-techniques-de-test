package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        metadataProvider = new IPokemonMetadataProviderImpl();
    }

    @Test
    public void testGetPokemonMetadataValidIndex() throws PokedexException {
        int index = 25;
        String expectedName = "Bulbasaur";
        int expectedAttack = 35;
        int expectedDefense = 42;
        int expectedStamina = 90;

        PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);
        assertNotNull(actualMetadata, "Metadata should not be null");

        assertEquals(index, actualMetadata.getIndex());
        assertEquals(expectedName, actualMetadata.getName());
        assertEquals(expectedAttack, actualMetadata.getAttack());
        assertEquals(expectedDefense, actualMetadata.getDefense());
        assertEquals(expectedStamina, actualMetadata.getStamina());
    }

    @Test
    public void testGetPokemonMetadataNonExistentIndex() {
        int invalidIndex = 1000;
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(invalidIndex),
                "Attempting to retrieve metadata for a non-existent Pokémon index should throw a PokedexException.");
    }

    @Test
    public void testGetPokemonMetadataAnotherValidIndex() throws PokedexException {
        int index = 35;
        String expectedName = "pokemon2";
        int expectedAttack = 384;
        int expectedDefense = 29;
        int expectedStamina = 500;

        PokemonMetadata actualMetadata = metadataProvider.getPokemonMetadata(index);
        assertNotNull(actualMetadata, "Metadata for a valid index should not be null");

        assertEquals(index, actualMetadata.getIndex());
        assertEquals(expectedName, actualMetadata.getName());
        assertEquals(expectedAttack, actualMetadata.getAttack());
        assertEquals(expectedDefense, actualMetadata.getDefense());
        assertEquals(expectedStamina, actualMetadata.getStamina());
    }
}
