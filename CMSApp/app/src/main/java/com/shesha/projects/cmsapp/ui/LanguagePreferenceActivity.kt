package com.shesha.projects.cmsapp.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.utils.LocaleHelper

class LanguagePreferenceActivity : AppCompatActivity() {
    private lateinit var button : Button
    private lateinit var textView : TextView
    private var languageCode : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_preference)
        button = findViewById(R.id.select_language_btn)
        textView = findViewById(R.id.display_text_id)
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val language = sharedPref.getString(getString(R.string.file_name),"none")
        if (language != "none")
        {
            when (language)
            {
                "en" -> {
                    val languageContext = this?.let { LocaleHelper.setLocale(this,"en") }
                    val resources = languageContext?.resources
                    textView.text = resources.getString(R.string.display_message)
                    window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                }
                "hi" -> {
                    val languageContext = this?.let { LocaleHelper.setLocale(this,"hi") }
                    val resources = languageContext?.resources
                    textView.text = resources.getString(R.string.display_message)
                    window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                }
                "ar" -> {
                    val languageContext = this?.let { LocaleHelper.setLocale(this,"ar") }
                    val resources = languageContext?.resources
                    textView.text = resources.getString(R.string.display_message)
                    window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                }
            }
        }
        button.setOnClickListener(View.OnClickListener {
            var alertDialogBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Select Language")
            val checkedLanguageArray = booleanArrayOf(true,false,false)
            alertDialogBuilder.setMultiChoiceItems(R.array.languageoptions, checkedLanguageArray) { dialog, which, isChecked ->
                val checkedLanguagesArray = this?.resources?.getStringArray(R.array.languageoptions)
                if (isChecked)
                {
                    // Update the current focused item's checked status
                    checkedLanguageArray[which] = isChecked
                    if (which == 0)
                    {
                        val languageContext = this?.let { LocaleHelper.setLocale(this,"en") }
                        val resources = languageContext?.resources
                        textView.text = resources.getString(R.string.display_message)
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                        languageCode = "en"

                    }
                    else if (which == 1){
                        val languageContext = this?.let { LocaleHelper.setLocale(this,"hi") }
                        val resources = languageContext?.resources
                        textView.text = resources.getString(R.string.display_message)
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_LTR
                        languageCode = "hi"
                    }
                    else
                    {
                        val languageContext = this?.let { LocaleHelper.setLocale(this,"ar") }
                        val resources = languageContext?.resources
                        textView.text = resources.getString(R.string.display_message)
                        window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
                        languageCode = "ar"
                    }
                }

                val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: null
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString(getString(R.string.file_name), languageCode)
                        apply()
                    }
                }




                // Get the current focused item
                val currentItem = checkedLanguagesArray?.get(which)
                // Notify the current action

            }
            alertDialogBuilder.setPositiveButton("OK",
                DialogInterface.OnClickListener{ dialog, which ->
                dialog.dismiss()

            })
            alertDialogBuilder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener(){ dialog, which ->
                dialog.dismiss()
            })
            alertDialogBuilder.create().show()

        })
    }
}