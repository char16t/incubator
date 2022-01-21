import React, { Component } from 'react';
import {Helmet} from "react-helmet";
import Add from '../containers/Add';

class NewsPhotos extends Component {
    render() {
        return(
                <div>
                    <Helmet>
                        <title>News Photos</title>
                        <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                    </Helmet>
                    <p>News Photos</p>
                    <Add />
                </div>
        );
    }
}

export default NewsPhotos;
