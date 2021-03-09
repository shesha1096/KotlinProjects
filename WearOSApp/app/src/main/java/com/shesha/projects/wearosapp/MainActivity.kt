package com.shesha.projects.wearosapp

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.wearable.activity.WearableActivity
import android.widget.AdapterView
import android.widget.ListView
import com.shesha.projects.wearosapp.adapters.NoteListAdapter
import com.shesha.projects.wearosapp.models.Note
import com.shesha.projects.wearosapp.utils.Helper

class MainActivity : WearableActivity() {
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        listView = findViewById(R.id.notes_list_view)
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                if (position == 0)
                {
                    displaySpeechScreen()
                }

            }
        updateUI()
    }

    private fun displaySpeechScreen() {
        var intent : Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"What is the title?")
        startActivityForResult(intent,100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100 && resultCode == RESULT_OK)
        {
            var results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            var title : String? = results?.get(0)
            var note : Note? = title?.let { Note(null, it) }
            if (note != null) {
                Helper.saveNote(note,this)
            }
            updateUI()
        }
    }

    private fun updateUI() {
        var noteList = Helper.getAllNotes(this)
        noteList.add(0,Note("",""))
        listView.adapter = NoteListAdapter(this,0,noteList)
    }
}