package com.example.poke_appv1;

public class PokemonManager {
    private final String id;
    private final String name;
    private final String nationalPokedexNumbers;
    private final String number;
    private final String largeImage;

    public PokemonManager(String id, String name, String nationalPokedexNumbers, String number, String largeImage) {
        this.id = id;
        this.name = name;
        this.nationalPokedexNumbers = nationalPokedexNumbers;
        this.number = number;
        this.largeImage = largeImage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationalPokedexNumbers() {
        return nationalPokedexNumbers;
    }

    public String getNumber() {
        return number;
    }

    public String getLargeImage() {
        return largeImage;
    }
}
