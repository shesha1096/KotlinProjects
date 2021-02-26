package com.shesha.kotlinprojects.daggerexampleapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shesha.kotlinprojects.daggerexampleapp.R
import com.shesha.kotlinprojects.daggerexampleapp.network.PageResponse
import java.util.*

class ArticleRecyclerAdapter : RecyclerView.Adapter<ArticleRecyclerAdapter.ArticleViewHolder> {
    private var context: Context? = null
    private var responseList: ArrayList<PageResponse>? = null

    constructor() {}
    constructor(
        context: Context?,
        responseList: ArrayList<PageResponse>
    ) {
        this.context = context
        this.responseList = responseList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_card_view, parent, false)
        return ArticleViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        var Name: String =
            responseList!![position].link?.replace("#038;", "'") ?: return
        Name = Name.replace("#8217;", "&")
        Name = Name.replace("#8216;", " ")
        Name = Name.replace("#8211;", "&")
        Name = Name.replace("&&;", " &")
        Name = Name.replace("&'", "&")
        Name = Name.replace("/", "")
        Name = Name.replace("&&", "'")
        Log.d("NAME", Name)
        holder.articleTitle.text = Name
        holder.articleDescription.setText(responseList!![position].date)
    }

    override fun getItemCount(): Int {
        return responseList!!.size
    }

    inner class ArticleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val articleTitle: TextView
        val articleDescription: TextView
        private val cardView: CardView

        init {
            articleTitle = itemView.findViewById(R.id.article_cardview_title)
            articleDescription = itemView.findViewById(R.id.article_cardview_description)
            cardView = itemView.findViewById(R.id.article_card_view)
        }
    }
}