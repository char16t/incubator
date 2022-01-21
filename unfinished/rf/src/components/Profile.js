import React, { Component } from 'react';
import {Helmet} from "react-helmet";

class Profile extends Component {
    render() {
        return(
                <div>
                    <Helmet>
                        <title>Profile</title>
                        <link rel="icon" type="image/png" href="favicon.ico" sizes="16x16" />
                    </Helmet>
                    Profile
                </div>
        );
    }
}

export default Profile;
