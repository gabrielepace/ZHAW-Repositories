package com.example.stonks;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class Stock {
        String name;
        String symbol;
        Double value;

        public Stock(String name, String symbol, Double value) {
            this.name = name;
            this.symbol = symbol;
            this.value = value;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Stock> items = new ArrayList<Stock>();
        items.add(new Stock("Microsoft", "MSFT", 214.36));
        items.add(new Stock("Google", "GOOGL", 1519.45));
        items.add(new Stock("Salesforce", "CRM", 255.52));
        items.add(new Stock("Facebook", "FB", 260.02));
        items.add(new Stock("Amazon", "AMZN", 3201.86));
        items.add(new Stock("eBay", "EBAY", 54.05));
        items.add(new Stock("Twitter", "TWTR", 45.41));
        items.add(new Stock("Snapchat", "SNAP", 28.11));


//create object of listview
        ListView listView=(ListView)findViewById(R.id.listview);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<>();

//Add elements to arraylist
        for (int i = 0; i < items.size(); i++)
        {
            arrayList.add(items.get(i).name + ": " + items.get(i).value);
        }



//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"clicked item:"+i+" "+arrayList.get(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}