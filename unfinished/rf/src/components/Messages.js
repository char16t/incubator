import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Messages extends Component {
    render() {
        return(
            <div>
                <Helmet>
                    <title>Messages</title>
                    <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                </Helmet>
                Messages
            </div>
        );
    }
}

export default Messages;
