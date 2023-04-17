package ie.ul.planmytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class UserSignIn extends AppCompatActivity {

    private Button SignInBtn;
    private Button redirectSignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_in);

        //  On Click - open main page of app
        SignInBtn = (Button) findViewById(R.id.SignInBtn);
        SignInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHomepage();
            }
        });

        // On Click - Redirected to Sign Up Page
        redirectSignUpBtn = (Button) findViewById(R.id.redirectSignUpBtn);
        redirectSignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUpPage();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(UserSignIn.this, Homepage.class);
        startActivity(intent);
    }

    public void openSignUpPage(){
        Intent intent = new Intent(UserSignIn.this, UserSignUp.class);
        startActivity(intent);
    }
}