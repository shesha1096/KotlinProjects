package com.shesha.projects.responsivelayoutapp

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.shesha.projects.responsivelayoutapp.fragments.EmailFragment
import com.shesha.projects.responsivelayoutapp.fragments.SocialEmailFragment
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var listView : ListView
    private lateinit var emailOptions : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        listView = findViewById(R.id.listview_drawer)
        emailOptions = ArrayList()
        emailOptions.add("Primary")
        emailOptions.add("Promoted")
        emailOptions.add("Social")
        emailOptions.add("Spam")
        if (listView != null)
        {
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, emailOptions)
            listView.adapter = adapter
        }
        listView.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val o: Any = listView.getItemAtPosition(position)
                val str: String =
                    o as String //As you are using Default String Adapter
                when (str)
                {
                    "Primary" ->
                    {
                        var fragmentManager : FragmentManager = supportFragmentManager
                        fragmentManager.beginTransaction().replace(R.id.frame_layout, EmailFragment()).commit()
                    }
                    "Social" ->
                    {
                        var fragmentManager : FragmentManager = supportFragmentManager
                        fragmentManager.beginTransaction().replace(R.id.frame_layout, SocialEmailFragment()).commit()
                    }
                }

            }
        val metrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(metrics)
        val yInches = metrics.heightPixels / metrics.ydpi
        val xInches = metrics.widthPixels / metrics.xdpi
        val diagonalInches = Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
        if (diagonalInches >= 6.5)
        {
            drawerLayout.visibility = View.GONE
        }
        Log.d("SIZE",diagonalInches.toString())
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var fragmentManager : FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frame_layout, EmailFragment()).commit()
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.primary_emails -> {
                    var fragmentManager: FragmentManager = supportFragmentManager
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, EmailFragment())
                        .commit()
                    if (diagonalInches < 6.5) {
                        drawerLayout.closeDrawers()
                    }

                }

                R.id.social_emails -> {
                    var fragmentManager: FragmentManager = supportFragmentManager
                    fragmentManager.beginTransaction().replace(
                        R.id.frame_layout,
                        SocialEmailFragment()
                    )
                        .commit()
                    if (diagonalInches < 6.5) {
                        drawerLayout.closeDrawers()
                    }
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}