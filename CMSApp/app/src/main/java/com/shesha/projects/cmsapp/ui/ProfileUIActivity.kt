package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.ProfileAdapter

class ProfileUIActivity : AppCompatActivity() {
    private var settingTitles : MutableList<String> = mutableListOf()
    private var settingIcons : MutableList<Int> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_u_i)
        recyclerView = findViewById(R.id.settings_recycler_view)
        settingTitles.add("Saved Payment Details")
        settingTitles.add("My Addresses")
        settingTitles.add("Favourites")
        settingTitles.add("Reset Password")
        settingTitles.add("Login using Fingerprint")
        settingIcons.add(R.drawable.ic_baseline_credit_card_24)
        settingIcons.add(R.drawable.ic_baseline_location_on_24)
        settingIcons.add(R.drawable.ic_baseline_favorite_24)
        settingIcons.add(R.drawable.ic_baseline_vpn_key_24)
        settingIcons.add(R.drawable.ic_baseline_vpn_key_24)
        val profileAdaper = ProfileAdapter(this,settingTitles,settingIcons)
        recyclerView.adapter = profileAdaper
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}