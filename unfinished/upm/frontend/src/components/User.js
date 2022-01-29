import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

function User({ match }) {
    return(
        <h1>User { match.params.id }</h1>
    )
}

export default User;