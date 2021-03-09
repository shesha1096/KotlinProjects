package com.shesha.projects.wearosapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.shesha.projects.wearosapp.R
import com.shesha.projects.wearosapp.models.Note
import org.w3c.dom.Text
import java.util.ArrayList

class NoteListAdapter(context: Context, resource: Int, objects: MutableList<Note>) :
    ArrayAdapter<Note>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (position == 0)
        {
            return LayoutInflater.from(context).inflate(R.layout.new_note,parent,false)
        }
        var view : View = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,parent,false)
        var note : Note? = getItem(position)
        var title : TextView = view.findViewById(android.R.id.text1)
        if (note != null) {
            title.text = note.title
        }
        return view
    }
}