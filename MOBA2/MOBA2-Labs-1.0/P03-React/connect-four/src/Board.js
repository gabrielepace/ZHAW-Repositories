import React, {Component} from 'react';

function Square(props) {
    return (
        <button className="square"
                onClick={() => props.onClick()}>
            {props.value != null ? (
                <img src={'player' + props.value + '.svg'} alt={'player' + (props.value ? 1 : 2)}/>
            ) : ''}
        </button>
    );
}

class Board extends Component {
    renderSquare = (row, col) => {
        return (
            <Square value={this.props.squares[row][col]}
                    onClick={() => this.props.onClick(row, col)}/>
        );
    }

    render() {
        return (
            <div className="board">
                <div className="board-row">
                    {this.renderSquare(0, 0)}
                    {this.renderSquare(0, 1)}
                    {this.renderSquare(0, 2)}
                    {this.renderSquare(0, 3)}
                    {this.renderSquare(0, 4)}
                    {this.renderSquare(0, 5)}
                    {this.renderSquare(0, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(1, 0)}
                    {this.renderSquare(1, 1)}
                    {this.renderSquare(1, 2)}
                    {this.renderSquare(1, 3)}
                    {this.renderSquare(1, 4)}
                    {this.renderSquare(1, 5)}
                    {this.renderSquare(1, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(2, 0)}
                    {this.renderSquare(2, 1)}
                    {this.renderSquare(2, 2)}
                    {this.renderSquare(2, 3)}
                    {this.renderSquare(2, 4)}
                    {this.renderSquare(2, 5)}
                    {this.renderSquare(2, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(3, 0)}
                    {this.renderSquare(3, 1)}
                    {this.renderSquare(3, 2)}
                    {this.renderSquare(3, 3)}
                    {this.renderSquare(3, 4)}
                    {this.renderSquare(3, 5)}
                    {this.renderSquare(3, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(4, 0)}
                    {this.renderSquare(4, 1)}
                    {this.renderSquare(4, 2)}
                    {this.renderSquare(4, 3)}
                    {this.renderSquare(4, 4)}
                    {this.renderSquare(4, 5)}
                    {this.renderSquare(4, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(5, 0)}
                    {this.renderSquare(5, 1)}
                    {this.renderSquare(5, 2)}
                    {this.renderSquare(5, 3)}
                    {this.renderSquare(5, 4)}
                    {this.renderSquare(5, 5)}
                    {this.renderSquare(5, 6)}
                </div>
                <div className="board-row">
                    {this.renderSquare(6, 0)}
                    {this.renderSquare(6, 1)}
                    {this.renderSquare(6, 2)}
                    {this.renderSquare(6, 3)}
                    {this.renderSquare(6, 4)}
                    {this.renderSquare(6, 5)}
                    {this.renderSquare(6, 6)}
                </div>
            </div>
        );
    }
}

export default Board;