package com.example.firstexample2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        findViewById<Button>(R.id.button).setOnClickListener { view ->
            val number1: Double = findViewById<EditText>(R.id.editTextTextPersonName).text.toString().toDouble();
            val number2: Double = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString().toDouble();
            findViewById<TextView>(R.id.textView).text = (number1 + number2).toString();
        }

        findViewById<Button>(R.id.button2).setOnClickListener { view ->
            val number1: Double = findViewById<EditText>(R.id.editTextTextPersonName).text.toString().toDouble();
            val number2: Double = findViewById<EditText>(R.id.editTextTextPersonName2).text.toString().toDouble();
            findViewById<TextView>(R.id.textView).text = (number1 - number2).toString();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}