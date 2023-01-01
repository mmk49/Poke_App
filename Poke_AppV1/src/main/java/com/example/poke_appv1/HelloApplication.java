package com.example.poke_appv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Poke App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("pokemon-tcg-data\\cards\\en\\base1.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray userList = (JSONArray) obj;

            // Iterate over employee array
            userList.forEach(user -> parseUserObject((JSONObject) user));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        launch();
    }

    private static void parseUserObject(JSONObject user) {

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
    }
}