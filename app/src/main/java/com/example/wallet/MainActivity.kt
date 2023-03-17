package com.example.wallet


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.wallet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonToRegister.setOnClickListener {
            val intent = Intent(this, LogIn_Register_Activity::class.java)
            startActivity(intent)
        }

        //MENU
        fun intentpass(){
            val intent = Intent(this, AddOrEditWallet_Activity::class.java)
            startActivity(intent)
        }

        findViewById<BottomNavigationView>(R.id.bottom_menu).setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.wallets_page->intentpass()
            }
            true
        }

    }
    }






