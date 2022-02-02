import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import './App.css'
import axios from 'axios'

const plan = () => {
  return axios.post('http://localhost:8080/plan').then(response => console.log(response));
}

class App extends Component {  
  render() {
    return (
      <div className="container container-table">
        <div className="row vertical-center-row">
          <div className="text-center">
            <div className="input-group">
            <input type="text" className="form-control col-9" placeholder="Что нужно сделать?" autoFocus={true} />
            <input type="text" className="form-control col-3" placeholder="Когда?" />
          </div>
			<br />
			<button type="button" className="btn btn-outline-primary" onClick={plan}>Запланировать</button>
          </div>
        </div>
      </div>
    );
  }
}

export default App;
