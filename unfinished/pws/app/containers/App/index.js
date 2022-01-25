/**
 *
 * App
 *
 * This component is the skeleton around the actual pages, and should only
 * contain code that should be seen on all pages. (e.g. navigation bar)
 */

import React from 'react';
import { Helmet } from 'react-helmet';
import styled from 'styled-components';
import { Switch, Route } from 'react-router-dom';
import { intlShape, injectIntl } from 'react-intl';

import Header from 'containers/Header';
import HomePage from 'containers/HomePage/Loadable';
import AboutPage from 'containers/AboutPage/Loadable';
import ContactsPage from 'containers/ContactsPage/Loadable';
import LoginPage from 'containers/LoginPage/Loadable';
import ProfilePage from 'containers/ProfilePage/Loadable'
import RegistrationPage from 'containers/RegistrationPage/Loadable';
import NotFoundPage from 'containers/NotFoundPage/Loadable';
import messages from './messages';

import GlobalStyle from '../../global-styles';

const AppWrapper = styled.div`
  max-width: 1335px;
  margin: 0 auto;
  display: flex;
  min-height: 100%;
  padding: 0 16px;
  flex-direction: column;
`;

// eslint-disable-next-line react/prefer-stateless-function
export class App extends React.Component {
  render() {
    const { formatMessage } = this.props.intl;
    return (
      <AppWrapper>
        <Helmet
          titleTemplate={formatMessage({ ...messages.titleTemplate })}
          defaultTitle={formatMessage({ ...messages.defaultTitle })}
        />
        <Header />
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route path="/about" component={AboutPage} />
          <Route path="/contacts" component={ContactsPage} />
          <Route path="/login" component={LoginPage} />
          <Route path="/join" component={RegistrationPage} />
          <Route path="/profile" component={ProfilePage} />
          <Route path="" component={NotFoundPage} />
        </Switch>
        <GlobalStyle />
      </AppWrapper>
    );
  }
}

App.propTypes = {
  intl: intlShape.isRequired,
};

export default injectIntl(App);
