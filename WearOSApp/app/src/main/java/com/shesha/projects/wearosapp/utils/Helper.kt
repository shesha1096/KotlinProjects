package com.shesha.projects.wearosapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.shesha.projects.wearosapp.models.Note
import java.util.ArrayList

class Helper {
    companion object{
        public fun saveNote(note : Note, context : Context) : String
        {
            var sharedPreference : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            var editor : SharedPreferences.Editor = sharedPreference.edit()
            var id : String = System.currentTimeMillis().toString()
            editor.putString(id,note.title)
            editor.commit()
            return id
        }

        public fun getAllNotes(context: Context) : ArrayList<Note>
        {
            var sharedPreference : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            var notes : ArrayList<Note> = arrayListOf()
            var noteMap : Map<String,String> = sharedPreference.all as Map<String, String>
            for (noteTitle in noteMap.entries)
            {
                var note : Note = Note(noteTitle.key,noteTitle.value)
                notes.add(note)
            }
            return notes
        }

        public fun removeNote(id : String, context: Context)
        {
            var sharedPreference : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            var editor : SharedPreferences.Editor = sharedPreference.edit()
            editor.remove(id)
            editor.commit()
        }
    }
}