package com.cs442.shash5259.Assignment5;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    EditText et1,et2,et3,et4;
    Button b_insert,b_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);
        b_insert = (Button)findViewById(R.id.button);
        b_back = (Button)findViewById(R.id.button2);


        et1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et1.setText("");
                return false;
            }
        });

        et2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et2.setText("");
                return false;
            }
        });

        et3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et3.setText("");
                return false;
            }
        });

        et4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et4.setText("");
                return false;
            }
        });

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, PhoneBook.class);
                startActivity(intent);
            }
        });


        b_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String a1 = et1.getText().toString();
                String a2 = et2.getText().toString();
                String a3 = et3.getText().toString();
                String a4 = et4.getText().toString();

                if(a1.length()!= 0 && a2.length()!=0 && a3.length()!=0 && a4.length()!=0)
                {

         /*   Intent intent =
                    new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                            ContactsContract.Contacts.CONTENT_URI);
            intent.setData(Uri.parse("tel:" + a1));

            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, "Google");
            intent.putExtra(ContactsContract.Intents.Insert.POSTAL,"1600 Amphitheatre Parkway, Mountain View, California");
*/
                    ArrayList<ContentValues> data = new ArrayList<ContentValues>();




                    ContentValues row = new ContentValues();
                    row.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    row.put(ContactsContract.CommonDataKinds.Phone.NUMBER, a1);
                    data.add(row);

                    ContentValues row7 = new ContentValues();
                    row7.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    row7.put(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, a2);
                    data.add(row7);

                    ContentValues row1 = new ContentValues();
                    row1.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
                    row1.put(ContactsContract.CommonDataKinds.Organization.COMPANY, a4);
                    data.add(row1);

                    ContentValues row2 = new ContentValues();
                    row2.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                    row2.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM);
                    row2.put(ContactsContract.CommonDataKinds.Email.LABEL, "");
                    row2.put(ContactsContract.CommonDataKinds.Email.ADDRESS, a3);
                    data.add(row2);


                    Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, a2);
                    intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);

                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");

                    startActivity(intent);
                }
                else {
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    et4.setText("");

                    Toast.makeText(MainActivity2.this, "Enter all values!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}
