package fr.univavignon.pokedex.api;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonMetadataProviderImpl;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

}