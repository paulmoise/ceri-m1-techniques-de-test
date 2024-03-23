package fr.univavignon.pokedex.api;

/**
 * Represents a Pokemon Trainer with a name, team, and associated Pokedex.
 *
 * @author fv
 */
public class PokemonTrainer {

  /**
   * The name of the trainer.
   **/
  private final String name;

  /**
   * The team of the trainer.
   **/
  private final Team team;

  /**
   * The Pokedex associated with the trainer.
   **/
  private final IPokedex pokedex;

  /**
   * Constructs a new Pokemon Trainer with the specified attributes.
   *
   * @param name    The name of the trainer.
   * @param team    The team of the trainer.
   * @param pokedex The Pokedex associated with the trainer.
   */
  public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
    this.name = name;
    this.team = team;
    this.pokedex = pokedex;
  }

  /**
   * Retrieves the name of the trainer.
   *
   * @return The name of the trainer.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the team of the trainer.
   *
   * @return The team of the trainer.
   */
  public Team getTeam() {
    return team;
  }

  /**
   * Retrieves the Pokedex associated with the trainer.
   *
   * @return The Pokedex associated with the trainer.
   */
  public IPokedex getPokedex() {
    return pokedex;
  }

}
