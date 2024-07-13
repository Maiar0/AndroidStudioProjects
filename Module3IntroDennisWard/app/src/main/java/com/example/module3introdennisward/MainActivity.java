package com.example.module3introdennisward;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextWatcher;
import android.text.Editable;

public class MainActivity extends AppCompatActivity {

    private EditText nameText;
    private TextView textGreeting;
    private Button buttonSayHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        textGreeting = findViewById(R.id.textGreeting);
        buttonSayHello = findViewById(R.id.buttonSayHello);

        buttonSayHello.setEnabled(false);

        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonSayHello.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void SayHello(View view) {
        String name = nameText.getText().toString();
        if (name != null && !name.isEmpty()) {
            textGreeting.setText("Hello " + name);
        }
    }
}
