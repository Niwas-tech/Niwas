package com.example.syncactivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;

import io.realm.Realm;

public class Userview extends AppCompatActivity {

    EditText text;
    TextView view;
    TextView textView;
    Button button;
    UserObject userObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userview);
        text=(EditText)findViewById(R.id.user);
        view=(TextView)findViewById(R.id.text);
        textView=(TextView)findViewById(R.id.text2);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean a= false;
                ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo.State mobile = cm.getNetworkInfo(0).getState();


                NetworkInfo.State wifi = cm.getNetworkInfo(1).getState();


                if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
                {

                }
                else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
                {

                }

                try
                {
                    Class cmClass = Class.forName(cm.getClass().getName());
                    Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
                    method.setAccessible(true);
                    a =(Boolean)method.invoke(cm);
                    if(a)
                    {
                        Toast.makeText(getApplicationContext(),"Mobile data is on ",Toast.LENGTH_SHORT).show();
                    }else
                        {
                            Realm.init(Userview.this);
                            Realm realm=Realm.getDefaultInstance();
                            String ab=getIntent().getExtras().getString("username");
                            String bc=getIntent().getExtras().getString("password");
                            userObject.setUsername(ab);
                            userObject.setPassword(bc);
                            realm.beginTransaction();
                            realm.copyToRealm(userObject);
                            realm.commitTransaction();

                            Toast.makeText(getApplicationContext(),"Mobile data is off",Toast.LENGTH_SHORT).show();
                        }
                }

                catch (Exception e)
                {

                    Toast.makeText(getApplicationContext(),"M is off"+e.toString(),Toast.LENGTH_SHORT).show();

                }




//                Realm realm=Realm.getDefaultInstance();
//                UserObject us=realm.where(UserObject.class).equalTo("password",text.getText().toString()).findFirst();

//                view.setText(us.getUsername());
//                textView.setText(us.getPassword());



            }
        });

    }
}
