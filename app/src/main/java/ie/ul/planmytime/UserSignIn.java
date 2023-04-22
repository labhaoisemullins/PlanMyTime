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

public class UserSignIn extends AppCompatActivity {

    private Button SignInBtn;
    private Button redirectSignUpBtn;
    TextInputEditText inputEmail;
    TextInputEditText inputPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        redirectSignUpBtn = findViewById(R.id.redirectSignUpBtn);
        SignInBtn = findViewById(R.id.SignInBtn);

        mAuth = FirebaseAuth.getInstance();

        SignInBtn.setOnClickListener(view -> {
            loginUser();
        });
        redirectSignUpBtn.setOnClickListener(view ->{
            startActivity(new Intent(UserSignIn.this, UserSignUp.class));
        });
    }

    private void loginUser(){
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            inputEmail.setError("Please type in your email!");
            inputEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            inputPassword.setError("Please type in your password!");
            inputPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(UserSignIn.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UserSignIn.this, Homepage.class));
                    }else{
                        Toast.makeText(UserSignIn.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_sign_in);
//
//        //  On Click - open main page of app
//        SignInBtn = (Button) findViewById(R.id.SignInBtn);
//        SignInBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openHomepage();
//            }
//        });
//
//        // On Click - Redirected to Sign Up Page
//        redirectSignUpBtn = (Button) findViewById(R.id.redirectSignUpBtn);
//        redirectSignUpBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openSignUpPage();
//            }
//        });
//    }
//    public void openHomepage(){
//        Intent intent = new Intent(UserSignIn.this, Homepage.class);
//        startActivity(intent);
//    }
//
//    public void openSignUpPage(){
//        Intent intent = new Intent(UserSignIn.this, UserSignUp.class);
//        startActivity(intent);
//    }
//}
