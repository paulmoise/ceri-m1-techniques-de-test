package fr.univavignon.pokedex.api;

/**
 * Implementation of the {@link IPokemonTrainerFactory} interface.
 * This factory is responsible for creating instances of {@link PokemonTrainer}.
 * It creates a {@link PokemonTrainer} instance using the provided name, team, and pokedex factory.
 * Usage example:
 * <pre>{@code
 * IPokemonMetadataProviderImpl metadataProvider = new IPokemonMetadataProviderImpl();
 * IPokemonFactoryImpl pokemonFactory = new IPokemonFactoryImpl();
 * IPokemonTrainerFactoryImpl trainerFactory = new IPokemonTrainerFactoryImpl(metadataProvider, pokemonFactory);
 * PokemonTrainer trainer = trainerFactory.createTrainer("Ash", Team.MYSTIC, new IPokedexFactoryImpl());
 * }</pre>
 *
 * @author paulmoise
 * @see IPokemonTrainerFactory
 * @see PokemonTrainer
 */
public class IPokemonTrainerFactoryImpl implements IPokemonTrainerFactory {

  private final IPokemonMetadataProviderImpl metadataProvider;
  private final IPokemonFactoryImpl pokemonFactory;

  /**
   * Constructs a new IPokemonTrainerFactoryImpl instance with the given metadata provider and Pokemon factory.
   *
   * @param metadataProvider the metadata provider to be used for retrieving Pokemon metadata.
   * @param pokemonFactory   the Pokemon factory to be used for creating Pokemon instances.
   */
  public IPokemonTrainerFactoryImpl(IPokemonMetadataProviderImpl metadataProvider,
                                    IPokemonFactoryImpl pokemonFactory) {
    this.metadataProvider = metadataProvider;
    this.pokemonFactory = pokemonFactory;
  }

  /**
   * Creates a new instance of {@link PokemonTrainer} with the provided name, team, and pokedex factory.
   *
   * @param name           the name of the Pokemon trainer.
   * @param team           the team of the Pokemon trainer.
   * @param pokedexFactory the factory to be used for creating the Pokemon trainer's Pokedex.
   * @return a new instance of {@link PokemonTrainer} with the specified parameters.
   */
  @Override
  public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
    // Creating a Pokedex using the provided factory
    IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

    // Creating and returning a new PokemonTrainer instance
    return new PokemonTrainer(name, team, pokedex);
  }
}
