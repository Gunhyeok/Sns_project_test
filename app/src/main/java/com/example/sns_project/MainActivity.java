package com.example.sns_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user ==null) //login안되었을때
        {
         MystartActivity(SignUPActivity.class);
        }else  //회원가입 or login check
        {
                for (UserInfo profile : user.getProviderData()) {
                    // Name, email address, and profile photo Url
                    String name = profile.getDisplayName();
                    Log.e("이름","이름 "+name);
                    if(name != null)
                    {
                        if(name.length() == 0)
                        {
                            MystartActivity(MemberActivity.class);
                        }
                    }


                }


        }

        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    MystartActivity(SignUPActivity.class);


                    break;
            }
        }
    };


    private void MystartActivity(Class c)
    {
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
