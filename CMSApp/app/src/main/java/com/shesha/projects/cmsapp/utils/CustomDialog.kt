package com.shesha.projects.cmsapp.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.shesha.projects.cmsapp.R

class CustomDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var alertDialogBuilder : AlertDialog.Builder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Select Language")
        val checkedLanguageArray = booleanArrayOf(true,false)
        alertDialogBuilder.setMultiChoiceItems(R.array.languageoptions, checkedLanguageArray) { dialog, which, isChecked ->
            val checkedLanguagesArray = activity?.resources?.getStringArray(R.array.languageoptions)
            if (isChecked)
            {
                // Update the current focused item's checked status
                checkedLanguageArray[which] = isChecked
                if (which == 0)
                {
                    val languageContext = context?.let { LocaleHelper.setLocale(it,"en") }
                    val resources = languageContext?.resources
                }
            }


            // Get the current focused item
            val currentItem = checkedLanguagesArray?.get(which)
            // Notify the current action

        }
        alertDialogBuilder.setPositiveButton("OK",DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()

        })
        alertDialogBuilder.setNegativeButton("Cancel",DialogInterface.OnClickListener(){dialog, which ->
            dialog.dismiss()
        })
        return alertDialogBuilder.create()
    }
}