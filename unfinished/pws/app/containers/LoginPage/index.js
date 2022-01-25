import React, { memo } from 'react';
import styled from 'styled-components';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';
import { intlShape, injectIntl } from 'react-intl';
import { Helmet } from 'react-helmet';
import * as actions from 'containers/App/actions';
import PropTypes from 'prop-types';
import messages from './messages';

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 2em 0;
`;

// eslint-disable-next-line react/prefer-stateless-function
export class LoginPage extends React.Component {
  render() {
    const { onLoginUser } = this.props;
    const { formatMessage } = this.props.intl;
    return (
      <Wrapper>
        <Helmet>
          <title>{formatMessage({ ...messages.title })}</title>
          <meta
            name="description"
            content={formatMessage({ ...messages.description })}
          />
        </Helmet>
        <section>
          <button onClick={onLoginUser}>Login</button>
        </section>
      </Wrapper>
    );
  }
}

LoginPage.propTypes = {
  intl: intlShape.isRequired,
  onLoginUser: PropTypes.func,
};

const mapStateToProps = createStructuredSelector({});

export function mapDispatchToProps(dispatch) {
  return {
    onLoginUser: () => dispatch(actions.loginUser()),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

export default compose(
  withConnect,
  memo,
)(injectIntl(LoginPage));
