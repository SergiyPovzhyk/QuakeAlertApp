package com.example.quakealertapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.quakealertapp.ui.AboutFragment
import com.example.quakealertapp.ui.MainFragment
import com.example.quakealertapp.ui.RecommendationFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout:DrawerLayout
    lateinit var toggle:ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(R.style.Theme_QuakeAlertApp)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setupDrawer()
    }



    private fun setupDrawer() {
        val navView:NavigationView = findViewById(R.id.nav_view)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.close,R.string.open)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId){
                R.id.menu_main_item -> replaceFragment(MainFragment())
                R.id.menu_recommend_item -> replaceFragment(RecommendationFragment())
                R.id.menu_about_item -> replaceFragment(AboutFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()

    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }


}