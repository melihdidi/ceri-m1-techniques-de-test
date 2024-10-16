package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {
	IPokedexFactory pokedexFactory;
	IPokedex pokedex;
	IPokemonMetadataProvider metadataProvider;
	IPokemonFactory pokemonFactory;

    @Before
    public void init() {
    	pokedex = Mockito.mock(IPokedex.class);
    	metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    	pokemonFactory = Mockito.mock(IPokemonFactory.class);
    	pokedexFactory = Mockito.mock(IPokedexFactory.class);
    }
    
    @Test
    public void testCreatePokedex() {
    	when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);
        
    	assertEquals(pokedex, pokedexFactory.createPokedex(metadataProvider, pokemonFactory));
        verify(pokedexFactory, times(1)).createPokedex(metadataProvider, pokemonFactory);
    }
}
