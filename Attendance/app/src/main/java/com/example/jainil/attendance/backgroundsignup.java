package com.example.jainil.attendance;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class backgroundsignup extends AsyncTask<String,Void,String> {

    public Context ctx;
    AlertDialog alertDialog;
    backgroundsignup(Context ctx)
    {
        this.ctx = ctx;

    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        String name = params[0];
        String email = params[1];
        String address = params[2];
        String mobile = params[3];
        String password = params[4];
        String send_url="http://192.168.43.30/webapp/signup.php";
        //Toast.makeText(ctx,Pass,Toast.LENGTH_LONG).show();

        try {
            URL url = new URL(send_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String data = URLEncoder.encode("name", "UTF-8") +"="+ URLEncoder.encode(name, "UTF-8")+ "&" + URLEncoder.encode("email", "UTF-8") +"="+ URLEncoder.encode(email, "UTF-8")+ "&" +URLEncoder.encode("address", "UTF-8") +"="+ URLEncoder.encode(address, "UTF-8")+ "&" + URLEncoder.encode("mobile", "UTF-8") +"="+ URLEncoder.encode(mobile, "UTF-8")+ "&" + URLEncoder.encode("password", "UTF-8") +"="+ URLEncoder.encode(password, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();
            //httpURLConnection.connect();
            httpURLConnection.disconnect();

            return "Registration Success...";
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }
}
