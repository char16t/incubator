import React, { memo } from 'react';
import styled from 'styled-components';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';
import { intlShape, injectIntl } from 'react-intl';
import { Helmet } from 'react-helmet';
import * as selectors from 'containers/App/selectors';
import { Redirect } from 'react-router-dom';
import PropTypes from 'prop-types';
import injectSaga from 'utils/injectSaga';
import saga from 'containers/App/saga';
import messages from './messages';

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 2em 0;
`;

// eslint-disable-next-line react/prefer-stateless-function
export class ProfilePage extends React.Component {
  render() {
    const { formatMessage } = this.props.intl;
    const { authentification } = this.props;

    if (authentification !== true) {
      return <Redirect to="/login" />;
    }

    return (
      <Wrapper>
        <Helmet>
          <title>{formatMessage({ ...messages.title })}</title>
          <meta
            name="description"
            content={formatMessage({ ...messages.description })}
          />
        </Helmet>
        <section>Your Profile</section>
      </Wrapper>
    );
  }
}

ProfilePage.propTypes = {
  intl: intlShape.isRequired,
  authentification: PropTypes.bool.isRequired,
};

const mapStateToProps = createStructuredSelector({
  authentification: selectors.makeSelectAuthentification(),
});

export function mapDispatchToProps() {
  return {};
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

export default compose(
  withConnect,
  memo,
  injectSaga({ key: 'profile', saga }),
)(injectIntl(ProfilePage));
