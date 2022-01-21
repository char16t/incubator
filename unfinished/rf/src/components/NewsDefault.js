import React, { Component } from 'react';
import { Redirect } from 'react-router'

class NewsDefault extends Component {
    render() {
        return(
                <Redirect to="/feed/all" />
        );
    }
}

export default NewsDefault;
