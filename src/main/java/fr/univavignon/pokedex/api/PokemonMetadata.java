package fr.univavignon.pokedex.api;

/**
 * Represents metadata for a Pokémon.
 * This class encapsulates information about a Pokémon's index, name, attack level, defense level,
 * and stamina level.
 *
 * @author fv
 */
public class PokemonMetadata {

  /**
   * The index of the Pokémon.
   */
  private final int index;

  /**
   * The name of the Pokémon.
   */
  private final String name;

  /**
   * The attack level of the Pokémon.
   */
  private final int attack;

  /**
   * The defense level of the Pokémon.
   */
  private final int defense;

  /**
   * The stamina level of the Pokémon.
   */
  private final int stamina;

  /**
   * Constructs a new instance of PokemonMetadata.
   *
   * @param index   The index of the Pokémon.
   * @param name    The name of the Pokémon.
   * @param attack  The attack level of the Pokémon.
   * @param defense The defense level of the Pokémon.
   * @param stamina The stamina level of the Pokémon.
   */
  public PokemonMetadata(final int index, final String name, final int attack, final int defense,
                         final int stamina) {
    this.index = index;
    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.stamina = stamina;
  }

  /**
   * Retrieves the index of the Pokémon.
   *
   * @return The index of the Pokémon.
   */
  public int getIndex() {
    return index;
  }

  /**
   * Retrieves the name of the Pokémon.
   *
   * @return The name of the Pokémon.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the attack level of the Pokémon.
   *
   * @return The attack level of the Pokémon.
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Retrieves the defense level of the Pokémon.
   *
   * @return The defense level of the Pokémon.
   */
  public int getDefense() {
    return defense;
  }

  /**
   * Retrieves the stamina level of the Pokémon.
   *
   * @return The stamina level of the Pokémon.
   */
  public int getStamina() {
    return stamina;
  }
}
