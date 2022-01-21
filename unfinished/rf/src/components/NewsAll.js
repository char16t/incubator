import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class NewsAll extends Component {
    render() {
        return(
                <div>
                    <Helmet>
                        <title>All News</title>
                        <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                    </Helmet>
                    All News
                </div>
        );
    }
}

export default NewsAll;
