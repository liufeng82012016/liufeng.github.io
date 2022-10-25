import Board from './Board';
import React from 'react';

class Game extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            // 初始化数组
            history: [{
                squares: Array(9).fill(null),
            }],
            xIsNext: true,
            stepNumber: 0,
        }
    }

    handleClick(i) {
        const history = this.state.history.slice(0, this.state.stepNumber + 1);
        const current = history[history.length - 1];
        const squares = current.squares.slice();
        if (calculateWinner(squares) || squares[i]) {
            // 如果游戏获胜，或者该处已被填充
            return;
        }
        squares[i] = (this.state.xIsNext ? 'X' : 'O');
        this.setState({
            history: history.concat([{
                squares: squares,
            }]),
            xIsNext: !this.state.xIsNext,
            stepNumber: history.length,
        });
    }

    // 跳转到某一步
    jumpTo(move) {
        // const history = this.state.history;
        // const current = history[move];
        // const squares = current.squares.slice();
        // let countX = 0;
        // for (let i = 0; i < squares.length; i++) {
        //     if ('X' === squares[i]) {
        //         countX++;
        //     } else if ('O' === squares[i]) {
        //         countX--;
        //     }
        // }
        this.setState({
            // history: history.concat([{
            //     squares: squares,
            // }]),
            stepNumber: move,
            xIsNext: move % 2 === 0,

        });
    }

    render() {
        const history = this.state.history;
        const current = history[this.state.stepNumber];
        const winner = calculateWinner(current);
        let status;
        if (winner) {
            status = 'Winner: ' + winner;
        } else {
            status = 'Next player:' + (this.state.xIsNext ? 'X' : 'O');
        }

        const moves = history.map((step, move) => {
            const desc = move ? 'go to move #' + move :
                'go to game start';
            return (
                <li>
                    <button onClick={() => this.jumpTo(move)}>{desc}</button>
                </li>
            );
        });

        return (
            <div className="game">
                <div className={"game-board"}>
                    <Board squares={current.squares}
                           onClick={(i) => this.handleClick(i)}/>
                </div>
                <div className={"game-info"}>
                    <div>
                        {status}
                    </div>
                    <ol>{moves}</ol>
                </div>

            </div>
        )
    }
}

function calculateWinner(squares) {
    const lines = [
        [0, 1, 2],
        [3, 4, 5],
        [6, 7, 8],
        [0, 3, 6],
        [1, 4, 7],
        [2, 5, 8],
        [0, 4, 8],
        [2, 4, 6]
    ];
    for (let i = 0; i < lines.length; i++) {
        const [a, b, c] = lines[i];
        if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
            return squares[a];
        }
    }
    return null;
}

export default Game;