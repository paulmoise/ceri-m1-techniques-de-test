package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link IPokedex} interface.
 * This class represents a Pokedex that stores information about captured Pokemon.
 * It provides methods to add, remove, and retrieve Pokemon, as well as other operations.
 * Usage example:
 * <pre>{@code
 * IPokemonMetadataProvider metadataProvider = new SomeMetadataProvider();
 * IPokemonFactory pokemonFactory = new SomePokemonFactory();
 * IPokedex pokedex = new IPokedexImpl(metadataProvider, pokemonFactory);
 * }</pre>
 *
 * @author paulmoise
 * @see IPokedex
 * @see IPokemonMetadataProvider
 * @see IPokemonFactory
 */
public class IPokedexImpl implements IPokedex {

  private final List<Pokemon> pokemons;
  private final IPokemonMetadataProvider pokemonMetadaProvider;
  private final IPokemonFactory pokemonFactory;

  /**
   * Constructs a new IPokedexImpl instance with the given metadata provider and Pokemon factory.
   *
   * @param pokemonMetadaProvider the metadata provider to be used for retrieving Pokemon metadata.
   * @param pokemonFactory        the Pokemon factory to be used for creating Pokemon instances.
   */
  public IPokedexImpl(IPokemonMetadataProvider pokemonMetadaProvider,
                      IPokemonFactory pokemonFactory) {
    this.pokemonMetadaProvider = pokemonMetadaProvider;
    this.pokemonFactory = pokemonFactory;
    this.pokemons = new ArrayList<>();
  }

  /**
   * Returns the number of Pokemon stored in the Pokedex.
   *
   * @return the number of Pokemon in the Pokedex.
   */
  @Override
  public int size() {
    return this.pokemons.size();
  }

  /**
   * Adds a new Pokemon to the Pokedex.
   *
   * @param pokemon the Pokemon to be added.
   * @return the index of the added Pokemon.
   * @throws RuntimeException if a Pokemon with the same index already exists in the Pokedex.
   */
  @Override
  public int addPokemon(Pokemon pokemon) {
    Optional<Pokemon> existingPokemon = pokemons.stream()
        .filter(p -> p.getIndex() == pokemon.getIndex())
        .findFirst();

    if (existingPokemon.isPresent()) {
      throw new RuntimeException("Duplicate: Pokemon with index " + pokemon.getIndex() +
          " already exists in the Pokedex.");
    }

    pokemons.add(pokemon);
    return pokemon.getIndex();
  }

  /**
   * Retrieves the Pokemon with the specified ID from the Pokedex.
   *
   * @param id the ID of the Pokemon to retrieve.
   * @return the Pokemon with the specified ID.
   * @throws PokedexException if the ID is invalid or the Pokemon does not exist in the Pokedex.
   */
  @Override
  public Pokemon getPokemon(int id) throws PokedexException {
    if (id < 0 || id > 150) {
      throw new PokedexException("Invalid ID");
    } else {
      for (Pokemon pokemon : pokemons) {
        if (pokemon.getIndex() == id) {
          return pokemon;
        }
      }
      throw new PokedexException("Ce pokemon n'existe pas");
    }
  }

  /**
   * Retrieves all the Pokemon stored in the Pokedex.
   *
   * @return a list containing all the Pokemon in the Pokedex.
   */
  @Override
  public List<Pokemon> getPokemons() {
    return pokemons;
  }

  /**
   * Retrieves all the Pokemon stored in the Pokedex, sorted using the specified comparator.
   *
   * @param order the comparator used to sort the Pokemon.
   * @return a list containing all the Pokemon in the Pokedex, sorted using the specified comparator.
   */
  @Override
  public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
    List<Pokemon> result = pokemons;
    result.sort(order);
    return result;
  }

  /**
   * Creates a new Pokemon instance with the specified parameters.
   *
   * @param index the index of the Pokemon.
   * @param cp    the combat power (CP) of the Pokemon.
   * @param hp    the hit points (HP) of the Pokemon.
   * @param dust  the stardust cost of the Pokemon.
   * @param candy the candy cost of the Pokemon.
   * @return a new instance of {@link Pokemon} with the specified parameters.
   */
  @Override
  public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
    return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
  }

  /**
   * Retrieves the metadata of the Pokemon with the specified index.
   *
   * @param index the index of the Pokemon.
   * @return the metadata of the Pokemon with the specified index.
   * @throws PokedexException if the metadata for the specified Pokemon index cannot be retrieved.
   */
  @Override
  public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
    return pokemonMetadaProvider.getPokemonMetadata(index);
  }
}
