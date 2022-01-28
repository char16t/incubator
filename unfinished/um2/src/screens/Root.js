import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import Home from '../components/Home'

class Root extends Component {
  render() {
    return (
      <Router>
        <Route path="/" exact component={Home} />
      </Router>
    );
  }
}

export default Root;
