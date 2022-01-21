import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Communities extends Component {
    render() {
        return(
            <div>
                <Helmet>
                    <title>Communities</title>
                    <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                </Helmet>
                Communities
            </div>
        );
    }
}

export default Communities;
