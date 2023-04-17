package ie.ul.planmytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {
    private Button signIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //  On Click - Sign In Page
        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignInPage();
            }
        });

        // On Click - Sign Up Page
        signUp = (Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignUpPage();
            }
        });
    }
    public void openSignInPage(){
        Intent intent = new Intent(this, UserSignIn.class);
        startActivity(intent);
    }

    public void openSignUpPage(){
        Intent intent = new Intent(this, UserSignUp.class);
        startActivity(intent);
    }
}


