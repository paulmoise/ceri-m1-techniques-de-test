package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link IPokemonMetadataProvider} interface.
 * This class provides metadata information about Pokemon such as their attack, defense, and stamina.
 * It contains a list of pre-defined {@link PokemonMetadata} instances.
 * <p>
 * Usage example:
 * {@code
 * IPokemonMetadataProvider metadataProvider = new IPokemonMetadataProviderImpl();
 * PokemonMetadata metadata = metadataProvider.getPokemonMetadata(25);
 * }
 * </p>
 *
 * @author paulmoise
 * @see IPokemonMetadataProvider
 * @see PokemonMetadata
 */
public class IPokemonMetadataProviderImpl implements IPokemonMetadataProvider {

  /**
   * A list containing metadata for Pokémon.
   * This list stores instances of {@link PokemonMetadata}.
   */
  private static final List<PokemonMetadata> pokemonMetadataList = new ArrayList<>();

  /**
   * Represents the base attack value for the first Pokémon.
   */
  public static final int ATTACK_ONE = 35;

  /**
   * Represents the base defense value for the first Pokémon.
   */
  public static final int DEFENSE_1 = 42;

  /**
   * Represents the base stamina value for the first Pokémon.
   */
  public static final int STAMINA_1 = 90;

  /**
   * Represents the base attack value for the second Pokémon.
   */
  public static final int ATTACK_2 = 384;

  /**
   * Represents the base defense value for the second Pokémon.
   */
  public static final int DEFENSE_2 = 29;

  /**
   * Represents the base stamina value for the second Pokémon.
   */
  public static final int STAMINA_2 = 500;

  /**
   * Initializes the Pokemon metadata provider with pre-defined Pokemon metadata.
   */
  public IPokemonMetadataProviderImpl() {
    // Adding pre-defined Pokemon metadata to the list
    pokemonMetadataList.add(new PokemonMetadata(25, "Bulbasaur", ATTACK_ONE, DEFENSE_1, STAMINA_1));
    pokemonMetadataList.add(new PokemonMetadata(35, "pokemon2", ATTACK_2, DEFENSE_2, STAMINA_2));
  }

  /**
   * Retrieves the metadata of a Pokemon with the given index.
   *
   * @param index the index of the Pokemon.
   * @return the metadata of the Pokemon with the specified index.
   * @throws PokedexException if the Pokemon metadata is not found.
   */
  @Override
  public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
    // Searching for the Pokemon metadata with the specified index
    return pokemonMetadataList.stream()
        .filter(metadata -> metadata.getIndex() == index)
        .findFirst()
        .orElseThrow(() -> new PokedexException("Le Pokémon n'est pas enregistré dans le pokédex"));
  }
}
