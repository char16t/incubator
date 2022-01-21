import React, { Component } from 'react';
import { BrowserRouter as Router, Route, NavLink } from "react-router-dom";
import { Redirect } from 'react-router'
import {Helmet} from "react-helmet";

import styles from './News.module.css'

import NewsAll from './NewsAll'
import NewsPhotos from './NewsPhotos'
import NewsDefault from './NewsDefault'

class News extends Component {
    render() {
        return(
                <div className="news">
                    <Helmet>
                        <title>News</title>
                        <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                    </Helmet>
                    <div>
                        <div><NavLink activeClassName={styles.selected} to="/feed/all">All News</NavLink></div>
                        <div><NavLink activeClassName={styles.selected} to="/feed/photos">News Photos</NavLink></div>  
                    </div>
                    <Route path="/feed" exact component={NewsDefault} />
                    <Route path="/feed/all" component={NewsAll} />
                    <Route path="/feed/photos" component={NewsPhotos} />
                </div>
        );
    }
}

export default News;
