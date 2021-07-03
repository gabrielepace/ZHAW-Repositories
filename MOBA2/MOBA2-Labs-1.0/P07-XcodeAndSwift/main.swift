import Foundation

class GameIteration {
    var  board: [[Int]]
    init(_ size: Int = 4){
        print("calculating cycles for array of dimension: " + String(size))
        board = Array(repeating: Array(repeating: 0, count: size), count: size)
        for idx in 0...3 {
            for jdx in 0...3 {
                board[idx][jdx] = random() % 2 // In Xcode works: board[idx][jdx] = Int(arc4random() % 2)
            }
        }
    }
    
    func printBoard() {
        for idx in 0...board.count - 1 {
            var s = ""
            for jdx in 0...board[idx].count - 1 {
                s += String(board[idx][jdx])
            }
            print(s)
        }
    }
        
    func calculateNextCircle() {
        let extendedBoard = extendBoard()
        for idx in 1...extendedBoard.count-2 {
            for jdx in 1...extendedBoard.count-2 {
                let numberOfLivingCells = 8 - countDeathcells(extendedBoard, idx, jdx)
                if(checkDeathRules(extendedBoard[idx][jdx], numberOfLivingCells)) {
                    board[idx - 1][jdx - 1] = 1
                } else if(checkLivingRules(extendedBoard[idx][jdx], numberOfLivingCells)) {
                    board[idx - 1][jdx - 1] = 0
                }
            }
        }
    }
    
    private func extendBoard() -> [[Int]] {
        let extendedCount = board.count + 2
        var extendedBoard = Array(repeating: Array(repeating: 0, count: extendedCount), count: extendedCount)
        for idx in 1...extendedBoard.count - 2 {
            for jdx in 1...extendedBoard.count - 2 {
                extendedBoard[idx][jdx] = board[idx - 1][jdx - 1]
            }
        }

        return extendedBoard
    }
    
    private func countDeathcells(_ calcBoard: [[Int]], _ colIndex: Int, _ rowIndex: Int) -> Int {
        var numberOfDeathcells = 0
        for idx in -1...1 {
            if(calcBoard[colIndex - 1][rowIndex + idx] == 0) {
                numberOfDeathcells+=1
            }
            if(calcBoard[colIndex + 1][rowIndex + idx] == 0) {
                numberOfDeathcells+=1
            }
        }

        if(calcBoard[colIndex][rowIndex - 1] == 0) {
            numberOfDeathcells+=1
        }
        if(calcBoard[colIndex][rowIndex + 1] == 0) {
            numberOfDeathcells+=1
        }
        
        return numberOfDeathcells
    }
    
    private func checkDeathRules(_ value: Int, _ numberOfLivingCells: Int) -> Bool {
        return value == 0 && numberOfLivingCells == 3
    }

    private func checkLivingRules(_ value: Int, _ numberOfLivingCells: Int) -> Bool {
        return value == 1 &&  (numberOfLivingCells < 2 || numberOfLivingCells > 3)
    }
}

let gameIteration = GameIteration(5)
print("initial iteration distribution ----")
gameIteration.printBoard()
for _ in 1..<10 {
    gameIteration.calculateNextCircle()
    print("next iteration distribution ----")
    gameIteration.printBoard()
}
