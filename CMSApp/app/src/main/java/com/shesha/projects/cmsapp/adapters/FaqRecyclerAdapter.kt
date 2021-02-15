package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.Faq
import java.util.*

class FaqRecyclerAdapter(private val context: Context, faqArrayList: ArrayList<Faq>) : RecyclerView.Adapter<FaqRecyclerAdapter.FaqViewHolder>() {
    val faqArrayList: ArrayList<Faq> = faqArrayList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.faq_card_item, parent, false)
        return FaqViewHolder(view,faqArrayList)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.questionView.setText(faqArrayList[position].question)
        holder.answerView.setText(faqArrayList[position].answer)
        val isExpanded: Boolean = faqArrayList[position].isExpanded
        holder.expandableRelativeLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

        holder.questionView.setOnClickListener {
            val faq: Faq = faqArrayList[position]
            faq.isExpanded = !faq.isExpanded
            notifyItemChanged(position)

        }
    }

    override fun getItemCount(): Int {
        return faqArrayList.size
    }


    class FaqViewHolder(itemView: View, faqArrayList: ArrayList<Faq>) : RecyclerView.ViewHolder(itemView) {
        val questionView: TextView
        val answerView: TextView
        val expandableRelativeLayout: RelativeLayout

        init {
            questionView = itemView.findViewById(R.id.question_title)
            answerView = itemView.findViewById(R.id.faq_answer)
            expandableRelativeLayout = itemView.findViewById(R.id.expandable_layout)

        }


    }

}
