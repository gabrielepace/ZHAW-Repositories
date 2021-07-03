import React from 'react';
import ReactDOM from 'react-dom';
import './App.css';
var classNames = require('classnames');

function Square(props) {
  let squareClass = classNames({
    "piece": true,
    "color-a": props.value === 'R',
    "color-b": props.value === 'B'
  })
  return (
      <div className="field" onClick={props.onClick}>
        <div className={squareClass}>
        </div>
      </div>
  );
}


class Board extends React.Component {

  render() {
    const boardSquares =  this.props.squares.map((value, index) =>
        <Square key={index} value={value} onClick={() => this.props.onClick(index%7)}/>
    )
    return (
        <div className="board">
          {
            boardSquares
          }
        </div>)
  }
}

class Game extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      squares: Array(42).fill(''),
      redIsNext: true
    }
  }

  handleClick(i) {
    let freeFieldFound = false;
    let fieldIndex = 35;
    let squares = this.state.squares;
    let redIsNext = this.state.redIsNext;

    while(!freeFieldFound && (fieldIndex >= 0)) {
      if(squares[i + fieldIndex] === '') {
        squares[i + fieldIndex] = redIsNext ? "R" : "B";
        redIsNext = !redIsNext;
        freeFieldFound = true;
      } else {
        fieldIndex -= 7;
      }
    }

    this.setState({
      squares: squares,
      redIsNext: redIsNext
    })
  }

  render() {
    return (
        <Board
            squares={this.state.squares}
            onClick={i => this.handleClick(i)}
        />
    );
  };
}

ReactDOM.render(<Game />, document.getElementById("root"));