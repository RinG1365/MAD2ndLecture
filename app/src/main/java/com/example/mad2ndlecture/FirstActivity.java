package com.example.mad2ndlecture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {

    TextView txtvmAge;
    EditText edtName, edtYear;
    Button btnClick;
    ImageView iv;

    Calendar calendar = Calendar.getInstance();
    int yoty = calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtvmAge = (TextView) findViewById(R.id.txtvmAge);
        edtName = (EditText) findViewById(R.id.edtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
        btnClick = (Button) findViewById(R.id.btnClick);

       /* btnClick.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View arg0)
            {
                String strName = edtName.getText().toString();
                String strYear = edtYear.getText().toString();

                if(strName.equalsIgnoreCase(""))
                {
                    edtName.setError("Fill in your name");
                }
                else if (strYear.equalsIgnoreCase(""))
                {
                    edtYear.setError("Fill in your birth year");
                }
                else
                {
                    int birthAge = yoty - Integer.valueOf(strYear);
                    txtvmAge.setText("Hello and Welcome " + strName + ". You are " + birthAge + " years old");
                }
            }
        });*/
    }

    public void fnGreet(View view)
    {
        String strName = edtName.getText().toString();
        String strYear = edtYear.getText().toString();

        if(strName.equalsIgnoreCase(""))
        {
            edtName.setError("Fill in your name");
        }
        else if (strYear.equalsIgnoreCase(""))
        {
            edtYear.setError("Fill in your birth year");
        }
        else
        {
            int birthAge = yoty - Integer.valueOf(strYear);
            txtvmAge.setText("Hello and Welcome " + strName + ". You are " + birthAge + " years old");
        }

    }

    public void fnThreadedActivity(View vw)
    {
        String strMsg= edtName.getText().toString();

        if(strMsg.equalsIgnoreCase(""))
        {
            edtName.setError("Fill in your name");
        }
        else
        {
            Intent intent = new Intent(this, ThreadedActivity.class);
            intent.putExtra("varStr1", strMsg);
            startActivityForResult(intent,2);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==2) {
            ImageView iv = (ImageView)findViewById(R.id.imgFrmActivity2);
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            txtvmAge.setText("Picture from activity 2 shown below:");
            iv.setImageBitmap(bp);
        }
    }

}
