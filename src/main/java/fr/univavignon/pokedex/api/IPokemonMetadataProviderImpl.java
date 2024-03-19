package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class IPokemonMetadataProviderImpl implements IPokemonMetadataProvider {

    private static final List<PokemonMetadata> pokemonMetadataList = new ArrayList<>();

    public static final int ATTACK_ONE = 35;
    public static final int DEFENSE_1 = 42;
    public static final int STAMINA_1 = 90;
    public static final int ATTACK_2 = 384;
    public static final int DEFENSE_2 = 29;
    public static final int STAMINA_2 = 500;

    public IPokemonMetadataProviderImpl() {
        pokemonMetadataList.add(new PokemonMetadata(25, "Bulbasaur", ATTACK_ONE, DEFENSE_1, STAMINA_1));
        pokemonMetadataList.add(new PokemonMetadata(35, "pokemon2", ATTACK_2, DEFENSE_2, STAMINA_2));
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataList.stream()
                .filter(metadata -> metadata.getIndex() == index)
                .findFirst()
                .orElseThrow(() -> new PokedexException("Le Pokémon n'est pas enregistré dans le pokédex"));
    }
}
