package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        pokemonFactory = mock(IPokemonFactory.class);
    }

    @Test
    public void testCreatePokemon() {
        int index = 15;
        String name = "Oshawott";
        int attack = 55;
        int defense = 40;
        int stamina = 35;
        int cp = 100;
        int hp = 50;
        int dust = 100;
        int candy = 25;
        double iv = 0.75;

        Pokemon expectedPokemon = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
        when(pokemonFactory.createPokemon(index, cp, hp, dust, candy)).thenReturn(expectedPokemon);

        Pokemon actualPokemon = pokemonFactory.createPokemon(index, cp, hp, dust, candy);
        assertEquals(expectedPokemon, actualPokemon);
    }
}
