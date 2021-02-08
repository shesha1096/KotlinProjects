package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R

class ProfileAdapter(context : Context, settingTitles : List<String>, settingIcons : List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var settingTitles : List<String>
    private lateinit var settingIcons : List<Int>

    init {
        this.context = context
        this.settingTitles = settingTitles
        this.settingIcons = settingIcons
    }

    class ProfileViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        var settingsImage : ImageView = view.findViewById(R.id.settings_vector)
        var settingsTitle : TextView = view.findViewById(R.id.settings_title)
    }

    class LoginFingerPrintViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        var settingsImage : ImageView = view.findViewById(R.id.settings_fingerprint_vector)
        var settingsTitle : TextView = view.findViewById(R.id.settings_fingerprint_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0)
        {
            return ProfileViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.settings_card_item, parent, false)
            )
        }
        return LoginFingerPrintViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.settings_fingerprint_card_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return settingTitles.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == settingTitles.size - 1)
        {
            var loginHolder : LoginFingerPrintViewHolder = holder as LoginFingerPrintViewHolder
            loginHolder.settingsTitle.text = settingTitles.get(position)

        }
        else
        {
            var settingsHolder : ProfileViewHolder = holder as ProfileViewHolder
            settingsHolder.settingsImage.setImageResource(settingIcons.get(position))
            settingsHolder.settingsTitle.text = settingTitles.get(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == settingTitles.size - 1)
        {
            return 1
        }
        return 0
    }
}