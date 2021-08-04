import React, {Component} from 'react'
import '../css/ChatWindow.css'

// 聊天窗口
class ChatWindow extends Component {
    constructor(props) {
        super(props);
        // 这里不要调用setState方法
        this.state = {
            message: '',
            msgList: [
                {}
            ]
        }
    }

    render() {
        return (
            <div className='container'>
                <h2>{this.props.friendName}</h2>
                <div id={'messageList'}></div>
                <div>
                    <span>图片</span>
                    <span>文件</span>
                </div>
                <textarea id={'messageInput'}></textarea>
                <button>发送</button>
            </div>
        )
    };
}

export default ChatWindow;