package com.example.moba1.sep.restaurantfinder

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listRestaurant = mutableListOf(
            Restaurant("Pöstli Stübli", "", "Bahnhofstrasse", 41,
                3920, "https://www.hotelpost.ch/poestli_stuebli_153.html", "Zermatt", "", "+41 27 9671931", "limited")
        )

        fun loadRestaurant(restaurant: Restaurant){
            var name : TextView = findViewById(R.id.name)
            var cuisine : TextView = findViewById(R.id.cuisine)
            var street : TextView = findViewById(R.id.street)
            var housenumber : TextView = findViewById(R.id.housenumber)
            var postcode : TextView = findViewById(R.id.postcode)
            var website : TextView = findViewById(R.id.website)
            var city : TextView = findViewById(R.id.city)
            var opening_hours : TextView = findViewById(R.id.opening_hours)
            var phone : TextView = findViewById(R.id.phone)
            var wheelchair : TextView = findViewById(R.id.wheelchair)

            name.text = restaurant.name;
            cuisine.text = restaurant.cuisine;
            street.text = restaurant.street;
            housenumber.text = restaurant.housenumber.toString();
            postcode.text = restaurant.postcode.toString();
            website.text = restaurant.website;
            city.text = restaurant.city;
            opening_hours.text = restaurant.opening_hours;
            phone.text = restaurant.phone;
            wheelchair.text = restaurant.wheelchair;
        }

        val restaurant = Restaurant("Pöstli Stübli", "", "Bahnhofstrasse", 41,
            3920, "https://www.hotelpost.ch/poestli_stuebli_153.html", "Zermatt", "", "+41 27 9671931", "limited");

        listRestaurant.add(restaurant);

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
