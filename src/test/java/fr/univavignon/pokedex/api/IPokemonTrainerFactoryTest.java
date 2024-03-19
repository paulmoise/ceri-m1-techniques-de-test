package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {

    private IPokedexFactory pokedexFactory;
    private IPokemonMetadataProviderImpl metadataProvider;
    private IPokemonFactoryImpl pokemonFactory;
    private IPokemonTrainerFactory trainerFactory;

    @BeforeEach
    void setUp() {
        pokedexFactory = mock(IPokedexFactory.class);
        metadataProvider = mock(IPokemonMetadataProviderImpl.class);
        pokemonFactory = mock(IPokemonFactoryImpl.class);

        trainerFactory = new IPokemonTrainerFactoryImpl(metadataProvider, pokemonFactory);
    }

    @Test
    void testCreateTrainer() {
        IPokedex pokedex = mock(IPokedex.class);
        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);

        String expectedName = "Ash";
        Team expectedTeam = Team.MYSTIC;
        PokemonTrainer trainer = trainerFactory.createTrainer(expectedName, expectedTeam, pokedexFactory);

        assertNotNull(trainer, "Trainer should not be null.");
        assertEquals(expectedName, trainer.getName(), "Trainer's name should match the expected name.");
        assertEquals(expectedTeam, trainer.getTeam(), "Trainer's team should match the expected team.");
        assertNotNull(trainer.getPokedex(), "Trainer should have a Pokedex.");
    }
}
