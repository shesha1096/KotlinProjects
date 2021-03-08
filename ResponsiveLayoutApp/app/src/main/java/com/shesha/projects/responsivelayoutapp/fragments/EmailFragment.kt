package com.shesha.projects.responsivelayoutapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.responsivelayoutapp.R
import com.shesha.projects.responsivelayoutapp.adapters.EmailRecyclerAdapter
import com.shesha.projects.responsivelayoutapp.models.Email
import java.util.ArrayList

class EmailFragment : Fragment() {
    private lateinit var emailRecyclerView : RecyclerView
    private lateinit var emailRecyclerAdapter: EmailRecyclerAdapter
    private var emailList : ArrayList<Email> = ArrayList()

    companion object {
        fun newInstance() : EmailFragment
        {
            return EmailFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_email,container,false)
        emailRecyclerView = view.findViewById(R.id.email_recycler_view)
        for(i in 1..10)
        {
            var email : Email = Email("Kotlin Dagger Essentials","This article explains the importance of using Dagger dependency injection","15.41")
            emailList.add(email)
        }
        emailRecyclerAdapter = context?.let { EmailRecyclerAdapter(emailList, it) }!!
        emailRecyclerView.apply {
            adapter = emailRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }
        return view
    }
}