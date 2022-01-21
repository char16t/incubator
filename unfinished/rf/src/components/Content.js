import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { Redirect } from 'react-router'

import Home from './Home'
import Profile from './Profile'
import News from './News'
import Messages from './Messages'
import Friends from './Friends'
import Communities from './Communities'
import Login from './Login'

class Content extends Component {
    render() {
        return(
            <div>
                <Route path="/" exact component={Home} />
                <Route path="/id*" component={Profile} />
                <Route path="/feed" component={News} />
                <Route path="/im" component={Messages} />
                <Route path="/friends" component={Friends} />
                <Route path="/groups" component={Communities} />
                <Route path="/login" component={Login} />
            </div>
        );
    }
}

export default Content;
