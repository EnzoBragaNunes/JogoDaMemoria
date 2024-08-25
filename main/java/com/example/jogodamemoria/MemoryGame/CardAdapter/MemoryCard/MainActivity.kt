package com.example.jogodamemoria.MemoryGame.CardAdapter.MemoryCard

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jogodamemoria.MemoryGame.CardAdapter.MemoryCard.MemoryGame.MemoryGame
import com.example.jogodamemoria.R
import com.seuprojeto.jogomemoriasimples.CardAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private var selectedPosition: Int? = null
    private var isProcessing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        memoryGame = MemoryGame(this)

        adapter = CardAdapter(memoryGame.getCards()) { position -> onCardClicked(position) }
        recyclerView.adapter = adapter
    }

    private fun onCardClicked(position: Int) {
        if (isProcessing) return // Ignorar cliques enquanto processando

        val secondCard = memoryGame.getCards()[position]

        if (secondCard.isMatched || secondCard.isFaceUp) {
            return // Ignorar se a carta já está combinada ou virada
        }

        // Virar a segunda carta
        memoryGame.flipCard(position)
        adapter.notifyItemChanged(position)

        if (selectedPosition == null) {
            // Primeira carta virada
            selectedPosition = position
        } else {
            // Segunda carta virada
            isProcessing = true
            val firstCard = memoryGame.getCards()[selectedPosition!!]

            // Verificar se as cores das cartas correspondem
            if (firstCard.color.constantState == secondCard.color.constantState) {
                // Cartas correspondem, mantê-las viradas e marcá-las como combinadas
                firstCard.isMatched = true
                secondCard.isMatched = true
                firstCard.isFaceUp = true
                secondCard.isFaceUp = true

                adapter.notifyItemChanged(selectedPosition!!)
                adapter.notifyItemChanged(position)

                selectedPosition = null
                isProcessing = false

                // Verificar se o jogo foi ganho
                if (memoryGame.hasWon()) {
                    Toast.makeText(this, "Parabéns! Você venceu!", Toast.LENGTH_LONG).show()
                }
            } else {
                // Cartas não correspondem, virá-las de volta após um atraso
                Handler().postDelayed({
                    firstCard.isFaceUp = false
                    secondCard.isFaceUp = false

                    adapter.notifyItemChanged(selectedPosition!!)
                    adapter.notifyItemChanged(position)

                    selectedPosition = null
                    isProcessing = false
                }, 1000)
            }
        }
    }
}
