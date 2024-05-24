package com.betrybe.trybnb.ui.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.ActivityMainBinding
import com.betrybe.trybnb.ui.views.fragments.CreateReservationFragment
import com.betrybe.trybnb.ui.views.fragments.ProfileFragment
import com.betrybe.trybnb.ui.views.fragments.ReservationFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val reservationFragment = ReservationFragment()
    private val createReservationFragment = CreateReservationFragment()
    private val profileFragment = ProfileFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationBottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.reservation_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, reservationFragment)
                        .commit()
                }

                R.id.create_reservation_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, createReservationFragment)
                        .commit()
                }

                R.id.profile_menu_tem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container, profileFragment)
                        .commit()
                }
            }
            true
        }
    }
}
