package com.example.pizzaordersystem;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    RadioButton PizzaType,hawaiian, hamAndCheese, Size, Crust;
    CheckBox Tomato, Onion, Pineapple, ExtraCheese, Mushroom;
    Button Process, NewOrder;
    TextView Summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PizzaType = findViewById(R.id.PizzaType);
        hawaiian = findViewById(R.id.Hawaiian);
        hamAndCheese = findViewById(R.id.HamAndCheese);
        Size = findViewById(R.id.Size);
        Crust = findViewById(R.id.Crust);

        Tomato = findViewById(R.id.TomatoOnion);
        Onion= findViewById(R.id.Onion);
        Pineapple = findViewById(R.id.Pineapple);
        ExtraCheese = findViewById(R.id.ExtraCheese);
        Mushroom = findViewById(R.id.ExtraCheeseMushroom);

        Process = findViewById(R.id.Process);
        NewOrder = findViewById(R.id.NewOrder);
        Summary = findViewById(R.id.Summary);

        Process.setOnClickListener(view -> processOrder());
        NewOrder.setOnClickListener(view -> resetForm());
    }

    private void processOrder() {
        String pizzaType = "";
        int basePrice = 0;

        int selectedPizza = PizzaType.getCheckedRadioButtonId();
        if (selectedPizza == R.id.Hawaiian) {
            pizzaType = "Hawaiian";
        } else if (selectedPizza == R.id.HamCheese) {
            pizzaType = "Ham & Cheese";
        } else {
            Toast.makeText(this, "Please select a pizza type.", Toast.LENGTH_SHORT).show();
            return;
        }

        String size = "";


        if (Size.getCheckedRadioButtonId() == R.id.Small) {
            size = "Small";
            if (pizzaType.equals("Hawaiian")) {
                basePrice = 100;
            } else if (pizzaType.equals("Ham & Cheese")) {
                basePrice = 200;
            }

        } else if (Size.getCheckedRadioButtonId() == R.id.Medium) {
            size = "Medium";
            if (pizzaType.equals("Hawaiian")) {
                basePrice = 150;
            } else if (pizzaType.equals("Ham & Cheese")) {
                basePrice = 300;
            }
        } else if (Size.getCheckedRadioButtonId() == R.id.Large) {
            size = "Large";
            if (pizzaType.equals("Hawaiian")) {
                basePrice = 200;
            } else if (pizzaType.equals("Ham & Cheese")) {
                basePrice = 400;
            }
        } else {
            Toast.makeText(this, "Please select a pizza size.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crust
        String crust = "";
        if (Crust.getCheckedRadioButtonId() == R.id.Thin) {
            crust = "Thin";
        } else if (Crust.getCheckedRadioButtonId() == R.id.Thick) {
            crust = "Thick";
            basePrice += basePrice * 0.5;
        } else {
            Toast.makeText(this, "Please select a crust type.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Toppings
        StringBuilder toppings = new StringBuilder();
        int toppingTotal = 0;

        if (Tomato.isChecked()) {
            toppings.append("Tomatoes & Onions, ");
            toppingTotal += 10;
        }
        if (Onion.isChecked()) {
            toppings.append("Tomatoes & Onions, ");
            toppingTotal += 10;
        }
        if (Pineapple.isChecked()) {
            toppings.append("Pineapple, ");
            toppingTotal += 15;
        }
        if (ExtraCheese.isChecked()) {
            toppings.append("Extra Cheese & Mushrooms, ");
            toppingTotal += 20;
        }
        if (Mushroom.isChecked()) {
            toppings.append("Mushrooms, ");
            toppingTotal += 10;
        }

        if (toppings.length() > 0)
            toppings.setLength(toppings.length() - 2); // Remove last comma
        else
            toppings.append("None");

        // Final Total
        int total = basePrice + toppingTotal;

        // Summary
        String summary = "Pizza: " + pizzaType + "\n"
                + "Size: " + size + "\n"
                + "Crust: " + crust + "\n"
                + "Toppings: " + toppings + "\n"
                + "Total: ₱" + total;

        Summary.setText(summary);
    }

    private void resetForm() {
        PizzaType.clearCheck();
        Size.clearCheck();
        Crust.clearCheck();
        Tomato.setChecked(false);
        Onion.setChecked(false);
        Pineapple.setChecked(false);
        Mushroom.setChecked(false);
        ExtraCheese.setChecked(false);
        hawaiian.clearCheck();
        hamAndCheese.clearCheck();
        Summary.setText("");
    }

}
