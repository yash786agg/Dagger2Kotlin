package com.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import dagger.android.support.DaggerAppCompatActivity
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.app.daggerkotlin.R
import androidx.navigation.ui.NavigationUI
import androidx.navigation.Navigation
import androidx.core.view.GravityCompat
import android.view.MenuItem
import androidx.navigation.NavOptions
import com.app.ui.auth.AuthActivity
import com.app.util.SessionManager
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() , NavigationView.OnNavigationItemSelectedListener
{
    @Inject lateinit var sessionManager : SessionManager
    private var drawerLayout: DrawerLayout? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        init()
    }

    private fun init()
    {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView!!, navController)
        navigationView!!.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

        when (menuItem.itemId)
        {
            R.id.nav_posts ->
            {
                // Nav Options to Clear Back Stack
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.main, true).build()

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postScreen, null, navOptions)
            }

            R.id.nav_news ->
            {
                if (isValidDestination(R.id.newsScreen))
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.newsScreen)
            }
        }

        menuItem.isChecked = true
        drawerLayout!!.closeDrawer(GravityCompat.START)

        return true
    }

    private fun isValidDestination(destination: Int): Boolean = destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination!!.id

    override fun onSupportNavigateUp(): Boolean = NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout)

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.logout ->
            {
                sessionManager.clear()

                val intent = Intent(this,AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
                return true
            }

            android.R.id.home -> return if (drawerLayout!!.isDrawerOpen(GravityCompat.START))
            {
                drawerLayout!!.closeDrawer(GravityCompat.START)
                true
            }
            else
                false
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed()
    {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START))
            drawerLayout!!.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}