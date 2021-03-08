package com.shesha.projects.responsivelayoutapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.responsivelayoutapp.R
import com.shesha.projects.responsivelayoutapp.models.Email
import java.util.ArrayList

class EmailRecyclerAdapter(emailList : ArrayList<Email>, context : Context) : RecyclerView.Adapter<EmailRecyclerAdapter.EmailViewHolder>() {

    private var emailList = emailList
    private var context = context

    class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var emailTitle : TextView = itemView.findViewById(R.id.email_title)
        var emailDescription : TextView = itemView.findViewById(R.id.email_description)
        var emailTiming : TextView = itemView.findViewById(R.id.email_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.email_view_item,parent,false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        holder.emailTitle.text = emailList.get(position).emailTitle
        holder.emailDescription.text = emailList.get(position).emailDescription
        holder.emailTiming.text = emailList.get(position).emailDate
    }

    override fun getItemCount(): Int {
        return emailList.size
    }
}