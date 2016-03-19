package com.cs442.shash5259.Assignment5;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class GroupList extends AppCompatActivity
{

    TextView result2_app,temp;
    private ListView lv;
    List<Order> myOrder = new ArrayList<>();
    final Context context = this;
    TextView t1,t6;
    String[] result1;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView)findViewById(R.id.listView);


        //result2_app = (TextView)findViewById(R.id.result_2);
        temp = (TextView)findViewById(R.id.textView2);
        t6 = (TextView)findViewById(R.id.textView6);
        b2= (Button)findViewById(R.id.button4);
        ArrayList<String> ArrayL = new ArrayList<>();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupList.this,PhoneBook.class);
                startActivity(i);
            }
        });


        final String[] GROUP_PROJECTION = new String[] {ContactsContract.Groups._ID, ContactsContract.Groups.TITLE };
       // final String[] GROUP_PROJECTION = new String[] {ContactsContract.CommonDataKinds.Organization._ID, ContactsContract.CommonDataKinds.Organization.COMPANY};

        Cursor gC = getContentResolver().query(ContactsContract.Groups.CONTENT_URI, GROUP_PROJECTION,null,null,null);
        gC.moveToFirst();
        while (!gC.isAfterLast()) {
            int idcolumn = gC.getColumnIndex(ContactsContract.Groups.TITLE);
            int a2 = gC.getColumnIndex(ContactsContract.Groups._ID);
            String id1 = gC.getString(a2);
            String Id = gC.getString(idcolumn);
            ArrayL.add(id1);
            myOrder.add(new Order(id1,Id));
            ArrayL.add(Id);
            gC.moveToNext();
        }
        LinkedHashSet<String> s = new LinkedHashSet<String>();
        s.addAll(ArrayL);
        ArrayL.clear();
        ArrayL.addAll(s);



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
         result1 = new String[c.getCount()];

        while (c.moveToNext())
        {
            String name=c.getString(c.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            result1[c.getPosition()]= name +" : "+ "\n";
        }

        Log.i("cursorc", c.getCount() + "");
        populateListView();
        registerClickCallBack();
    }
    private void populateListView()
    {
        final ArrayAdapter<Order> adapter = new MyListAdapter();
        final ListView lv = (ListView)findViewById(R.id.listView);

        //list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strArr));
        lv.setAdapter(adapter);
        registerClickCallBack();
    }
    public class MyListAdapter extends ArrayAdapter<Order> {
        public MyListAdapter() {
            super(GroupList.this, R.layout.ls, myOrder);
        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.ls, parent, false);
            }
            //find item to work with
            Order currentOrder = myOrder.get(position);

            TextView t1 = (TextView) itemView.findViewById(R.id.textView);
            t1.setText(currentOrder.getId());

            TextView t3 = (TextView) itemView.findViewById(R.id.textView2);
            t3.setText(currentOrder.getItems());
            //lv.setBackgroundColor(Color.WHITE);

            return itemView;
            //return super.getView(position,convertView,parent);
        }
    }
    private void registerClickCallBack()
    {
        final ListView list = (ListView)findViewById(R.id.listView);
        list.setClickable(true);
        list.setItemsCanFocus(true);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ArrayAdapter<Order> adapter = new MyListAdapter();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView t8 = (TextView)findViewById(R.id.textView7);
                t8.setText("");
                Order clickedHotel = myOrder.get(position);
                list.setItemChecked(position, true);

                TextView textView = (TextView) view.findViewById(R.id.textView);
                TextView textView1 = (TextView) view.findViewById(R.id.textView2);

                String a1 = textView.getText().toString();
                String a2 = textView1.getText().toString();



                ArrayList<String> ArrayL = new ArrayList<>();



                // final String[] GROUP_PROJECTION = new String[] {ContactsContract.CommonDataKinds.Organization._ID, ContactsContract.CommonDataKinds.Organization.COMPANY};





                String groupId = a1;
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
                String[] result2 = new String[c.getCount()];

                while (c.moveToNext())
                {
                    String name=c.getString(c.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                    result2[c.getPosition()]= name+ "\n";

                }
                TextView t7 = (TextView)findViewById(R.id.textView7);
                t7.setMovementMethod(new ScrollingMovementMethod());
                if(result2.length != 0)
                {

                    for(int i=0;i<result2.length;i++)
                    {
                        t7.append(result2[i]);
                    }
                }
                else {
                    Toast.makeText(GroupList.this,"No Contacts in this group",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
