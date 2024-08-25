package com.example.jogodamemoria.MemoryGame.CardAdapter.MemoryCard.MemoryGame

import android.content.Context
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat
import com.seuprojeto.jogomemoriasimples.MemoryCard
import com.example.jogodamemoria.R

class MemoryGame(context: Context) {

    private val cards: List<MemoryCard>

    init {
        // Adicionando mais cores e cartas (cada cor tem duas cartas para fazer par)
        val colors = listOf(
            ContextCompat.getColor(context, android.R.color.holo_red_light),
            ContextCompat.getColor(context, android.R.color.holo_blue_light),
            ContextCompat.getColor(context, android.R.color.holo_green_light),
            ContextCompat.getColor(context, android.R.color.holo_orange_light),
            ContextCompat.getColor(context, android.R.color.holo_purple),
            ContextCompat.getColor(context, android.R.color.holo_red_dark),
            ContextCompat.getColor(context, android.R.color.holo_blue_dark),
            ContextCompat.getColor(context, android.R.color.holo_green_dark)
        )

        val memoryCards = mutableListOf<MemoryCard>()
        colors.forEachIndexed { index, color ->
            memoryCards.add(MemoryCard(index * 2, ColorDrawable(color)))
            memoryCards.add(MemoryCard(index * 2 + 1, ColorDrawable(color)))
        }

        cards = memoryCards.shuffled() // Embaralha as cartas
    }

    fun getCards(): List<MemoryCard> {
        return cards
    }

    fun flipCard(position: Int): Boolean {
        val card = cards[position]
        if (card.isFaceUp || card.isMatched) {
            return false // Não pode virar carta que já está virada ou combinada
        }
        card.isFaceUp = true
        return true
    }

    fun hasWon(): Boolean {
        return cards.all { it.isMatched }
    }
}
