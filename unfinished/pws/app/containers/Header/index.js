import React, { memo } from 'react';
import styled from 'styled-components';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';
import PropTypes from 'prop-types';
import { FormattedMessage } from 'react-intl';
import LocaleToggle from 'containers/LocaleToggle';
import { NavLink } from 'react-router-dom';
import * as selectors from 'containers/App/selectors';
import * as actions from 'containers/App/actions';
import messages from './messages';

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 2em 0;
`;

const Section = styled.span`
  padding: 0 15px;
`;

// eslint-disable-next-line react/prefer-stateless-function
export class Header extends React.Component {
  render() {
    const { authentification, onLogoutUser } = this.props;
    const LoginMenu = () => {
      if (authentification === true) {
        return (
          <Section>
            <NavLink to="/profile">
              <FormattedMessage {...messages.profile} />
            </NavLink>{' '}
            (
            <NavLink to="#" onClick={onLogoutUser}>
              <FormattedMessage {...messages.logout} />
            </NavLink>
            )
          </Section>
        );
      }
      return (
        <span>
          <Section>
            <NavLink to="/login">
              <FormattedMessage {...messages.login} />
            </NavLink>
          </Section>
          <Section>
            <NavLink to="/join">
              <FormattedMessage {...messages.registration} />
            </NavLink>
          </Section>
        </span>
      );
    };

    return (
      <Wrapper>
        <section>
          <b>
            <FormattedMessage {...messages.name} />
          </b>
        </section>
        <section>
          <Section>
            <NavLink to="/about">
              <FormattedMessage {...messages.about} />
            </NavLink>
          </Section>
          <Section>
            <NavLink exact to="/">
              <FormattedMessage {...messages.home} />
            </NavLink>
          </Section>
          <Section>
            <NavLink to="/contacts">
              <FormattedMessage {...messages.contacts} />
            </NavLink>
          </Section>
          {LoginMenu()}
        </section>
        <section>
          <LocaleToggle />
        </section>
      </Wrapper>
    );
  }
}

Header.propTypes = {
  authentification: PropTypes.bool.isRequired,
  onLogoutUser: PropTypes.func.isRequired,
};

const mapStateToProps = createStructuredSelector({
  authentification: selectors.makeSelectAuthentification(),
});

export function mapDispatchToProps(dispatch) {
  return {
    onLogoutUser: () => dispatch(actions.logoutUser()),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

export default compose(
  withConnect,
  memo,
)(Header);
