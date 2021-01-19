package com.shesha.projects.kotlinpracticeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var drawerLayout : DrawerLayout
    lateinit var navView : NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.background_service -> startBackgroundActivity()
                R.id.foreground_service -> startForegroundActivity()
                R.id.work_manager -> startWorkManagerActivity()
                R.id.work_manager_constraints -> startConstraintsWorkManagerActivity()
                R.id.broadcast_receiver_demo -> startBroadcastReceiverDemo()
                R.id.check_internet_connection -> startCheckingInternetConnection()
                R.id.navigation_example -> startNavigationExample()
            }
            true
        }

    }

    private fun startNavigationExample()
    {
        var intent : Intent = Intent(this@MainActivity,NavigationExampleActivity::class.java)
        startActivity(intent)
    }

    private fun startCheckingInternetConnection()
    {
        var intent : Intent = Intent(this@MainActivity,InternetConnectivityActivity::class.java)
        startActivity(intent)
    }

    private fun startBroadcastReceiverDemo()
    {
        var intent : Intent = Intent(this@MainActivity,BroadcastReceiverActivity::class.java)
        startActivity(intent)
    }

    private fun startConstraintsWorkManagerActivity()
    {
        var intent : Intent = Intent(this@MainActivity,WorkManagerConstraintsActivity::class.java)
        startActivity(intent)
    }

    private fun startWorkManagerActivity()
    {
        var intent : Intent = Intent(this@MainActivity,WorkManagerLocationActivity::class.java)
        startActivity(intent)
    }

    private fun startForegroundActivity()
    {
        var intent : Intent = Intent(this@MainActivity,ForegroundServiceActivity::class.java)
        startActivity(intent)
    }

    private fun startBackgroundActivity()
    {
        var intent : Intent = Intent(this@MainActivity,BackgroundServiceActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}