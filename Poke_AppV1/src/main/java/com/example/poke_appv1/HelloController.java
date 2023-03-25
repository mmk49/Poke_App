package com.example.poke_appv1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    public ArrayList<PokemonManager> pokeList;
    @FXML
    private ComboBox<String> pokeCombo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pokeCombo.getItems().addAll("base1.json", "base2.json", "base3.json");
        pokeCombo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> p) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(item);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            Image icon;
                            try {
                                //int iconNumber = this.getIndex() + 1;
                                //String iconPath = "MyProject/resources/images/icon_" + iconNumber + ".png";
                                icon = new Image("https://images.pokemontcg.io/base2/symbol.png");
                            } catch(NullPointerException ex) {
                                // in case the above image doesn't exist, use a default one
                                //String iconPath = "https://images.pokemontcg.io/base2/symbol.png";
                                icon = new Image("https://images.pokemontcg.io/base2/symbol.png");
                            }
                            ImageView iconImageView = new ImageView(icon);
                            iconImageView.setFitHeight(30);
                            iconImageView.setPreserveRatio(true);
                            setGraphic(iconImageView);
                        }
                    }
                };
            }
        });
    }
    private void test(String set, boolean checkCards) {
        pokeList = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        String test = "pokemon-tcg-data\\cards\\en\\";
        String filePath = test + set;
        try (FileReader reader = new FileReader(filePath)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            // Iterate over employee array
            userList.forEach(user -> parseUserObject((JSONObject) user, pokeList, checkCards));

//            idLabel.setText("ID: " + pokeList.get(0).getId());
//            nameLabel.setText("Name: " + pokeList.get(0).getName());
//            pokedexNumberLabel.setText("Pokedex Number: " + pokeList.get(0).getNationalPokedexNumbers());
//            numberLabel.setText("Number: " + pokeList.get(0).getNumber());
//            Image image = new Image(pokeList.get(0).getLargeImage());
//            pokemonImage.setImage(image);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseUserObject(JSONObject user, ArrayList<PokemonManager> pokeList, boolean checkCards) {

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
        //String small = (String) images.get("small");
        //System.out.println(small);
        String large = (String) images.get("large");
        System.out.println(large);
        String pokedexNumber = "NA";
        if(nationalPokedexNumbers != null) {
            pokedexNumber = nationalPokedexNumbers.toString();
            pokedexNumber = pokedexNumber.substring(1, pokedexNumber.length() - 1);
        }
        System.out.println(pokedexNumber);
        if(checkCards == true) {
            boolean hasCard = (boolean) user.get("hasCard");
            pokeList.add(new PokemonManager(id, name, pokedexNumber, number, large, hasCard));
        } else {
            pokeList.add(new PokemonManager(id, name, pokedexNumber, number, large, false));
        }
//        pokeList.add(new PokemonManager(id, name, pokedexNumber, number, large, false));
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
    private Button viewButton;

    @FXML
    private TextField searchText;
    @FXML
    public void OnActionSearch(ActionEvent event) {
        if(pokeList == null) {
            test("base1.json", false);
        }
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView pokemonImage00;
    @FXML
    private ImageView pokemonImage01;
    @FXML
    private ImageView pokemonImage02;
    @FXML
    private ImageView pokemonImage10;
    @FXML
    private ImageView pokemonImage11;
    @FXML
    private ImageView pokemonImage12;
    @FXML
    private ImageView pokemonImage20;
    @FXML
    private ImageView pokemonImage21;
    @FXML
    private ImageView pokemonImage22;
    @FXML
    private ImageView pokemonImage30;
    @FXML
    private ImageView pokemonImage31;
    public void OnActionView(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("set-view.fxml"));
//        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        Parent root = FXMLLoader.load(getClass().getResource("set-view.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("new window");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.NONE);
        primaryStage.initOwner(viewButton.getScene().getWindow());
        primaryStage.show();
    }

    private int imageCount = 0;
    public void OnActionNextPage(ActionEvent actionEvent) {
//        if(pokeList == null) {
//            test(pokeCombo.getValue());
//        } else {
//            imageCount += 11;
//        }
        imageCount += 11;
        updateImages();
    }

    public void updateImages() {
        pokemonImage00.setImage(new Image(pokeList.get(imageCount).getLargeImage()));
        if(pokeList.get(imageCount).getHasCard() == true) {
            pokemonImage00.setOpacity(1);
        } else {
            pokemonImage00.setOpacity(.5);
        }
        pokemonImage01.setImage(new Image(pokeList.get(imageCount + 1).getLargeImage()));
        if(pokeList.get(imageCount + 1).getHasCard() == true) {
            pokemonImage01.setOpacity(1);
        } else {
            pokemonImage01.setOpacity(.5);
        }
        pokemonImage02.setImage(new Image(pokeList.get(imageCount + 2).getLargeImage()));
        if(pokeList.get(imageCount + 2).getHasCard() == true) {
            pokemonImage02.setOpacity(1);
        } else {
            pokemonImage02.setOpacity(.5);
        }
        pokemonImage10.setImage(new Image(pokeList.get(imageCount + 3).getLargeImage()));
        if(pokeList.get(imageCount + 3).getHasCard() == true) {
            pokemonImage10.setOpacity(1);
        } else {
            pokemonImage10.setOpacity(.5);
        }
        pokemonImage11.setImage(new Image(pokeList.get(imageCount + 4).getLargeImage()));
        if(pokeList.get(imageCount + 4).getHasCard() == true) {
            pokemonImage11.setOpacity(1);
        } else {
            pokemonImage11.setOpacity(.5);
        }
        pokemonImage12.setImage(new Image(pokeList.get(imageCount + 5).getLargeImage()));
        if(pokeList.get(imageCount + 5).getHasCard() == true) {
            pokemonImage12.setOpacity(1);
        } else {
            pokemonImage12.setOpacity(.5);
        }
        pokemonImage20.setImage(new Image(pokeList.get(imageCount + 6).getLargeImage()));
        if(pokeList.get(imageCount + 6).getHasCard() == true) {
            pokemonImage20.setOpacity(1);
        } else {
            pokemonImage20.setOpacity(.5);
        }
        pokemonImage21.setImage(new Image(pokeList.get(imageCount + 7).getLargeImage()));
        if(pokeList.get(imageCount + 7).getHasCard() == true) {
            pokemonImage21.setOpacity(1);
        } else {
            pokemonImage21.setOpacity(.5);
        }
        pokemonImage22.setImage(new Image(pokeList.get(imageCount + 8).getLargeImage()));
        if(pokeList.get(imageCount + 8).getHasCard() == true) {
            pokemonImage22.setOpacity(1);
        } else {
            pokemonImage22.setOpacity(.5);
        }
        pokemonImage30.setImage(new Image(pokeList.get(imageCount + 9).getLargeImage()));
        if(pokeList.get(imageCount + 9).getHasCard() == true) {
            pokemonImage30.setOpacity(1);
        } else {
            pokemonImage30.setOpacity(.5);
        }
        pokemonImage31.setImage(new Image(pokeList.get(imageCount + 10).getLargeImage()));
        if(pokeList.get(imageCount + 10).getHasCard() == true) {
            pokemonImage31.setOpacity(1);
        } else {
            pokemonImage31.setOpacity(.5);
        }
    }

    public void OnMouseClickedPokemonImage00(MouseEvent mouseEvent) {
        pokeList.get(imageCount).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage01(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 1).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage02(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 2).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage10(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 3).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage11(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 4).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage12(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 5).setHasCard(true);
    }

    public void OnMouseClickedPokemonImage20(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 6).setHasCard(true);
    }
    public void OnMouseClickedPokemonImage21(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 7).setHasCard(true);
    }
    public void OnMouseClickedPokemonImage22(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 8).setHasCard(true);
    }
    public void OnMouseClickedPokemonImage30(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 9).setHasCard(true);
    }
    public void OnMouseClickedPokemonImage31(MouseEvent mouseEvent) {
        pokeList.get(imageCount + 10).setHasCard(true);
    }

    public void OnActionUpdate(ActionEvent actionEvent) {
        updateImages();
    }

    public void OnActionPokeComboSwitch(ActionEvent actionEvent) {
        if(pokeList != null) {
            pokeList.clear();
        }
        //test(pokeCombo.getValue(), true);
        test("test.json", true);
        updateImages();
        imageCount = 0;
    }

    public void OnActionPrevPage(ActionEvent actionEvent) {
        imageCount -= 11;
        updateImages();
    }
    public void WriteJSON() {
        JSONArray pokemonList = new JSONArray();
        for (PokemonManager pokemon: pokeList) {
            JSONObject pokemonDetails = new JSONObject();
            pokemonDetails.put("id", pokemon.getId());
            pokemonDetails.put("name", pokemon.getName());
            JSONArray nationalPokedexNumbers = new JSONArray();
            if(!pokemon.getNationalPokedexNumbers().equals("NA")) {
                nationalPokedexNumbers.add(Integer.parseInt(pokemon.getNationalPokedexNumbers()));
                pokemonDetails.put("nationalPokedexNumbers", nationalPokedexNumbers);
            }
            pokemonDetails.put("number", pokemon.getNumber());
            JSONObject images = new JSONObject();
            images.put("large", pokemon.getLargeImage());
            pokemonDetails.put("images", images);
            pokemonDetails.put("hasCard", pokemon.getHasCard());
            pokemonList.add(pokemonDetails);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter("test.json")) {
            file.write(pokemonList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnActionSave(ActionEvent actionEvent) {
        WriteJSON();
    }
}

