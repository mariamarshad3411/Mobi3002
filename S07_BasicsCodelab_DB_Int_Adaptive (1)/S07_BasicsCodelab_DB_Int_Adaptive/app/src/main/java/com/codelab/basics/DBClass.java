package com.codelab.basics;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

public class DBClass {

    private List<DataModel> pokemonList;
    private SharedPreferences prefs;

    public DBClass(Context context) {
        prefs = context.getSharedPreferences("PokemonPrefs", Context.MODE_PRIVATE);

        pokemonList = new ArrayList<>();
        pokemonList.add(new DataModel(1, "Pikachu", 80, "Electric mouse", prefs.getLong("Pikachu", 0L)));
        pokemonList.add(new DataModel(2, "Charmander", 70, "Fire lizard", prefs.getLong("Charmander", 0L)));
        pokemonList.add(new DataModel(3, "Bulbasaur", 65, "Grass seed", prefs.getLong("Bulbasaur", 0L)));
        pokemonList.add(new DataModel(4, "Squirtle", 60, "Water turtle", prefs.getLong("Squirtle", 0L)));
        pokemonList.add(new DataModel(5, "Jigglypuff", 50, "Balloon singer", prefs.getLong("Jigglypuff", 0L)));
        pokemonList.add(new DataModel(6, "Eevee", 75, "Evolution fox", prefs.getLong("Eevee", 0L)));
    }

    public List<DataModel> findAll() {
        return pokemonList;
    }


    public void incAccessCount(int number) {
        for (DataModel p : pokemonList) {
            if (p.number == number) {
                p.accessCount++;


                prefs.edit().putLong(p.name, p.accessCount).apply();
                break;
            }
        }
    }

    public DataModel getMostAccessed() {
        DataModel favorite = pokemonList.get(0);
        for (DataModel p : pokemonList) {
            if (p.accessCount > favorite.accessCount) {
                favorite = p;
            }
        }
        return favorite;
    }
}
