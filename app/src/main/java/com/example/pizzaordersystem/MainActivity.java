package com.example.pizzaordersystem;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioButton hawaiian, hamAndCheese;
    RadioButton small, medium, large;
    RadioButton thin, thick;
    RadioButton pwdSenior;

    CheckBox onion, tomato, mushroom, extraCheese, pineapple;

    Button processOrder, newOrder;
    TextView summary;

    RadioGroup sizeGroup, crustGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pizza type
        hawaiian = findViewById(R.id.Hawaiian);
        hamAndCheese = findViewById(R.id.HamAndCheese);

        // Size group
        small = findViewById(R.id.Small);
        medium = findViewById(R.id.Medium);
        large = findViewById(R.id.Large);

        // Crust group
        thin = findViewById(R.id.Thin);
        thick = findViewById(R.id.Thick);

        // Toppings
        onion = findViewById(R.id.Onion);
        tomato = findViewById(R.id.Tomato);
        mushroom = findViewById(R.id.Mushroom);
        extraCheese = findViewById(R.id.ExtraCheese);
        pineapple = findViewById(R.id.Pineapple);

        // Discount
        pwdSenior = findViewById(R.id.radioButtonPwdSenior);

        // Buttons and summary
        processOrder = findViewById(R.id.Process);
        newOrder = findViewById(R.id.NewOrder);
        summary = findViewById(R.id.Summary);


        // Button listeners
        processOrder.setOnClickListener(v -> processOrder());
        newOrder.setOnClickListener(v -> resetForm());

        // Manual exclusivity for radio buttons not in group
        hawaiian.setOnClickListener(v -> hamAndCheese.setChecked(false));
        hamAndCheese.setOnClickListener(v -> hawaiian.setChecked(false));

        small.setOnClickListener(v -> { medium.setChecked(false); large.setChecked(false); });
        medium.setOnClickListener(v -> { small.setChecked(false); large.setChecked(false); });
        large.setOnClickListener(v -> { small.setChecked(false); medium.setChecked(false); });

        thin.setOnClickListener(v -> thick.setChecked(false));
        thick.setOnClickListener(v -> thin.setChecked(false));
    }

    private void processOrder() {
        StringBuilder orderSummary = new StringBuilder();
        double basePrice = 0;
        double subtotal = 0;

        // Pizza type and size
        String pizzaType = "";
        if (hawaiian.isChecked()) {
            pizzaType = "Hawaiian";
        } else if (hamAndCheese.isChecked()) {
            pizzaType = "Ham and Cheese";
        } else {
            showToast("Please select a pizza type.");
            return;
        }

        String size = "";
        if (small.isChecked()) {
            size = "Small";
            basePrice = hawaiian.isChecked() ? 100 : 200;
        } else if (medium.isChecked()) {
            size = "Medium";
            basePrice = hawaiian.isChecked() ? 150 : 300;
        } else if (large.isChecked()) {
            size = "Large";
            basePrice = hawaiian.isChecked() ? 200 : 400;
        } else {
            showToast("Please select a size.");
            return;
        }

        subtotal = basePrice;
        orderSummary.append("Pizza: ").append(pizzaType).append("\n");
        orderSummary.append("Size: ").append(size).append("\n");

        // Crust
        if (thin.isChecked()) {
            orderSummary.append("Crust: Thin\n");
        } else if (thick.isChecked()) {
            double thickCrustCost = basePrice * 0.5;
            subtotal += thickCrustCost;
            orderSummary.append("Crust: Thick (+₱").append(String.format("%.2f", thickCrustCost)).append(")\n");
        } else {
            showToast("Please select a crust.");
            return;
        }

        // Toppings
        orderSummary.append("Toppings:\n");
        if (onion.isChecked()) {
            orderSummary.append("- Onion (₱10)\n");
            subtotal += 10;
        }
        if (tomato.isChecked()) {
            orderSummary.append("- Tomato (₱10)\n");
            subtotal += 10;
        }
        if (pineapple.isChecked()) {
            orderSummary.append("- Pineapple (₱15)\n");
            subtotal += 15;
        }
        if (extraCheese.isChecked()) {
            orderSummary.append("- Extra Cheese (₱20)\n");
            subtotal += 20;
        }
        if (mushroom.isChecked()) {
            orderSummary.append("- Mushroom (₱20)\n");
            subtotal += 20;
        }

        // Discount
        if (pwdSenior.isChecked()) {
            orderSummary.append("Discount: PWD/Senior (20%)\n");
            subtotal *= 0.8;
        }

        // VAT (12%)
        double vat = subtotal * 0.12;
        double totalWithVAT = subtotal + vat;

        orderSummary.append(String.format("VAT (12%%): ₱%.2f\n", vat));
        orderSummary.append(String.format("Total with VAT: ₱%.2f", totalWithVAT));

        summary.setText(orderSummary.toString());
    }

    private void resetForm() {
        // Clear radio groups

        // Clear manually controlled radio buttons
        hawaiian.setChecked(false);
        hamAndCheese.setChecked(false);
        thin.setChecked(false);
        thick.setChecked(false);
        pwdSenior.setChecked(false);
        small.setChecked(false);
        medium.setChecked(false);
        large.setChecked(false);

        // Clear toppings checkboxes
        onion.setChecked(false);
        tomato.setChecked(false);
        mushroom.setChecked(false);
        extraCheese.setChecked(false);
        pineapple.setChecked(false);

        // Clear summary
        summary.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
