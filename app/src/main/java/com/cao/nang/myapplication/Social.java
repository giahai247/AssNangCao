package com.cao.nang.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Social extends AppCompatActivity {
    LoginButton loginButton;
    Button bntdangxuat , bntchon;
    ProfilePictureView profilePictureView;
    private ProfilePictureView friendProfilePicture;
    CallbackManager callbackManager;
    String name1  ;
    private TextView tvhienthi;
    private TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_social);
        anhxa();
        tvhienthi.setVisibility(View.INVISIBLE);
        name.setVisibility(View.INVISIBLE);
        bntchon.setVisibility(View.INVISIBLE);
        bntdangxuat.setVisibility(View.INVISIBLE);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        friendProfilePicture = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        setLogin();
        chuyen();
        logout();

    }

    private void chuyen() {
        bntchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Social.this , Chucnangfb.class);
                startActivity(intent);
            }
        });
    }


    private void logout() {
        bntdangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                bntchon.setVisibility(View.INVISIBLE);
                bntdangxuat.setVisibility(View.INVISIBLE);
                tvhienthi.setVisibility(View.INVISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                friendProfilePicture.setProfileId(null);
                name.setVisibility(View.INVISIBLE);
                name.setText("");

            }
        });

    }

    private void setLogin() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setVisibility(View.INVISIBLE);
                bntchon.setVisibility(View.VISIBLE);
                tvhienthi.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                bntdangxuat.setVisibility(View.VISIBLE);
                resul();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });

    }
    private void resul() {
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON", response.getJSONObject().toString());
                try{
                friendProfilePicture.setProfileId(Profile.getCurrentProfile().getId());
                name1 = object.getString("name");
                name.setText(name1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,first_name");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }

    private void anhxa() {;
        tvhienthi = (TextView) findViewById(R.id.tvhienthi);
        name = (TextView) findViewById(R.id.name);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        bntchon = (Button)findViewById(R.id.bntchon);
        bntdangxuat = (Button)findViewById(R.id.bntdangxuat);
    }
}
