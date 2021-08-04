import React, {Component} from 'react'
import '../css/Message.css'

// 消息内容
class Message extends Component {
    constructor(props) {
        super(props);
        // 这里不要调用setState方法
        this.state = {
            color: 'white'
        }
    }

    render() {
        return (
            <div className={'container'}>
                <div className={this.props.own ? 'icon-right' : 'icon-left'}>
                    <img src={this.props.icon} alt={''}/>
                </div>
                <div className={this.props.own ? 'content-right' : 'content-left'}>
                    How are you ? where are you from ? I am Rein,from Italy.Random .lefths supersc kasfk.

                    cefefce cddw
                </div>
            </div>
        )
    };
}

export default Message;