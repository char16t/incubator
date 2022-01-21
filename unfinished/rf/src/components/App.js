import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import {Helmet} from "react-helmet";

import Header from './Header'
import Footer from './Footer'
import Sidebar from './Sidebar'
import Content from './Content'

class App extends Component {
  render() {
    return (
        <Router>
          <Helmet>
            <title>Hello</title>
            <link rel="shortcut icon" href="/favicon.ico" />
          </Helmet>

          <div>
            <Header />
            <div>
              <Sidebar />
              <Content />
            </div>
            <Footer />
          </div>
        </Router>
    );
  }
}

export default App;
