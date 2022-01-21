import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Friends extends Component {
    render() {
        return(
            <div>
                <Helmet>
                    <title>Friends</title>
                    <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                </Helmet>
                Friends
            </div>
        );
    }
}

export default Friends;
