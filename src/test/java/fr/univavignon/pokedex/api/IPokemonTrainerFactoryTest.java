package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokemonTrainerFactoryTest {


    @Test
    void testCreateTrainer() {
        // Mock the dependencies
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);
        IPokedex pokedex = mock(IPokedex.class);


        IPokemonTrainerFactory trainerFactory = mock(IPokemonTrainerFactory.class);
        when(trainerFactory.createTrainer("Ash", Team.MYSTIC, pokedexFactory)).thenReturn(new PokemonTrainer("Ash", Team.MYSTIC, pokedex));

        PokemonTrainer trainer = new PokemonTrainer("Ash", Team.MYSTIC, pokedex);

        assertEquals("Ash", trainer.getName(), "The trainer's name should match the provided name.");
        assertEquals(Team.MYSTIC, trainer.getTeam(), "The trainer's team should match the provided team.");

        assertNotNull(trainer.getPokedex(), "The trainer should have a Pokedex.");
    }
}
