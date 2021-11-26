package com.example.proyectomovil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sign_in, btn_sign_up;

    private TextInputLayout txtinputusername, txtinputpassword;
    private final int ACTIVITY_REGISTRO = 1;
    private SharedPreferences misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_in = findViewById(R.id.btn_sign_in);
        btn_sign_up = findViewById(R.id.btn_sign_up);
        txtinputusername = findViewById(R.id.txt_input_username);
        txtinputpassword = findViewById(R.id.txt_input_password);

        btn_sign_in.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);

        misPreferencias = getSharedPreferences("MI_PREFERENCIA", MODE_PRIVATE);
        String usuario = misPreferencias.getString("usuario", "");
        String contrasena = misPreferencias.getString("contrasena", "");

        if (!usuario.equals("") && !contrasena.equals("")){
            toLogin(usuario, contrasena);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                Toast.makeText(this, "SIGN IN", Toast.LENGTH_SHORT).show();
                String usuario = txtinputusername.getEditText().getText().toString();
                String contrasena = txtinputpassword.getEditText().getText().toString();

                Log.e("Usuario", usuario);
                Log.e("Contraseña", contrasena);

                if (usuario.isEmpty()) {
                    txtinputusername.setError("Field can't be empty");
                } else {
                    txtinputusername.setError(null);
                }

                if (contrasena.isEmpty()) {
                    txtinputpassword.setError("Field can't be empty");
                } else {
                    txtinputpassword.setError(null);
                }

                if (usuario.equals("admin@admin.com") && contrasena.equals("admin")) {
                    toLogin(usuario, contrasena);

                } else {
                    Toast.makeText(this, "ERROR INICIANDO SESION", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_sign_up:
                Toast.makeText(this, "SIGN UP", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, RegistrationActivity.class);
                startActivityForResult(intent, ACTIVITY_REGISTRO);
                break;
        }
    }


    public void toLogin (String usuario, String contrasena){
        Toast.makeText(this, "INICIADA SESION", Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = misPreferencias.edit();
        editor.putString("usuario", usuario);
        editor.putString("contrasena", contrasena);
        editor.commit();

        Intent intent = new Intent(this, DrawerActivity.class);
        intent.putExtra("usuario", "usuario");
        intent.putExtra("contrasena", "contrasena");
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REGISTRO) {
            if (resultCode == Activity.RESULT_OK) {

                String usuario = data.getStringExtra("correo");
                String contrasena = data.getStringExtra("contrasena");

                toLogin(usuario, contrasena);

            }
        }
    }
}