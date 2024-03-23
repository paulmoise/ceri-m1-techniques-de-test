package fr.univavignon.pokedex.api;

/**
 * Implementation of the {@link IPokemonFactory} interface.
 * This factory is responsible for creating instances of {@link Pokemon}.
 * It creates a {@link Pokemon} instance using the provided parameters such as index, CP, HP, dust, and candy.
 * <p>
 * The default values for the Pokemon's name, attack, defense, stamina, and IV are set internally.
 * </p>
 *
 * @author paulmoise
 * @see IPokemonFactory
 * @see Pokemon
 */
public class IPokemonFactoryImpl implements IPokemonFactory {

  /**
   * Creates a new instance of {@link Pokemon} using the provided parameters.
   *
   * @param index the index of the Pokemon.
   * @param cp    the combat power (CP) of the Pokemon.
   * @param hp    the hit points (HP) of the Pokemon.
   * @param dust  the amount of stardust required for powering up the Pokemon.
   * @param candy the amount of candy required for evolving the Pokemon.
   * @return a new instance of {@link Pokemon} with the specified parameters.
   */
  @Override
  public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
    // Default values for name, attack, defense, stamina, and IV
    double iv = 0.75;
    String name = "Bulbasaur";
    int attack = 55;
    int defense = 35;
    int stamina = 40;

    // Creating and returning a new Pokemon instance
    return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
  }
}
