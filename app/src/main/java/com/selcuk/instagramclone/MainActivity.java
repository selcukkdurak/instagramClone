package com.selcuk.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.selcuk.instagramclone.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        auth = FirebaseAuth.getInstance();

    }

    public void signInClicked(View view) {

    }

    public void signUpClicked(View view) {

        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_LONG).show();
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                //kullanıcı oluşturmak başarılı olduğu zaman yapılacaklar
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {

                //kullanıcı oluşturma sırasında hata olduğu zaman yapılacaklar
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}

