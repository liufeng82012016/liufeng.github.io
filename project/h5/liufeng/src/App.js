import './App.css';
// import Contact from './components/Contact'
import Message from './components/Message'
import React from 'react'
import triger from './img/triger.jpg'
import flower from './img/flower.jpg'

function App() {
    return (
        <div className="App">
            {/*<Contact own={true} icon={flower}/>*/}
            {/*<Contact own={false} icon={triger}/>*/}

            <Message own={true} icon={flower}/>
            <Message own={false} icon={triger}/>
        </div>
    );
}

export default App;
