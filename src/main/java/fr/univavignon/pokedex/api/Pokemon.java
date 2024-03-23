package fr.univavignon.pokedex.api;

/**
 * Represents a Pokemon with its attributes.
 * Extends PokemonMetadata to inherit basic metadata.
 *
 * @author fv
 */
public final class Pokemon extends PokemonMetadata {

  /**
   * Combat Point (CP) of the Pokemon.
   **/
  private final int cp;

  /**
   * Hit Points (HP) of the Pokemon.
   **/
  private final int hp;

  /**
   * Required Stardust for upgrading this Pokemon.
   **/
  private final int dust;

  /**
   * Required Candy for upgrading this Pokemon.
   **/
  private final int candy;

  /**
   * Individual Value (IV) representing perfection percentage.
   **/
  private final double iv;

  /**
   * Constructs a new Pokemon with the specified attributes.
   *
   * @param index   The index of the Pokemon.
   * @param name    The name of the Pokemon.
   * @param attack  The attack level of the Pokemon.
   * @param defense The defense level of the Pokemon.
   * @param stamina The stamina level of the Pokemon.
   * @param cp      The Combat Point (CP) of the Pokemon.
   * @param hp      The Hit Points (HP) of the Pokemon.
   * @param dust    The required Stardust for upgrading this Pokemon.
   * @param candy   The required Candy for upgrading this Pokemon.
   * @param iv      The Individual Value (IV) representing perfection percentage.
   */
  public Pokemon(
      final int index,
      final String name,
      final int attack,
      final int defense,
      final int stamina,
      final int cp,
      final int hp,
      final int dust,
      final int candy,
      final double iv) {
    super(index, name, attack, defense, stamina);
    this.cp = cp;
    this.hp = hp;
    this.dust = dust;
    this.candy = candy;
    this.iv = iv;
  }

  /**
   * Retrieves the Combat Point (CP) of the Pokemon.
   *
   * @return The Combat Point (CP) of the Pokemon.
   */
  public int getCp() {
    return cp;
  }

  /**
   * Retrieves the Hit Points (HP) of the Pokemon.
   *
   * @return The Hit Points (HP) of the Pokemon.
   */
  public int getHp() {
    return hp;
  }

  /**
   * Retrieves the required Stardust for upgrading this Pokemon.
   *
   * @return The required Stardust for upgrading this Pokemon.
   */
  public int getDust() {
    return dust;
  }

  /**
   * Retrieves the required Candy for upgrading this Pokemon.
   *
   * @return The required Candy for upgrading this Pokemon.
   */
  public int getCandy() {
    return candy;
  }

  /**
   * Retrieves the Individual Value (IV) representing perfection percentage.
   *
   * @return The Individual Value (IV) representing perfection percentage.
   */
  public double getIv() {
    return iv;
  }

}
