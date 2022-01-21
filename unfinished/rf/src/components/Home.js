import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Home extends Component {
    render() {
        return(
            <div>
                <Helmet>
                    <title>Home</title>
                    <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                </Helmet>
                Home
            </div>
        );
    }
}

export default Home;
