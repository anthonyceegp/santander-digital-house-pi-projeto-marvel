package com.example.marvelworld.creatorlist.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelworld.R
import com.example.marvelworld.api.models.Image
import com.example.marvelworld.creatorlist.models.Creator
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class CreatorListAdapter(
    private val creatorList: List<Creator>,
    private val onCreatorClickListener: OnCreatorClickListener
) : RecyclerView.Adapter<CreatorListAdapter.CreatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatorViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.creator_list_item, parent, false)

        return CreatorViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: CreatorViewHolder, position: Int) {
        val comic = creatorList[position]
        holder.bind(comic, onCreatorClickListener)
    }

    override fun getItemCount() = creatorList.size

    class CreatorViewHolder(
        view: View,
        private val context: Context
    ) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.img_creator_list_item)
        private val favoriteButton: ImageButton = view.findViewById(R.id.creator_list_favorite_button)
        private val title: TextView = view.findViewById(R.id.title_creator_list_item)
        private val cardView: MaterialCardView = view.findViewById(R.id.card)

        @SuppressLint("SetTextI18n")
        fun bind(creator: Creator, onCreatorClickListener: OnCreatorClickListener) {
            val path = creator.thumbnail.getImagePath(Image.PORTRAIT_UNCANNY)
            Picasso.get().load(path).into(image)
            title.text = creator.fullName

            if (creator.isFavorite) {
                favoriteButton.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_favorite_button_set
                )

                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorPrimary
                    )
                )
            } else {
                favoriteButton.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_favorite_button_unset
                )

                cardView.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.dark_grey
                    )
                )
            }

            favoriteButton.setOnClickListener {
                onCreatorClickListener.onCreatorFavoriteClick(adapterPosition)
            }

            val gestureDetector =
                GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                        onCreatorClickListener.onCreatorClick(adapterPosition)
                        return super.onSingleTapConfirmed(e)
                    }

                    override fun onDoubleTap(e: MotionEvent?): Boolean {
                        onCreatorClickListener.onCreatorFavoriteClick(adapterPosition)
                        return super.onDoubleTap(e)
                    }
                })

            itemView.setOnTouchListener { v, event ->
                gestureDetector.onTouchEvent(event)
                v.performClick()
                true
            }
        }
    }
}