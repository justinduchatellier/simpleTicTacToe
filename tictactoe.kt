package tictactoe

import kotlin.math.absoluteValue

fun main() {
    val size = 9
    val stringChanged = MutableList(size){' '}
    //println("line 8")
    printBoard(stringChanged, -1, ' ')
    getMove(stringChanged)
}

fun getMove(stringInput: MutableList<Char>) {
    var turn = 0
    val charX = 'X'
    val charO = 'O'
    var letter = ' '
    while (true) {
        if (turn == 9) return
        val move = readln()
        val pattern1 = """\d{1} \d{1}""".toRegex()
        val pattern2 = "^[1-3] [1-3]".toRegex()
        if (!pattern1.matches(move)) {
            println("You should enter numbers!")
            continue
        }
        else if (!pattern2.matches(move)) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        val spotNumber = changeBoard(stringInput, move)
        println("spotNumber: $spotNumber")
        if (stringInput[spotNumber] != ' ') {
            println("This cell is occupied! Choose another one!")
            continue
        }
        else {
            println("turn: $turn")
            if (turn %2 == 0) letter = charX
            else if (turn %2 != 0) letter = charO
            stringInput[spotNumber] = letter
            printBoard(stringInput, spotNumber, letter)
            turn++
        }
        val winner = checkWinner(stringInput)
        //println(stringInput)
        if (winner) return
    }
}

fun changeBoard(stringInput: MutableList<Char>, move: String): Int {
    val spot1 = move.substring(0, 1)
    //println("spot1 is: $spot1")
    val spot2 = move.substring(2)
    val number = spot1 + spot2
    //println("number is: $number")
    var spotNumber = 0
    when (number) {
        "11" -> spotNumber = 0
        "12" -> spotNumber = 1
        "13" -> spotNumber = 2
        "21" -> spotNumber = 3
        "22" -> spotNumber = 4
        "23" -> spotNumber = 5
        "31" -> spotNumber = 6
        "32" -> spotNumber = 7
        "33" -> spotNumber = 8
    }

    return spotNumber
}

fun checkWinner(stringInput: MutableList<Char>): Boolean {
    var message = ""
    var letter = ' '
    var match = false

    if (stringInput[0] == stringInput[1] && stringInput[1] == stringInput[2] &&
            stringInput[0] != ' ') {
            letter = stringInput[0].uppercaseChar()
            match = true
    }
    if (stringInput[0] == stringInput[3] && stringInput[3] == stringInput[6] &&
            stringInput[0] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[0].uppercaseChar()
            match = true
        }
    }
    if (stringInput[0] == stringInput[4] && stringInput[4] == stringInput[8] &&
            stringInput[0] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[0].uppercaseChar()
            match = true
        }
    }
    if (stringInput[1] == stringInput[4] && stringInput[4] == stringInput[7] &&
            stringInput[1] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[1].uppercaseChar()
            match = true
        }
    }
    if (stringInput[3] == stringInput[4] && stringInput[4] == stringInput[5] &&
            stringInput[3] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[3].uppercaseChar()
            match = true
        }
    }
    if (stringInput[2] == stringInput[4] && stringInput[4] == stringInput[6] &&
            stringInput[2] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[2].uppercaseChar()
            match = true
        }
    }
    if (stringInput[2] == stringInput[5] && stringInput[5] == stringInput[8] &&
            stringInput[2] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[2].uppercaseChar()
            match = true
        }
    }
    if (stringInput[6] == stringInput[7] && stringInput[7] == stringInput[8] &&
            stringInput[6] != ' ') {
        if (match) {
            message = "Impossible"
            //return message
        } else {
            letter = stringInput[6].uppercaseChar()
        }
    }

    if ((!stringInput.contains(' ') &&
        !stringInput.contains('_') &&
        ((stringInput.count{it == 'X'} - stringInput.count{it == 'O'}).absoluteValue  != 1) &&
        ((stringInput.count{it == 'X'} - stringInput.count{it == 'O'}).absoluteValue != 0)  &&
        !match) || ((stringInput.count{it == 'X'} - stringInput.count{it == 'O'}).absoluteValue  != 1) &&
        ((stringInput.count{it == 'X'} - stringInput.count{it == 'O'}).absoluteValue  != 0)) {
        message = "Impossible"
        println("line 159")
        println("$stringInput")
    }
    else if ((letter == ' ' || letter == '_')
        && (stringInput.contains('_') || stringInput.contains(' ')))
        message = "Game not finished"
    else if ((letter == ' ' || letter == '_') &&
        (!stringInput.contains('_') || stringInput.contains(' ')))
        message = "Draw"
    else if (letter == 'X' || letter == 'O') {
        message = if (letter == 'X') "X wins" else "O wins"
        match = true
    }

    println(message)
    return match
}

fun printBoard(stringChanged: MutableList<Char>, number: Int, letter: Char) {
    //println("line 169")
    //val stringChanged: MutableList<Char> = stringInput.toMutableList()
    if (number != -1){
        stringChanged[number] = letter
        //println("stringChanged[$number]: ${stringChanged[number]}")
    }
    //for (i in 0 until stringChanged.size) {
    //    if (stringChanged[i] == '_') stringChanged[i] = ' '
    //}
    for (i in stringChanged.indices) {
        if (i == 0) println("-".repeat(9))
        when (i) {
            0 -> print("| ${stringChanged[i]}")
            1, 2 -> print(" ${stringChanged[i]}")
            3 -> print(" |\n| ${stringChanged[i]}")
            4, 5 -> print(" ${stringChanged[i]}")
            6 -> print(" |\n| ${stringChanged[i]}")
            7, 8 -> print(" ${stringChanged[i]}")
        }
        if (i == 8) println(" |\n" + "-".repeat(9))
    }
}
