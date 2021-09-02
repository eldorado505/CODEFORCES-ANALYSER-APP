package com.rajdeeptanwar5.codeforcesanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rajdeeptanwar5.codeforcesanalyser.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSubmitClick(View view) {
        EditText usernameField=(EditText) findViewById(R.id.username_field);
        String username=usernameField.getText().toString();
        Intent intent =new Intent(this,ProfileActivity.class);
        intent.putExtra("key",username);
        startActivity(intent);
    }
}
