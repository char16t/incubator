import React from "react";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Header from './components/Header'
import Home from './components/Home'
import User from './components/User'

function DemoApp() {
  return (
    <Router>
      <div>
        <Header />

        <Route exact path="/" component={Home} />
        <Route path={`/user/:id`} component={User} />
      </div>
    </Router>
  );
}

export default DemoApp;
