package QueenPlacerKt


const val NumCols = 8
const val NumRows = 8


data class QueenPos(val x: Int, val y: Int)

typealias BoardState = Array<QueenPos?>


fun main(args: Array<String>){
    val num = findSolution(0, arrayOf<QueenPos?>())
    println("${num} solution(s) found")
}


fun findSolution(y: Int, boardState: BoardState) : Int{
    var num = 0

    for (x in 0 until NumCols){
        if (isSafe(x, y, boardState)){
            val nextY = y + 1
            val nextBoardState = boardState.copyOf(boardState.size + 1)
            nextBoardState[boardState.size] = QueenPos(x, y)

            if (nextY == NumRows) {
                displaySolution(nextBoardState)
                num += 1
            }
            else
                num += findSolution(nextY, nextBoardState)
        }
    }

    return num
}


fun isSafe(x: Int, y:Int, boardState: BoardState) : Boolean {
    for (piece in boardState){
        if (piece!!.x == x) return false
        if (piece!!.y == y) return false
        if (Math.abs(piece!!.x - x) - Math.abs(piece!!.y - y) == 0) return false
    }

    return true
}


fun displaySolution(boardState: BoardState){
    var tiles = Array<Boolean>(NumCols * NumRows, {false})


    // Mark the piece locations

    for (piece in boardState){
        tiles[piece!!.x + (piece!!.y * NumCols)] = true
    }

    println("Solution:")
    for (y in 0 until NumRows){
        for (x in 0 until NumCols){
            print(if (tiles[x + (y * NumRows)]) "x" else "-")
        }
        println("")
    }

    println("")
}