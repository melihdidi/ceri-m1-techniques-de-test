package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IPokemonFactoryTest {
		
	 	Pokemon bulbizarre;
	    Pokemon aquali;
	    IPokemonFactory Pokemon1;
	    IPokemonFactory Pokemon2;

	    @Before
	    public void init() {
	    	Pokemon1 = Mockito.mock(IPokemonFactory.class);
	    	Pokemon2 = Mockito.mock(IPokemonFactory.class);

	    	bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56.00);
	    	aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.00);
	    }
	    
	    @Test
	    public void testCreate() {
	    	when(Pokemon1.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);
	        when(Pokemon2.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(aquali);

	    	assertEquals(bulbizarre, Pokemon1.createPokemon(0, 613, 64, 4000, 4));
	        verify(Pokemon1, times(1)).createPokemon(0, 613, 64, 4000, 4);
	        
	    	assertEquals(aquali, Pokemon2.createPokemon(133, 2729, 202, 5000, 4));
	        verify(Pokemon2, times(1)).createPokemon(133, 2729, 202, 5000, 4);
	    }
	    
}
