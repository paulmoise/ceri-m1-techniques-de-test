package fr.univavignon.pokedex.api;

public class IPokemonFactoryImpl implements IPokemonFactory	{


	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		double iv = 0.75;
		String name = "Bulbasaur";
		int attack = 126;
		int defense = 126;
		int stamina = 90;

		return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
	}
}
