import Square from './Square';
import React from 'react';

class Board extends React.Component {
    // 注释代码，将状态交给Game维护
    // constructor(props) {
    //     super(props);
    //     this.state = {
    //         squares: Array(9).fill(null),
    //         xIsNext: true,
    //     }
    // }

    renderSquare(i) {
        // 将状态交给Game维护 this.state.squares[i]==>this.props.squares[i]
        return <Square value={this.props.squares[i]}
            // 给Square被点击的时候，增加对应处理
            // 将状态交给Game维护
            //            onClick={() => this.handleClick(i)}
                       onClick={() => this.props.onClick(i)}
        />
    }

    // 注释代码，将状态交给Game维护
    // handleClick(i) {
    //     // 创建了副本，而不是修改现有的数组，实现不可变
    //     const squares = this.state.squares.slice();
    //     if (calculateWinner(squares) || squares[i]) {
    //         // 如果游戏结束，或者已经被填充
    //         return;
    //     }
    //     squares[i] = this.state.xIsNext ? 'X' : 'O';
    //     this.setState({squares: squares, xIsNext: !this.state.xIsNext})
    // }


    render() {
        // 注释代码，将状态交给Game维护
        // const winner = calculateWinner(this.state.squares);
        // let status;
        // if (winner) {
        //     status = 'Winner:' + winner;
        // } else {
        //     status = 'Next Player: ' + (this.state.xIsNext ? 'X' : 'O');
        // }
        return (
            <div>
                {/*注释代码，将状态交给Game维护*/}
                {/*<div className="status">{status}</div>*/}
                <div className='board-row'>
                    {this.renderSquare(0)}
                    {this.renderSquare(1)}
                    {this.renderSquare(2)}
                </div>
                <div className='board-row'>
                    {this.renderSquare(3)}
                    {this.renderSquare(4)}
                    {this.renderSquare(5)}
                </div>
                <div className='board-row'>
                    {this.renderSquare(6)}
                    {this.renderSquare(7)}
                    {this.renderSquare(8)}
                </div>
            </div>
        )
    }

}

// 计算赢家
// 注释代码，由Game维护
// function calculateWinner(squares) {
//     const lines = [
//         [0, 1, 2],
//         [3, 4, 5],
//         [6, 7, 8],
//         [0, 3, 6],
//         [1, 4, 7],
//         [2, 5, 8],
//         [0, 4, 8],
//         [2, 4, 6]
//     ];
//     for (let i = 0; i < lines.length; i++) {
//         const [a, b, c] = lines[i];
//         if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
//             return squares[a];
//         }
//     }
//     return null;
// }

export default Board;