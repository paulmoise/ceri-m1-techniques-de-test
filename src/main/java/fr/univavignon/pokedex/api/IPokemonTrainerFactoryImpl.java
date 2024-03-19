package fr.univavignon.pokedex.api;

public class IPokemonTrainerFactoryImpl implements IPokemonTrainerFactory {


    private final IPokemonMetadataProviderImpl metadataProvider;
    private final IPokemonFactoryImpl pokemonFactory;

    public IPokemonTrainerFactoryImpl(IPokemonMetadataProviderImpl metadataProvider, IPokemonFactoryImpl pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        return new PokemonTrainer(name, team, pokedex);
    }
}
