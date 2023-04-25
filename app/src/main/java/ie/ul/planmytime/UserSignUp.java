package ie.ul.planmytime;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserSignUp extends AppCompatActivity {

    private Button SignUpBtn;
    private Button redirectSignInBtn;

    TextInputEditText inputName;

    TextInputEditText inputEmail;
    TextInputEditText inputPassword;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);


        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        redirectSignInBtn = findViewById(R.id.redirectSignInBtn);
        SignUpBtn = findViewById(R.id.SignInBtn);

       mAuth = FirebaseAuth.getInstance();

        SignUpBtn.setOnClickListener(view ->{
            createUser();
        });
//
        redirectSignInBtn.setOnClickListener(view ->{
            startActivity(new Intent(UserSignUp.this, UserSignIn.class));
        });
    }


    private void createUser() {
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Please provide an email!");
            inputEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Please create a password!");
            inputPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(UserSignUp.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserSignUp.this, UserSignIn.class));
                    } else {
                        Toast.makeText(UserSignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

        //  On Click - open main page of app
//        SignUpBtn = (Button) findViewById(R.id.SignInBtn);
//        SignUpBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openHomepage();
//            }
//        });

        // On Click - Redirected to Sign Up Page
//        redirectSignInBtn = (Button) findViewById(R.id.redirectSignInBtn);
//        redirectSignInBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openSignUpPage();
//            }
//        });
//    }
//    public void openHomepage(){
//        Intent intent = new Intent(this, Homepage.class);
//        startActivity(intent);
//    }
//
//    public void openSignUpPage(){
//        Intent intent = new Intent(this, UserSignUp.class);
//        startActivity(intent);
//    }

