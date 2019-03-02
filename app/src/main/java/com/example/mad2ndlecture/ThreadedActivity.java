package com.example.mad2ndlecture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView iv;
    TextView txtVmHello;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded_main);

        iv = (ImageView) findViewById(R.id.imgVmProfile);
        Intent intent = getIntent();
        String strMsg = intent.getStringExtra("varStr1");
        txtVmHello = (TextView)findViewById(R.id.txtVmHello);
        txtVmHello.setText("Welcome to new activity wahai "+ strMsg);

        btnSend = (Button)findViewById(R.id.btnSendBack);
        btnSend.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View arg0)
            {
                Bitmap bp = ((BitmapDrawable)iv.getDrawable()).getBitmap();
                Intent intent = new Intent();
                intent.putExtra("data",bp);
                setResult(2,intent);
                finish();
            }
        });
    }


    public void fnTakePic(View view)
    {
        Runnable run = new Runnable()
        {
            public void run()
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable(){

                    public void run()
                    {
                        txtVmHello.setText(txtVmHello.getText().toString()+ " ... This is your Picture heheh..");
                    }
                });
            }
        };

        Thread thread1 =new Thread(run);
        thread1.start();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }
}
