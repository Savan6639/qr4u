package com.example.qrgenerator

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.qrgenerator.R
import com.example.qrgenerator.databinding.ActivityMainBinding
import com.example.qrgenerator.ui.dashboard.DashboardFragment
import com.example.qrgenerator.ui.home.HomeFragment
import com.example.qrgenerator.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    val fragmentManager: FragmentManager = supportFragmentManager
    val fmHome: Fragment = HomeFragment()
    val fmDashboard: Fragment = DashboardFragment()
    val fmProfile: Fragment = ProfileFragment()
    private lateinit var binding: ActivityMainBinding
    var fragmentId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


          hidekeyboard()
//        hideSystemUI()
          getSupportActionBar()?.hide();    //hide Actionbar

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentId = intent.getIntExtra("FRAGMENT_ID", 0)

        Log.e("","==  "+fragmentId)


/*
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
*/
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        binding.mainBottom.setOnItemSelectedListener { item ->

            lateinit var fragment: Fragment
            when (item.itemId) {

                R.id.navigation_home -> fragment = fmHome
                R.id.navigation_dashboard -> fragment = fmDashboard
                R.id.navigation_notifications -> fragment = fmProfile
            }
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).detach(fragment)
                .attach(fragment).commit()
            true
        }

        if(fragmentId == 1){
            binding.mainBottom.selectedItemId = R.id.navigation_notifications
            val clubInfo = ProfileFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, clubInfo).commit()

        }
        else{
            binding.mainBottom.selectedItemId = R.id.navigation_home
        }
     /*   val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
*/

    }

    //  Hide Keyboard
    fun hidekeyboard(){
        val view = this.currentFocus
        if (view != null)
        {

            val hideMe = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN )

    }


//    fun hideSystemUI() {
//        val decorView = window.decorView
//        // Set the IMMERSIVE flag.
//        // Set the content to appear under the system bars so that the content
//        // doesn't resize when the system bars hide and show.
//        decorView.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE  or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                    or  View.SYSTEM_UI_FLAG_IMMERSIVE)
//    }
}