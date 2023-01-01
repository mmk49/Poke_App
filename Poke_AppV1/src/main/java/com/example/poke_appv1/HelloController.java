package com.example.poke_appv1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public ArrayList<PokemonManager> pokeList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pokeList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("pokemon-tcg-data\\cards\\en\\base1.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            // Iterate over employee array
            userList.forEach(user -> parseUserObject((JSONObject) user, pokeList));

            idLabel.setText("ID: " + pokeList.get(0).getId());
            nameLabel.setText("Name: " + pokeList.get(0).getName());
            pokedexNumberLabel.setText("Pokedex Number: " + pokeList.get(0).getNationalPokedexNumbers());
            numberLabel.setText("Number: " + pokeList.get(0).getNumber());
            Image image = new Image(pokeList.get(0).getLargeImage());
            pokemonImage.setImage(image);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUserObject(JSONObject user, ArrayList<PokemonManager> pokeList) {

        String id = (String) user.get("id");
        System.out.println(id);

        String name = (String) user.get("name");
        System.out.println(name);

        JSONArray nationalPokedexNumbers = (JSONArray) user.get("nationalPokedexNumbers");
        if(nationalPokedexNumbers != null) {
            for (int i = 0; i < nationalPokedexNumbers.size(); i++) {
                System.out.println(nationalPokedexNumbers.get(i));
            }
        }
        String number = (String) user.get("number");
        System.out.println(number);

        JSONObject images = (JSONObject) user.get("images");
        String small = (String) images.get("small");
        System.out.println(small);
        String large = (String) images.get("large");
        System.out.println(large);
        String pokedexNumber = "NA";
        if(nationalPokedexNumbers != null) {
            pokedexNumber = nationalPokedexNumbers.toString();
        }
        pokeList.add(new PokemonManager(id, name, pokedexNumber, number, large));
    }

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label numberLabel;

    @FXML
    private Label pokedexNumberLabel;

    @FXML
    private ImageView pokemonImage;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchText;

    @FXML
    public void OnActionSearch(ActionEvent event) {
        String pokemonName = searchText.getText();
        PokemonManager currentPokemon = pokeList.get(0);
        int i = 0;
        while(!pokemonName.equals(currentPokemon.getName())){
            currentPokemon = pokeList.get(i);
            i += 1;
        }

        idLabel.setText("ID: " + currentPokemon.getId());
        nameLabel.setText("Name: " + currentPokemon.getName());
        pokedexNumberLabel.setText("Pokedex Number: " + currentPokemon.getNationalPokedexNumbers());
        numberLabel.setText("Number: " + currentPokemon.getNumber());
        Image image = new Image(currentPokemon.getLargeImage());
        pokemonImage.setImage(image);
    }
}

