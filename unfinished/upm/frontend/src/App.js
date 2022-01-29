import React, { Component } from 'react';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src="/upsidemind.svg" className="App-logo" alt="logo" />
          <p className="App-welcome-text">
            Свяжитесь с нами, если у вас есть вопросы или предложения
          </p>
          <a
            className="App-link"
            href="mailto:contact@upsidemind.ru"
            target="_blank"
            rel="noopener noreferrer"
          >
            contact@upsidemind.ru
          </a>
        </header>
      </div>
    );
  }
}

export default App;
