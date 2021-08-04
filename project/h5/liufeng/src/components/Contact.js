import React, {Component} from 'react'
import '../css/Contact.css'

// 联系人
class Contact extends Component {
    constructor(props) {
        super(props);
        // 这里不要调用setState方法
        this.state = {
            color: 'white'
        }
    }

    render() {
        return (
            <div className='container'>
                <div className={'icon'}>
                    <img src={this.props.icon} alt={''}/>
                </div>
                <div className='right-box'>
                    <div className='right-top-box'>
                        <div className='title'>
                            hello,I am Delay
                        </div>
                        <div className='time'>
                            23:55
                        </div>
                    </div>
                    <div className='content'>
                        How are you ? where are you from ?
                    </div>
                </div>
            </div>
        )
    };
}

export default Contact;