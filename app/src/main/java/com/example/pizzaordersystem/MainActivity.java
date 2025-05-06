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

        hawaiian.setOnClickListener(v -> {
            if (hawaiian.isChecked()) {
                hamAndCheese.setChecked(false);
            }
        });
        hamAndCheese.setOnClickListener(v -> {
            if (hamAndCheese.isChecked()) {
                hawaiian.setChecked(false);
            }
        });

        small.setOnClickListener(v -> {
            if (small.isChecked()) {
                medium.setChecked(false);
                large.setChecked(false);
            }
        });
        medium.setOnClickListener(v -> {
            if (medium.isChecked()) {
                small.setChecked(false);
                large.setChecked(false);
            }
        });
        large.setOnClickListener(v -> {
            if (large.isChecked()) {
                small.setChecked(false);
                medium.setChecked(false);
            }
        });

        thin.setOnClickListener(v -> {
            if (thin.isChecked()) {
                thick.setChecked(false);
            }
            });
        thick.setOnClickListener(v -> {
            if (thick.isChecked()) {
                thin.setChecked(false);
            }
        });

    }

    private void processOrder() {
        StringBuilder orderSummary = new StringBuilder();
        double subtotal = 0;

        // Pizza type
        if (hawaiian.isChecked()) {
            orderSummary.append("Pizza: Hawaiian\n");
            subtotal += 150;
        } else if (hamAndCheese.isChecked()) {
            orderSummary.append("Pizza: Ham and Cheese\n");
            subtotal += 140;
        } else {
            showToast("Please select a pizza type.");
            return;
        }

        // Size
        if (small.isChecked()) {
            orderSummary.append("Size: Small\n");
        } else if (medium.isChecked()) {
            orderSummary.append("Size: Medium (+₱20)\n");
            subtotal += 20;
        } else if (large.isChecked()) {
            orderSummary.append("Size: Large (+₱40)\n");
            subtotal += 40;
        } else {
            showToast("Please select a size.");
            return;
        }

        // Crust
        if (thin.isChecked()) {
            orderSummary.append("Crust: Thin\n");
        } else if (thick.isChecked()) {
            orderSummary.append("Crust: Thick (+₱10)\n");
            subtotal += 10;
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
        if (mushroom.isChecked()) {
            orderSummary.append("- Mushroom (₱20)\n");
            subtotal += 20;
        }
        if (extraCheese.isChecked()) {
            orderSummary.append("- Extra Cheese (₱20)\n");
            subtotal += 20;
        }
        if (pineapple.isChecked()) {
            orderSummary.append("- Pineapple (₱15)\n");
            subtotal += 15;
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
        sizeGroup.clearCheck();
        crustGroup.clearCheck();

        hawaiian.setChecked(false);
        hamAndCheese.setChecked(false);
        pwdSenior.setChecked(false);

        onion.setChecked(false);
        tomato.setChecked(false);
        mushroom.setChecked(false);
        extraCheese.setChecked(false);
        pineapple.setChecked(false);

        summary.setText("");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
