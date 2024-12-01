package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.mockito.Mockito;

public class IPokemonComparatorTest {
	Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon pokemon3;
    
    @Before
    public void setup() {
        // Initialisation de quelques objets Pokemon pour les tests
        pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
        pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.00);
        pokemon3 = pokemon1;
    }
    
    @Test
    public void testComparator() {
    	
    }
}
