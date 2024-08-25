package com.seuprojeto.jogomemoriasimples

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.jogodamemoria.R

class CardAdapter(
    private val cards: List<MemoryCard>,
    private val cardClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)

        init {
            view.setOnClickListener {
                cardClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        if (card.isFaceUp) {
            holder.imageView.setImageDrawable(card.color)
        } else {
            holder.imageView.setImageResource(android.R.color.darker_gray)
        }
    }

    override fun getItemCount() = cards.size
}
