package ie.ul.planmytime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class UserSignUp extends AppCompatActivity {

    private Button SignUpBtn;
    private Button redirectSignInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        //  On Click - open main page of app
        SignUpBtn = (Button) findViewById(R.id.SignInBtn);
        SignUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHomepage();
            }
        });

        // On Click - Redirected to Sign Up Page
        redirectSignInBtn = (Button) findViewById(R.id.redirectSignInBtn);
        redirectSignInBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUpPage();
            }
        });
    }
    public void openHomepage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openSignUpPage(){
        Intent intent = new Intent(this, UserSignUp.class);
        startActivity(intent);
    }
}