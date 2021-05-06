package com.example.sedmahra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.sedmahra.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = this.findNavController(R.id.mainFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

/*
        val arr: ArrayList<Int> = ArrayList<Int>();
        arr.add(R.drawable.karta_10c);
        arr.add(R.drawable.karta_10g);
        arr.add(R.drawable.karta_10l);




        val karta : ImageButton = binding.kartadole;
        var i: Int = 0;
        karta.setOnClickListener() {
            karta.setImageResource(arr[i]);

            i++;
            if(i == 3) {
                i = 0;
            }
        }*/

    }

    override fun onSupportNavigateUp() :Boolean {
        val navController = this.findNavController(R.id.mainFragment)
        return navController.navigateUp()
    }

}