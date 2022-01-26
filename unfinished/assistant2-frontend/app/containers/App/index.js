/**
 *
 * App.js
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 *
 */

import React from 'react';
import { Switch, Route } from 'react-router-dom';

import HomePage from 'containers/HomePage/Loadable';
import LoginPage from 'containers/LoginPage/Loadable';
import JoinPage from 'containers/JoinPage/Loadable';
import PasswordRestorePage from 'containers/PasswordRestorePage/Loadable';
import PasswordResetPage from 'containers/PasswordResetPage/Loadable';
import ProfilePage from 'containers/ProfilePage/Loadable';
import MoneyPage from 'containers/MoneyPage/Loadable';
import NotFoundPage from 'containers/NotFoundPage/Loadable';

import GlobalStyle from '../../global-styles';

export default function App() {
  return (
    <div>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route path="/profile" component={ProfilePage} />
        <Route path="/money" component={MoneyPage} />
        <Route path="/login" component={LoginPage} />
        <Route path="/join" component={JoinPage} />
        <Route path="/restore" component={PasswordRestorePage} />
        <Route path="/password-reset" component={PasswordResetPage} />
        <Route component={NotFoundPage} />
      </Switch>
      <GlobalStyle />
    </div>
  );
}
