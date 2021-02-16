package com.shesha.projects.cmsapp.adapters

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.Faq
import java.util.*


class FaqRecyclerAdapter(private val context: Context, faqArrayList: ArrayList<Faq>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val faqArrayList: ArrayList<Faq> = faqArrayList
    var mExpandedPosition : Int = -1
    var previousExpandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 3)
        {
            return ThreeChildFaqViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.faq_3_child_card_item,parent,false))
        }

        if (viewType == 4)
        {
            return FourChildFaqViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.faq_4_child_card_item,parent,false))
        }

        if (viewType == 2)
        {
            return FaqViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.faq_card_item, parent, false))
        }

        return SingleChildViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.faq_single_card_item, parent, false))
    }

    private fun changeStateOfItemsInLayout(listItem: Faq, p: Int) {
        for (x in 0 until faqArrayList.size) {
            if (x == p) {
                listItem.isExpanded = true
                //Since this is the tapped item, we will skip
                //the rest of loop for this item and set it expanded
                continue
            }
            faqArrayList.get(x).isExpanded = false
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var message : String = "Parent: $position"
        val isExpanded = position==mExpandedPosition
        if (holder.itemViewType == 3)
        {

            val holder = holder as ThreeChildFaqViewHolder
            holder.questionView.setText(faqArrayList[position].question)
            holder.answerView.setText(faqArrayList[position].answer)
            val isExpandedLayout: Boolean = faqArrayList[position].isExpanded
            holder.expandableRelativeLayout.visibility = if (isExpandedLayout) View.VISIBLE else View.GONE
            if (isExpandedLayout)
            {
                previousExpandedPosition = position
            }

            if (holder.expandableRelativeLayout.visibility == View.VISIBLE)
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
            else
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
            }
            holder.questionView.setOnClickListener {
                val faq: Faq = faqArrayList[position]
                faq.isExpanded = !faq.isExpanded
                mExpandedPosition = if (isExpandedLayout) -1 else position
                changeStateOfItemsInLayout(faqArrayList.get(holder.getLayoutPosition()),holder.getLayoutPosition())
                message = "Parent : $position, Child : 1"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }



            holder.expandedQuestionView.setOnClickListener{
                if (holder.secondExpandableRelativeLayout.visibility == View.VISIBLE)
                {
                    holder.secondExpandableRelativeLayout.visibility = View.GONE
                    holder.secondImageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
                }
                else
                {
                    holder.secondExpandableRelativeLayout.visibility = View.VISIBLE
                    holder.secondImageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                }
                message = "Parent : $position, Child : 2"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()

            }
        }

        else if(holder.itemViewType == 4)
        {
            val holder = holder as FourChildFaqViewHolder
            holder.questionView.setText(faqArrayList[position].question)
            holder.answerView.setText(faqArrayList[position].answer)
            val isExpandedLayout: Boolean = faqArrayList[position].isExpanded
            holder.expandableRelativeLayout.visibility = if (isExpandedLayout) View.VISIBLE else View.GONE
            if (isExpandedLayout)
            {
                previousExpandedPosition = position
            }

            if (holder.expandableRelativeLayout.visibility == View.VISIBLE)
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
            else
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
            }
            holder.questionView.setOnClickListener {
                val faq: Faq = faqArrayList[position]
                faq.isExpanded = !faq.isExpanded
                mExpandedPosition = if (isExpandedLayout) -1 else position
                changeStateOfItemsInLayout(faqArrayList.get(holder.getLayoutPosition()),holder.getLayoutPosition())
                message = "Parent : $position, Child : 1"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()

            }

            holder.expandedQuestionView.setOnClickListener{
                if (holder.secondExpandableRelativeLayout.visibility == View.VISIBLE)
                {
                    holder.secondExpandableRelativeLayout.visibility = View.GONE
                    holder.thirdExpandableRelativeLayout.visibility = View.GONE
                    holder.secondImageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
                }
                else
                {
                    holder.secondExpandableRelativeLayout.visibility = View.VISIBLE
                    holder.secondImageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                }
                message = "Parent : $position, Child : 2"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()



            }

            holder.secondExpandedTextView.setOnClickListener{
                if (holder.thirdExpandableRelativeLayout.visibility == View.VISIBLE)
                {
                    holder.thirdExpandableRelativeLayout.visibility = View.GONE
                    holder.thirdImageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
                }
                else
                {
                    holder.thirdExpandableRelativeLayout.visibility = View.VISIBLE
                    holder.thirdImageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
                }
                message = "Parent : $position, Child : 3"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()


            }
        }
        else if (holder.itemViewType == 2)
        {
            val holder = holder as FaqViewHolder
            holder.questionView.setText(faqArrayList[position].question)
            holder.answerView.setText(faqArrayList[position].answer)
            val isExpandedLayout: Boolean = faqArrayList[position].isExpanded
            holder.expandableRelativeLayout.visibility = if (isExpandedLayout) View.VISIBLE else View.GONE
            if (isExpandedLayout)
            {
                previousExpandedPosition = position
            }

            if (holder.expandableRelativeLayout.visibility == View.VISIBLE)
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
            }
            else
            {
                holder.imageView.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
            }

            holder.questionView.setOnClickListener {
                mExpandedPosition = if (isExpanded) -1 else position
                val faq: Faq = faqArrayList[position]
                faq.isExpanded = !faq.isExpanded
                mExpandedPosition = if (isExpandedLayout) -1 else position
                changeStateOfItemsInLayout(faqArrayList.get(holder.getLayoutPosition()),holder.getLayoutPosition())
                message = "Parent : $position, Child : 1"
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()

            }
        }
        else
        {
            if (isExpanded)
            {
                previousExpandedPosition = position
            }


        }



    }

    override fun getItemCount(): Int {
        return faqArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 4 == 0)
        {
            return 3
        }

        if (position % 4 == 1)
        {
            return 4
        }

        if (position % 4 == 2)
        {
            return 2
        }
        return 0
    }


    class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionView: TextView
        val answerView: TextView
        val expandableRelativeLayout: RelativeLayout
        val imageView : ImageView

        init {
            questionView = itemView.findViewById(R.id.question_title)
            answerView = itemView.findViewById(R.id.faq_answer)
            expandableRelativeLayout = itemView.findViewById(R.id.expandable_layout)
            imageView = itemView.findViewById(R.id.faq_arrow)

        }


    }

    class FourChildFaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val questionView: TextView
        val answerView: TextView
        val expandableRelativeLayout: RelativeLayout
        val secondExpandableRelativeLayout : RelativeLayout
        val thirdExpandableRelativeLayout : RelativeLayout
        val expandedQuestionView : TextView
        val secondExpandedTextView : TextView
        val secondImageView : ImageView
        val imageView : ImageView
        val thirdImageView : ImageView

        init {
            questionView = itemView.findViewById(R.id.question_title)
            answerView = itemView.findViewById(R.id.faq_answer)
            secondExpandableRelativeLayout = itemView.findViewById(R.id.second_expandable_layout)
            thirdExpandableRelativeLayout = itemView.findViewById(R.id.third_expandable_layout)
            expandableRelativeLayout = itemView.findViewById(R.id.expandable_layout)
            expandedQuestionView = itemView.findViewById(R.id.expand_text_view)
            secondExpandedTextView = itemView.findViewById(R.id.second_expand_text_view)
            secondImageView = itemView.findViewById(R.id.expand_image_view)
            imageView = itemView.findViewById(R.id.faq_arrow)
            thirdImageView = itemView.findViewById(R.id.second_expand_image_view)

        }

    }

    class ThreeChildFaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val questionView: TextView
        val answerView: TextView
        val expandableRelativeLayout: RelativeLayout
        val secondExpandableRelativeLayout : RelativeLayout
        val expandedQuestionView : TextView
        val imageView : ImageView
        val secondImageView : ImageView


        init {
            questionView = itemView.findViewById(R.id.question_title)
            answerView = itemView.findViewById(R.id.faq_answer)
            expandableRelativeLayout = itemView.findViewById(R.id.expandable_layout)
            secondExpandableRelativeLayout = itemView.findViewById(R.id.second_expandable_layout)
            expandedQuestionView = itemView.findViewById(R.id.expand_text_view)
            imageView = itemView.findViewById(R.id.faq_arrow)
            secondImageView = itemView.findViewById(R.id.expand_image_view)

        }
    }

    class SingleChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }



}
