package com.example.syncactivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import io.realm.Realm;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.provider.Telephony.Carriers.PASSWORD;

public class MainActivity extends AppCompatActivity {

    EditText text;
    EditText editText;
    Button button;
    UserObject userObject;
    String result="";
    public String baseurl="http://dummy.restapiexample.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText) findViewById(R.id.user);
        editText = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);
        userObject = new UserObject();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username = text.getText().toString();
                String pass = editText.getText().toString();
                ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nInfo = cm.getActiveNetworkInfo();
                boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                if (isConnected()) {
                    Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_SHORT).show();


                    String url = "https://cakeapi.trinitytuts.com/api/listuser";

                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url(url)
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .build();

//        Response response = client.newCall(request).execute();
//        Log.e(TAG, response.body().string());

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            String mMessage = e.getMessage().toString();
                            Log.w("failure Response", mMessage);
                            //call.cancel();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String mMessage = response.body().string();
                            Log.d("responseee", mMessage);
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }




    private boolean isConnected() {
        boolean connected = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();

        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

});
    }
    public class  task extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this,baseurl+result,Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url =new URL(baseurl);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String value=bf.readLine();
                System.out.println("result"+value);
                result=value;

                Log.d("anythingggg",""+value);


            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return result;
        }
    }


}



















        //String username=text.getText().toString();
        //String pass=editText.getText().toString();
        //Realm.init(MainActivity.this);
        //Realm realm=Realm.getDefaultInstance();
        //userObject.setUsername(username);
        //userObject.setPassword(pass);
        //realm.beginTransaction();
        //realm.copyToRealm(userObject);
        //realm.commitTransaction();


//               UserObject us=realm.where(UserObject.class).equalTo("password",pass).findFirst();
        //Toast.makeText(getApplicationContext(),username+pass+"dtdtr"+us.getUsername(),Toast.LENGTH_SHORT).show();


