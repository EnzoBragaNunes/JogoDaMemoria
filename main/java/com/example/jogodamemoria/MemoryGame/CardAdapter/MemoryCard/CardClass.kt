// Definindo a classe Card
data class Card(
    val id: Int,
    val content: String,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)

// Método para obter uma lista de cartas
fun getCards(): List<Card> {
    // Exemplo de implementação criando um conjunto de cartas
    val cards = listOf(
        Card(1, "A"),
        Card(2, "A"),
        Card(3, "B"),
        Card(4, "B"),
        // Adicione mais cartas conforme necessário
    )
    return cards
}

