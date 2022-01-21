import React, { Component } from 'react';
import { BrowserRouter as Router, Route, NavLink } from "react-router-dom";

class Sidebar extends Component {
    render() {
        return(
            <div>
                <div><NavLink activeClassName="selected" to="/id0">Profile</NavLink></div>
                <div><NavLink activeClassName="selected" to="/feed">News</NavLink></div>
                <div><NavLink activeClassName="selected" to="/im">Messages</NavLink></div>
                <div><NavLink activeClassName="selected" to="/friends">Friends</NavLink></div>
                <div><NavLink activeClassName="selected" to="/groups">Communities</NavLink></div>
          </div>
        );
    }
}

export default Sidebar;
