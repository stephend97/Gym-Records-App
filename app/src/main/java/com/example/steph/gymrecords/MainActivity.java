package com.example.steph.gymrecords;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    String id;
    GoogleSignInAccount account;
    GoogleApiClient googleApiClient;
    static final int REQ_CODE = 9001;
    LinearLayout vLayout;
    SignInButton gSignInButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.welcomeTV);
        gSignInButton = findViewById(R.id.sign_in_button);
        vLayout = findViewById(R.id.verticalLayout);
        gSignInButton.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
        vLayout.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        if(view.getId() == R.id.sign_in_button){
            signIn();
        }
        else if(view.getId() == R.id.sign_out_button) {
            signOut();
        }
        else if(view.getId() == R.id.BenterData){
            Intent i = new Intent(MainActivity.this, EnterDate.class);
            i.putExtra("ID", account.getId());
            startActivity(i);
        }
        else if(view.getId() == R.id.BviewHistory){
            Intent i = new Intent(MainActivity.this, ViewHistory.class);
            i.putExtra("ID", account.getId());
            startActivity(i);
        }
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
        Toast.makeText(this, "Sign out successful.", Toast.LENGTH_LONG).show();
    }

    private void updateUI(boolean signedIn){
        if(signedIn){
            vLayout.setVisibility(View.VISIBLE);
            gSignInButton.setVisibility(View.GONE);
        }
        else{
            vLayout.setVisibility(View.GONE);
            gSignInButton.setVisibility(View.VISIBLE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    private void handleResult(GoogleSignInResult result){
        if(result.isSuccess()) {
            account = result.getSignInAccount();
            updateUI(true);
            textView.setText("Signed in as: " + account.getEmail());
        }
        else{
            Toast.makeText(this, "Sign in failed.", Toast.LENGTH_LONG).show();
        }
    }
}