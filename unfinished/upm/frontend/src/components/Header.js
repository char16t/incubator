import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

class Header extends Component {
    render() {
        return(
            <ul>
                <li>
                <Link to="/">Home</Link>
                </li>
                <li>
                <Link to="/user/1">About</Link>
                </li>
          </ul>
        )
    }
}

export default Header;