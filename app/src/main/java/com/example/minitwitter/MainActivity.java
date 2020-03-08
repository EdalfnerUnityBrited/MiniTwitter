package com.example.minitwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView tvGoSignUp;
    EditText etEmail, etPassword;
    Intent intent;


    private Conexion conne= new Conexion();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        btnLogin=findViewById(R.id.buttonLogin);
        tvGoSignUp=findViewById(R.id.textViewGoSignUp);
        etEmail=findViewById(R.id.editTextEmail);
        etPassword=findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(this);
        tvGoSignUp.setOnClickListener(this);

        conne.connectionBD();
        conexionExitosa();
    }

    private void conexionExitosa() {
        if (conne.conexion!=null){
            Toast.makeText(getApplicationContext(),"La conexion se ha establecido",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Conexion erronea",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.buttonLogin:
                login();
                break;
            case R.id.textViewGoSignUp:
                goToSignUp();
                break;
        }
    }

    private void login() {
        String email = etEmail.getText().toString();
        String password= etPassword.getText().toString();
        boolean usuarioEncontrado=conne.validarUsuarios(email, password);
        if (usuarioEncontrado==true){
            Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"Vete",Toast.LENGTH_LONG).show();
        }
    }

    private void goToSignUp() {
        Intent i= new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
        finish();
    }
}
