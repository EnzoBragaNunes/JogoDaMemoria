package com.seuprojeto.jogomemoriasimples

import android.graphics.drawable.ColorDrawable

data class MemoryCard(
    val id: Int,
    val color: ColorDrawable,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)
