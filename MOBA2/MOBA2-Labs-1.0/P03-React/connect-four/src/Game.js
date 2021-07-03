import React, {Component} from "react";
import Board from "./Board";

class Game extends Component {
    constructor(props) {
        super(props);

        this.state = {
            boardLen: 7,
            history: [{
                squares: Array.from({length: 7}, () => Array.from({length: 7}, () => null)),
            }],
            stepNumber: 0,
            oneIsNext: true,
            status: {text: 'Next Player: Player 1', img: <img src='./player1.svg' alt="player1"/>}
        }
    }

    getBottomMostEmptyRow = (squares, row, col) => {
        if (squares[row][col] == null) {
            if (row === this.state.boardLen - 1) {
                return row;
            } else {
                return this.getBottomMostEmptyRow(squares, row + 1, col);
            }
        } else {
            return row - 1;
        }
    };

    checkWinner = (squares) => {
        let prevColor = null;
        let count = 0;

        const chcekSquare = (r, c) => {
            if (squares[r][c] === prevColor) {
                count++;

                if (count === 4) {
                    return prevColor;
                }
            } else {
                count = 1;
            }
            prevColor = squares[r][c];
        };

        const resetState = () => {
            prevColor = null;
            count = 0;
        };

        // check right
        for (let r = this.state.boardLen - 1; r >= 0; r--) {
            for (let c = 0; c <= squares.length - 1; c++) {
                if (squares[r][c] == null) {
                    resetState();
                    continue;
                }

                const result = chcekSquare(r, c);
                if (result != null) return result;
            }
        }

        // check down
        for (let c = 0; c <= squares.length - 1; c++) {
            for (let r = this.state.boardLen - 1; r >= 0; r--) {
                if (squares[r][c] == null) {
                    resetState();
                    continue;
                }

                const result = chcekSquare(r, c);
                if (result != null) return result;
            }
        }
        return null;
    };

    handleClick = (row, col) => {
        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = {
            squares: history[this.state.stepNumber].squares.map(r => {
                return r.slice();
            })
        };
        const squares = current.squares.slice();

        let winner = this.checkWinner(squares);
        if (winner != null) {
            return;
        }

        if (squares[row][col] == null) {
            const newRow = this.getBottomMostEmptyRow(current.squares, row, col);
            squares[newRow][col] = this.state.oneIsNext ? 1 : 2;
            this.setState({
                history: history.concat([{
                    squares: squares,
                }]),
                stepNumber: history.length,
                oneIsNext: !this.state.oneIsNext,
                status: {
                    text: 'Next Player: Player ' + (!this.state.oneIsNext ? 1 : 2),
                    img: <img src={'player' + (!this.state.oneIsNext ? 1 : 2) + '.svg'} alt={'player' + (!this.state.oneIsNext ? 1 : 2)}/>
                }
            });

            winner = this.checkWinner(squares);
            if (winner != null) {
                this.setState({
                    status: {text: "Player" + winner + " has won this match!", img: <img src={'player' + winner + '.svg'} alt={'player' + winner}/>}
                });
            }
        } else {
            console.error("not possible here!");
        }
    };

    jumpTo = (step) => {
        this.setState({
            stepNumber: step,
            oneIsNext: (step % 2) === 0,
            status: {
                text: 'Next Player: Player ' + ((step % 2) === 0 ? 1 : 2),
                img: <img src={'player' + ((step % 2) === 0 ? 1 : 2) + '.svg'} alt={'player' + ((step % 2) === 0 ? 1 : 2)}/>
            }
        });
    };

    render() {
        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = {
            squares: history[this.state.stepNumber].squares.map(r => {
                return r.slice();
            })
        };

        const moves = history.map((step, move) => {
            const desc = move ?
                'Back to #' + move :
                'Reset';
            return (
                <li key={move}>
                    <button onClick={() => this.jumpTo(move)}>{desc}</button>
                </li>
            )
        });

        return (
            <div className="game">
                <h1>Connect Four</h1>
                <div className="game-board">
                    <Board
                        squares={current.squares}
                        onClick={(row, col) => this.handleClick(row, col)}/>
                </div>
                <div className="game-info">
                    <div className="status">{this.state.status.text} {this.state.status.img}</div>
                    <ol>{moves}</ol>
                </div>
            </div>
        );
    }
}

export default Game;