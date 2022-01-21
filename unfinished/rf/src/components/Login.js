import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Login extends Component {
    render() {
        return(
            <div>
                <Helmet>
                    <title>Login</title>
                    <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                </Helmet>
                Login
            </div>
        );
    }
}

export default Login;
