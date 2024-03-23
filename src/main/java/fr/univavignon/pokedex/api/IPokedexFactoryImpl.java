package fr.univavignon.pokedex.api;

/**
 * Implementation of the {@link IPokedexFactory} interface.
 * This factory is responsible for creating instances of {@link IPokedex}.
 * It creates a {@link IPokedex} instance using the provided {@link IPokemonMetadataProvider}
 * and {@link IPokemonFactory}.
 * <p>
 * Usage example:
 * {@code
 * IPokemonMetadataProvider metadataProvider = new SomeMetadataProvider();
 * IPokemonFactory pokemonFactory = new SomePokemonFactory();
 * IPokedexFactory factory = new IPokedexFactoryImpl();
 * IPokedex pokedex = factory.createPokedex(metadataProvider, pokemonFactory);
 * }
 * </p>
 *
 * @author paulmoise
 * @see IPokedexFactory
 * @see IPokedex
 * @see IPokemonMetadataProvider
 * @see IPokemonFactory
 */
public class IPokedexFactoryImpl implements IPokedexFactory {

  /**
   * Creates a new instance of {@link IPokedex} using the provided {@link IPokemonMetadataProvider}
   * and {@link IPokemonFactory}.
   *
   * @param metadataProvider the metadata provider to be used for creating the pokedex.
   * @param pokemonFactory   the pokemon factory to be used for creating the pokedex.
   * @return a new instance of {@link IPokedex}.
   */
  @Override
  public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider,
                                IPokemonFactory pokemonFactory) {
    return new IPokedexImpl(metadataProvider, pokemonFactory);
  }
}
