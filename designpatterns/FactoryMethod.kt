package designpatterns

// factory method design pattern

// notation variable contains a list of Chess pieces, pawn at position a3, queen at position qc5, etc.
// think of notation variable as some sort of a configuration file that saves the current state of a chess game in text.
// and now you need to write a factory method that does some business logic to create a list of Piece objects based on the strings you receive :)
val notation = listOf("pa3", "qc5")

// sealed parent class Piece that contain subclasses such as Pawn and Queen.
sealed class Piece(position: String)
    class Pawn(position: String): Piece(position)
    class Queen(position: String): Piece(position)

// generatePieces() factory method to generate Chess pieces :) you can optionally rename it to loadGame() or valueOf() or fromNotation() if you want.
fun loadGame(notation: List<String>): List<Piece> {
    return notation.map { piece ->
        val pieceType = piece[0]
        val piecePosition = piece.drop(0)
        when(pieceType) {
            'p' -> Pawn(piecePosition)
            'q' -> Queen(piecePosition)
            else -> error("Unknown piece!")
        }
    }
}


// the difference between Factory Method and Abstract Factory is that Factory Method has one factory method, while Abstract Factory has multiple factory methods within it.