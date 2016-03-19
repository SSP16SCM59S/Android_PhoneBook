package com.cs442.shash5259.Assignment5;

import android.app.Dialog;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhoneBook extends AppCompatActivity
{
    TextView result_app,result2_app,temp;
    String id1;
    Button button_insert,button_display,button_pick,button_delete,button_edit,button_select;
    EditText et1;
    final Context context = this;
    private Button button;
    private Button button_group;
    private EditText result;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);
        button_insert =(Button) findViewById(R.id.b_insert);
       // button_display = (Button)findViewById(R.id.b_display);
        button_pick = (Button)findViewById(R.id.b_pick);
        button_edit = (Button)findViewById(R.id.b_edit);
        button_group = (Button)findViewById(R.id.button_group);
       // temp = (TextView)findViewById(R.id.textView2);

        display();

       // button_delete = (Button)findViewById(R.id.b_delete);
        result_app = (TextView)findViewById(R.id.result);
       // result2_app = (TextView)findViewById(R.id.result_2);
        //et1 = (EditText)findViewById(R.id.editText);




        button_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PhoneBook.this,GroupList.class);
                startActivityForResult(i,5);
            }
        });
/*
ArrayList<String> ArrayL = new ArrayList<>();

        final String[] GROUP_PROJECTION = new String[] {
                ContactsContract.Groups._ID, ContactsContract.Groups.TITLE };

        Cursor gC = getContentResolver().query(
                ContactsContract.Groups.CONTENT_URI, GROUP_PROJECTION,null,null,null);
        gC.moveToFirst();
        while (!gC.isAfterLast()) {
            int idcolumn = gC.getColumnIndex(ContactsContract.Groups.TITLE);
            int a2 = gC.getColumnIndex(ContactsContract.Groups._ID);
            String id1 = gC.getString(a2);
            String Id = gC.getString(idcolumn);
            ArrayL.add(id1);
            ArrayL.add(Id);
            gC.moveToNext();
        }
        LinkedHashSet<String> s = new LinkedHashSet<String>();
        s.addAll(ArrayL);
        ArrayL.clear();
        ArrayL.addAll(s);

        StringBuilder builder = new StringBuilder();
        for (String details : ArrayL) {
            builder.append(details + "\n");
        }

         result2_app.setText(builder.toString());

        String groupId = "4";
        String where = ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID + "="
                + groupId + " AND "
                + ContactsContract.CommonDataKinds.GroupMembership.MIMETYPE + "='"
                + ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE + "'";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{
                        ContactsContract.CommonDataKinds.GroupMembership.RAW_CONTACT_ID,
                        ContactsContract.Data.DISPLAY_NAME
                }, where, null, ContactsContract.Data.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
String[] result1 = new String[c.getCount()];

        while (c.moveToNext())
        {
            String name=c.getString(c.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            result1[c.getPosition()]= name +" : "+ "\n";
        }
        for(int i=0;i<result1.length;i++)
        {
            temp.append(result1[i]);
        }

        Log.i("cursorc", c.getCount() + "");

*/

        button_edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                // pickContact.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                //  pickContact.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(pickContact, 2);
            }
        });


        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PhoneBook.this,MainActivity2.class);
                startActivity(i);
            }

        });



        button_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                 pickContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                // pickContact.setType(ContactsContract.RawContacts.CONTENT_TYPE);
             //  pickContact.setType(ContactsContract.Contacts.CONTENT_TYPE);
                startActivityForResult(pickContact, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1) && (resultCode == RESULT_OK))
        {
            Uri contactData = data.getData();
            Cursor c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {
                int a1 = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                //int a1 = c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                int phoneIndex = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
               int email = c.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                int add = c.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.DISPLAY_NAME);
                int comp = c.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY);



                int id = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                //int phoneIndex = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

                //Toast.makeText(PhoneBook.this,+a1,Toast.LENGTH_SHORT).show();
                    //String num1 = c.getString(a3);
                   // String em = c.getString(email);
                    String num = c.getString(phoneIndex);
                    String nam = c.getString(a1);
                    String email1 = c.getString(email);
                    String id1 = c.getString(id);
                    String add1 = c.getString(add);
                    String co = c.getString(comp);

                Toast.makeText(PhoneBook.this, nam + " Deleted ", Toast.LENGTH_LONG).show();
                    Context ctx = getApplicationContext();

                   Boolean abs = deleteContact(ctx, num, nam);


            }while (c.moveToNext());

        }

        if ((requestCode == 2) && (resultCode == RESULT_OK))
        {

            Uri contactData = data.getData();
            Cursor c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst())
            {
                int id = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
                id1 = c.getString(id);
            }while (c.moveToNext());
            modifyContact();
            display();

        }
    }

    private void display()
    {
        AsyncTask<Void, Void, StringBuilder> task = new AsyncTask<Void, Void, StringBuilder>()
        {
            @Override
            protected StringBuilder doInBackground(Void... params) {
                //String[] result = getNames();
                String[] result = getNames();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < result.length; i++)
                    sb.append(result[i]);
                return sb;
            }

            @Override
            protected void onPostExecute(StringBuilder sb) {

                result_app.setText(sb.toString());
            }
        };
        task.execute();
    }

    private void modifyContact()
    {
        if(id1 != null)
        {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.extra);
            dialog.setTitle("Edit Contact");
            result = (EditText) dialog.findViewById(R.id.editText2);
            button = (Button) dialog.findViewById(R.id.button_submit);

            result.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    result.setText("");
                    return false;
                }
            });
            // if button is clicked, close the custom dialog
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    number = result.getText().toString();



                    try {
                        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

                        ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                                .withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=? AND " +
                                                ContactsContract.Data.MIMETYPE + "='" +
                                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",

                                        new String[]{id1})
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                                .build());

                        ContentProviderResult[] result = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
//                    Toast.makeText(PhoneBook.this,result.length,Toast.LENGTH_SHORT).show();
                    } catch (Exception e)
                    {
                        Log.w("UpdateContact", e.getMessage() + "");
                        for (StackTraceElement ste : e.getStackTrace()) {
                            Log.w("UpdateContact", "\t" + ste.toString());
                        }
                        Context ctx = getApplicationContext();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(ctx, "Update failed", duration);
                        toast.show();
                    }

                    dialog.dismiss();
                }
            });

            dialog.show();
        }

        }
    private void modify2(String num,String id1)
    {
        number = "";

        try {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(ContactsContract.CommonDataKinds.Phone._ID + "=? AND " +
                                    ContactsContract.Data.MIMETYPE + "='" +
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "'",

                            new String[]{id1})
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                    .build());

            ContentProviderResult[] result = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
//                    Toast.makeText(PhoneBook.this,result.length,Toast.LENGTH_SHORT).show();
        } catch (Exception e)
        {
            Log.w("UpdateContact", e.getMessage() + "");
            for (StackTraceElement ste : e.getStackTrace()) {
                Log.w("UpdateContact", "\t" + ste.toString());
            }
            Context ctx = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(ctx, "Update failed", duration);
            toast.show();
        }
    }

    private void insertContactWithIntent()
    {
        /**
         * Listing 8-40: Inserting a new contact using an Intent
         */
        String a1 = et1.getText().toString();
        if(a1.length()!= 0)
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
            row.put(ContactsContract.CommonDataKinds.Phone.NUMBER,a1);
            data.add(row);

            ContentValues row1 = new ContentValues();
            row1.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE);
            row1.put(ContactsContract.CommonDataKinds.Organization.COMPANY, "Android");
            data.add(row1);

            ContentValues row2 = new ContentValues();
            row2.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
            row2.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM);
            row2.put(ContactsContract.CommonDataKinds.Email.LABEL, "Green Bot");
            row2.put(ContactsContract.CommonDataKinds.Email.ADDRESS, "android@android.com");

            data.add(row2);


            Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
            intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);

            startActivity(intent);
        }
        else {
            Toast.makeText(PhoneBook.this,"Enter a Number!",Toast.LENGTH_SHORT).show();
        }
    }


    private String[] getNames() {
        /**
         * Listing 8-36: Accessing the Contacts Contract Contact Content Provider
         */
        // Create a projection that limits the result Cursor
        // to the required columns.
        String[] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,
                ContactsContract.CommonDataKinds.GroupMembership.DISPLAY_NAME,
                ContactsContract.Contacts._COUNT
        };

        // Get a Cursor over the Contacts Provider.
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, null, null, null);

        // Get the index of the columns.
        //int nameIdx =
           //     cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        //int idIdx =
        //        cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
        int hasnum =
               cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.HAS_PHONE_NUMBER);

        // Initialize the result set.
        String[] result = new String[cursor.getCount()];

        // Iterate over the result Cursor.
        while(cursor.moveToNext()) {
            // Extract the name.
            String name=cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //String name = cursor.getString(nameIdx);
            // Extract the unique ID.
            //String id = cursor.getString(idIdx);

            String hs = cursor.getString(hasnum);
            String phoneNumber = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER));


                    result[cursor.getPosition()] = name +" : "+phoneNumber   + "\n";


        }

        // Close the Cursor.
        cursor.close();

        //
        return result;
    }

    public  Boolean deleteContact(Context ctx, String phone, String name) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor cur = ctx.getContentResolver().query(contactUri, null, null, null, null);
        //Toast.makeText(PhoneBook.this,cur.getCount(),Toast.LENGTH_SHORT).show();
        try {
            if (cur.moveToFirst()) {
                do {
                    if (cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        ctx.getContentResolver().delete(uri, null, null);
                      // ctx.getContentResolver().update(uri,null,null,null);
                        this.display();
                        return true;
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        this.display();
        return false;
    }
}
