package com.alirnp.androidwoocommerceapp.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.alirnp.androidwoocommerceapp.R
import com.alirnp.androidwoocommerceapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //  initBottomNavigation()
        setUpNavigation()

    }

    private fun setUpNavigation() {

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {

            onNavDestinationSelected(it, Navigation.findNavController(this, R.id.mainFragment))

/*            if (it.itemId == R.id.profileFragment) {

                //  navHostFragment?.navController?.navigate(R.id.action_profileFragment_to_registerFragment)

            }*/
            return@setOnNavigationItemSelectedListener true
        }
    }


    private fun initBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.categoryFragment -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.basketFragment -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profileFragment -> {
                    Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }


}