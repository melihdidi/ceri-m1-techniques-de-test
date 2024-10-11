package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    protected IPokedex pokedex;
    protected Pokemon pokemon1;
    protected Pokemon pokemon2;
    protected Pokemon pokemon3;

    @Before
    public void setup() {
        // Mock de l'interface IPokedex
        pokedex = Mockito.mock(IPokedex.class);

        // Initialisation de quelques objets Pokemon pour les tests
        pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56);
        pokemon2 = new Pokemon(1, "Ivysaur", 156, 158, 120, 800, 130, 4500, 6, 85);
        pokemon3 = new Pokemon(2, "Venusaur", 198, 200, 160, 1000, 300, 5000, 7, 115);
    }

    @Test
    public void testSize() {
        // Configuration du mock pour la méthode size()
        when(pokedex.size()).thenReturn(3);

        // Vérification de la taille
        assertEquals(3, pokedex.size());
        verify(pokedex, times(1)).size();
    }

    @Test
    public void testAddPokemon() {
        // Configuration du mock pour la méthode addPokemon()
        when(pokedex.addPokemon(pokemon1)).thenReturn(0);

        // Test de l'ajout
        int index = pokedex.addPokemon(pokemon1);
        assertEquals(0, index);

        // Vérification que addPokemon() a bien été appelée
        verify(pokedex, times(1)).addPokemon(pokemon1);
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Configuration du mock pour retourner un Pokémon par son ID
        when(pokedex.getPokemon(1)).thenReturn(pokemon2);

        // Test de récupération du Pokémon
        Pokemon retrievedPokemon = pokedex.getPokemon(1);
        assertEquals("Ivysaur", retrievedPokemon.getName());
        assertEquals(1, retrievedPokemon.getIndex());

        // Vérification que getPokemon() a bien été appelée avec l'ID correct
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
        List<Pokemon> pokemons = Arrays.asList(pokemon1, pokemon2, pokemon3);
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(pokemons));

        // Test de récupération de la liste
        List<Pokemon> retrievedPokemons = pokedex.getPokemons();
        assertEquals(3, retrievedPokemons.size());
        assertEquals("Bulbasaur", retrievedPokemons.get(0).getName());
        assertEquals("Ivysaur", retrievedPokemons.get(1).getName());
        assertEquals("Venusaur", retrievedPokemons.get(2).getName());

        // Vérification que getPokemons() a bien été appelée
        verify(pokedex, times(1)).getPokemons();
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Configuration pour une liste triée avec un Comparator
        List<Pokemon> pokemons = Arrays.asList(pokemon3, pokemon1, pokemon2);
        when(pokedex.getPokemons(any(Comparator.class)))
                .thenReturn(Collections.unmodifiableList(pokemons));

        // Utilisation d'un Comparator qui trie par nom pour cet exemple
        List<Pokemon> retrievedPokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));

        assertEquals(3, retrievedPokemons.size());
        assertEquals("Venusaur", retrievedPokemons.get(0).getName());
        assertEquals("Bulbasaur", retrievedPokemons.get(1).getName());
        assertEquals("Ivysaur", retrievedPokemons.get(2).getName());

        // Vérification que getPokemons(Comparator) a bien été appelée
        verify(pokedex, times(1)).getPokemons(any(Comparator.class));
    }
}
