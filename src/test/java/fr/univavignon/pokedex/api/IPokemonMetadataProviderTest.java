package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IPokemonMetadataProviderTest {

    PokemonMetadata bulbizarreMetadata;
    PokemonMetadata aqualiMetadata;

    IPokemonMetadataProvider pokemonMetadataProvider;

    @Before
    public void initialisationPokemon() throws PokedexException {
    	bulbizarreMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
    	aqualiMetadata = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    	
    	pokemonMetadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    	
    	Mockito.when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(bulbizarreMetadata);
        Mockito.when(pokemonMetadataProvider.getPokemonMetadata(133)).thenReturn(aqualiMetadata);
        Mockito.when(pokemonMetadataProvider.getPokemonMetadata(7)).thenThrow(new PokedexException("Erreur pokemon inexistant"));
    }


    @Test
    public void shouldReturnPokemonMetadata_Index() throws PokedexException
    {
        assertEquals(bulbizarreMetadata, pokemonMetadataProvider.getPokemonMetadata(0));
        assertEquals(aqualiMetadata, pokemonMetadataProvider.getPokemonMetadata(133));
    }
    
    @Test
    public void shouldReturnPokedexException_WhenIndex(){
        PokedexException pe = assertThrows(PokedexException.class, () ->{
        	pokemonMetadataProvider.getPokemonMetadata(7);
        });
    }


}