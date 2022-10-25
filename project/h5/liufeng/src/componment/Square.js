import React from 'react';
import '../css/Square.css'

class Square extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: null,
        }
    }

    render() {
        return (
            <button
                className="square"
                // 让react添加对点击事件的监听，React 会调用 Square 组件的 render() 方法中的 onClick 事件处理函数
                // 事件处理函数触发了传入其中的 this.props.onClick() 方法。这个方法是由 Board 传递给 Square 的
                onClick={() =>
                       this.props.onClick()}
                >
                {this.props.value}
            </button>
        )
    }
}

export default Square;

// 函数式组件
// function Square(props){
//     return (
//       <button className={"square"} onClick={props.onClick}>
//           ${props.value}
//       </button>
//     );
// }