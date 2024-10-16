package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {
	
	IPokemonTrainerFactory TrainerFactory;
	PokemonTrainer trainer;
	IPokedexFactory pokedexFactory;
	Team myTeam;
	
	@Before
    public void init() {
		
		TrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
		trainer = Mockito.mock(PokemonTrainer.class);
		pokedexFactory = Mockito.mock(IPokedexFactory.class);
    }
    
    @Test
    public void testCreateTrainer() {
    	when(TrainerFactory.createTrainer("sacha", myTeam, pokedexFactory)).thenReturn(trainer);

    	assertEquals(trainer, TrainerFactory.createTrainer("sacha", myTeam, pokedexFactory));
        verify(TrainerFactory, times(1)).createTrainer("sacha", myTeam, pokedexFactory);
    }
}
