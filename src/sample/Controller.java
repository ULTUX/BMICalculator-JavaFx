package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

    @FXML
    private RadioButton m;
    @FXML
    private RadioButton k;
    @FXML
    private TextField waga;
    @FXML
    private TextField wzrost;

    Alert error = new Alert(Alert.AlertType.ERROR);


    private double calculateBMI(double waga, double wzrost){
        double bmi;
        while (true) {
            try {
                wzrost /= 100;
                bmi = waga / Math.pow(wzrost, 2);
                return bmi;
            } catch (ArithmeticException e) {
                error.setContentText("Upewnij się, że żadna z wartości nie jest równa zero.");
                error.show();
            }
        }
    }
    public void initialize() {
        System.out.println("Initializing...");
        final ToggleGroup toggleGroup = new ToggleGroup();
        m.setToggleGroup(toggleGroup);
        k.setToggleGroup(toggleGroup);
        m.fire();
        error.setTitle("Wystąpił błąd");
        error.setHeaderText("Jedna z wprowadzonych wartości jest błędna.");

    }
    @FXML
    private void onClick(ActionEvent actionEvent){
        try {
            double dWaga = Double.parseDouble(waga.getText());
            double dWzrost = Double.parseDouble(wzrost.getText());
            double bmi = calculateBMI(dWaga, dWzrost);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Wynik");
            info.setHeaderText("Twoje BMI zostało obliczone.");
            String typ;
            if (bmi < 16) typ = "wygłodzenie";
            else if (bmi < 16.99) typ = "wychudzenie";
            else if (bmi < 18.49) typ = "niedowagę";
            else if (bmi < 24.99) typ = "wagę normie";
            else if (bmi < 29.99) typ = "nadwagę";
            else if (bmi < 34.99) typ = "I stopień otyłości";
            else if (bmi < 39.99) typ = "II stopień otyłości";
            else if (bmi > 40) typ = "III stopień otyłości (otyłość skrajna)";
            else typ = null;
            info.setContentText("Twoje BMI wynosi: "+String.format("%.2f", bmi)+" masz "+typ);
            info.show();
        }
        catch (Exception e){
            error.setContentText("Upewnij się, że wiek jest liczbą całkowitą, a waga i wzrost zmiennoprzecinkową.");
            error.show();
        }

    }
}
