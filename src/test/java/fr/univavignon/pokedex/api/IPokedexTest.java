package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    IPokedex pokedex;
    Pokemon pokemon1;
    Pokemon pokemon2;

    @Before
    public void setup() {
        // Mock de l'interface IPokedex
        pokedex = Mockito.mock(IPokedex.class);

        // Initialisation de quelques objets Pokemon pour les tests
        pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
        pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100.00);
    }

    @Test
    public void testSize() {
        // Configuration du mock pour la méthode size()
        when(pokedex.size()).thenReturn(2);

        // Vérification de la taille
        assertEquals(2, pokedex.size());
        verify(pokedex, times(1)).size();
    }

    @Test
    public void testAddPokemon() {
        // Configuration du mock pour la méthode addPokemon()
        when(pokedex.addPokemon(pokemon1)).thenReturn(0);

        // Test de l'ajout
        int index1 = pokedex.addPokemon(pokemon1);
        assertEquals(0, index1);
        int index2 = pokedex.addPokemon(pokemon2);
        assertEquals(0, index2);

        // Verification que addPokemon() a bien été appelée
        verify(pokedex, times(1)).addPokemon(pokemon1);
        verify(pokedex, times(1)).addPokemon(pokemon2);
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Configuration du mock pour retourner un Pokémon par son ID
        when(pokedex.getPokemon(0)).thenReturn(pokemon1);
        when(pokedex.getPokemon(1)).thenReturn(pokemon2);

        // Test de récupération du Pokémon
        Pokemon retrievedPokemon1 = pokedex.getPokemon(0);
        Pokemon retrievedPokemon2 = pokedex.getPokemon(1);
        assertEquals(pokemon1, retrievedPokemon1);
        assertEquals(pokemon2, retrievedPokemon2);

        // Vérification que getPokemon() a bien été appelée avec l'ID correct
        verify(pokedex, times(1)).getPokemon(0);
        verify(pokedex, times(1)).getPokemon(1);
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonInvalidId() throws PokedexException {
        // Simulation d'une exception lorsque l'ID n'est pas valide
        when(pokedex.getPokemon(100)).thenThrow(new PokedexException("Invalid ID"));

        // Appel avec un ID invalide, attend une PokedexException
        pokedex.getPokemon(100);
    }

    @Test
    public void testGetPokemons() {
        // Configuration du mock pour retourner une liste de Pokémon
        List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2);
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pokemons));

        // Test de récupération de la liste
        List<Pokemon> retrievedPokemons = pokedex.getPokemons();
        assertEquals(2, retrievedPokemons.size());
        assertEquals(pokemon1, retrievedPokemons.get(0));
        assertEquals(pokemon2, retrievedPokemons.get(1));

        // Vérification que getPokemons() a bien été appelée
        verify(pokedex, times(1)).getPokemons();
    }

    @Test
    public void testGetPokemonsWithIndex() {
        // Configuration pour une liste triée avec un Comparator
        List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2);
        when(pokedex.getPokemons(PokemonComparators.INDEX))
                .thenReturn(Collections.unmodifiableList(pokemons));

        // Utilisation d'un Comparator qui trie par index pour cet exemple
        List<Pokemon> retrievedPokemons = pokedex.getPokemons(PokemonComparators.INDEX);

        assertEquals(2, retrievedPokemons.size());
        assertEquals(pokemon1, retrievedPokemons.get(0));
        assertEquals(pokemon2, retrievedPokemons.get(1));

        // Vérification que getPokemons(Comparator) a bien été appelée
        verify(pokedex, times(1)).getPokemons(PokemonComparators.INDEX);
    }
    
    @Test
    public void testGetPokemonsWithName() {
        // Configuration pour une liste triée avec un Comparator
        List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2);
        when(pokedex.getPokemons(PokemonComparators.NAME))
                .thenReturn(Collections.unmodifiableList(pokemons));

        // Utilisation d'un Comparator qui trie par Name pour cet exemple
        List<Pokemon> retrievedPokemons = pokedex.getPokemons(PokemonComparators.NAME);

        assertEquals(2, retrievedPokemons.size());
        assertEquals(pokemon1, retrievedPokemons.get(0));
        assertEquals(pokemon2, retrievedPokemons.get(1));

        // Vérification que getPokemons(Comparator) a bien été appelée
        verify(pokedex, times(1)).getPokemons(PokemonComparators.NAME);
    }
    
    @Test
    public void testGetPokemonsWithCP() {
        // Configuration pour une liste triée avec un Comparator
        List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2);
        when(pokedex.getPokemons(PokemonComparators.CP))
                .thenReturn(Collections.unmodifiableList(pokemons));

        // Utilisation d'un Comparator qui trie par CP pour cet exemple
        List<Pokemon> retrievedPokemons = pokedex.getPokemons(PokemonComparators.CP);

        assertEquals(2, retrievedPokemons.size());
        assertEquals(pokemon1, retrievedPokemons.get(0));
        assertEquals(pokemon2, retrievedPokemons.get(1));

        // Vérification que getPokemons(Comparator) a bien été appelée
        verify(pokedex, times(1)).getPokemons(PokemonComparators.CP);
    }
}
