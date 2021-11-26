package com.example.proyectomovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private CheckBox check_politics;
    private Button button_registration;
    //private TextView text_politics;
    private TextInputLayout txtregistername, txtregisterlastname, txtregisteremail, txtregisterpassword;
    private final int ACTIVITY_TERMINOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        check_politics = findViewById(R.id.chk_politics);
        button_registration = findViewById(R.id.btn_registration);
        //text_politics = findViewById(R.id.txt_politics);
        //text_politics.setMovementMethod(LinkMovementMethod.getInstance());
        //text_politics.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        txtregistername = findViewById(R.id.txt_register_name);
        txtregisterlastname = findViewById(R.id.txt_register_last_name);
        txtregisteremail = findViewById(R.id.txt_register_email);
        txtregisterpassword = findViewById(R.id.txt_register_password);


        check_politics.setOnClickListener(this);
        //text_politics.setOnClickListener(this);
        button_registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chk_politics:
                politics();

                break;
            /*case R.id.txt_politics:
                break;*/
            case R.id.btn_registration:
                Boolean name = validatename();
                Boolean last = validatelastname();
                Boolean email = validateemail();
                Boolean pass = validatepassword();
                if ((name) && (last) && (email) && (pass)) {
                    Toast.makeText(this, "Successful Registration", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }


    public void politics() {
        Intent intent = new Intent(this, PoliticsActivity.class);
        startActivityForResult(intent, ACTIVITY_TERMINOS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_TERMINOS) {
            if (resultCode == Activity.RESULT_OK) {
                String estado = data.getStringExtra("ESTADO");
                Toast.makeText(this, "You Accepted terms", Toast.LENGTH_SHORT).show();
                check_politics.setChecked(true);
                button_registration.setEnabled(true);
            } else {
                Toast.makeText(this, "You don´t agree terms", Toast.LENGTH_SHORT).show();
                check_politics.setChecked(false);
                button_registration.setEnabled(false);
            }
        }
    }


    private boolean validatename() {
        String username = txtregistername.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            txtregistername.setError("Field can't be empty");
            return false;
        } else {
            txtregistername.setError(null);
            return true;
        }
    }

    private boolean validatelastname() {
        String userlastname = txtregisterlastname.getEditText().getText().toString().trim();
        if (userlastname.isEmpty()) {
            txtregisterlastname.setError("Field can't be empty");
            return false;
        } else {
            txtregisterlastname.setError(null);
            return true;
        }
    }

    private boolean validateemail() {
        String useremail = txtregisteremail.getEditText().getText().toString().trim();
        if (useremail.isEmpty()) {
            txtregisteremail.setError("Field can't be empty");
            return false;
        } else {
            txtregisteremail.setError(null);
            return true;
        }
    }

    private boolean validatepassword() {
        String userpassword = txtregisterpassword.getEditText().getText().toString().trim();
        if (userpassword.isEmpty()) {
            txtregisterpassword.setError("Field can't be empty");
            return false;
        } else {
            if (userpassword.length() < 8 && !isValidPassword(userpassword)) {
                Toast.makeText(this, "Contraseña no válida", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                Toast.makeText(this, "Contraseña válida", Toast.LENGTH_SHORT).show();
                txtregisterpassword.setError(null);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("correo",txtregisteremail.getEditText().getText().toString());
                resultIntent.putExtra("contrasena", txtregisterpassword.getEditText().getText().toString());
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
            return true;
        }
    }

    private static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}

