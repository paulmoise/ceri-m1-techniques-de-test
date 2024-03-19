package fr.univavignon.pokedex.api;

public class IPokedexFactoryImpl implements IPokedexFactory{
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new IPokedexImpl(metadataProvider, pokemonFactory);
    }
}
