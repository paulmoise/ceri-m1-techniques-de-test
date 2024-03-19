package fr.univavignon.pokedex.api;


import java.util.ArrayList;
import java.util.List;

public class IPokemonMetadataProviderImpl implements IPokemonMetadataProvider {

    /**
     * The Pokemon metadata list.
     */
    private List<PokemonMetadata> pokemonMetadataList;

    /**
     * The constant attack1.
     */
    public static final int ATTACK_ONE = 4;
    /**
     * The constant defense1.
     */
    public static final int DEFENSE_1 = 4;
    /**
     * The constant stamina1.
     */
    public static final int STAMINA_1 = 4;
    /**
     * The constant attack2.
     */
    public static final int ATTACK_2 = 384;
    /**
     * The constant defense2.
     */
    public static final int DEFENSE_2 = 29;
    /**
     * The constant stamina2.
     */
    public static final int STAMINA_2 = 500;

    /**
     * Instantiates a new Pokemon metadata provider.
     */
    public void PokemonMetadataProviderImpl() {

        this.pokemonMetadataList = new ArrayList<>();
        this.pokemonMetadataList.add(
                new PokemonMetadata(0, "pokemon1", ATTACK_ONE, DEFENSE_1,
                        STAMINA_1));
        this.pokemonMetadataList.add(
                new PokemonMetadata(1, "pokemon2", ATTACK_2, DEFENSE_2,
                        STAMINA_2));

    }

    @Override
    public final PokemonMetadata getPokemonMetadata(final int index)
            throws PokedexException {
        for (PokemonMetadata pokemonMetadata : pokemonMetadataList) {
            if (pokemonMetadata.getIndex() == index) {
                return pokemonMetadata;
            }
        }
        throw new PokedexException(
                "Le Pokémon n'est pas enregistré dans le pokédex");
    }
}
