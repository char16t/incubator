/*
 * HomePage
 *
 * List all the features
 */
import React from 'react';
import styled from 'styled-components';
import { intlShape, injectIntl } from 'react-intl';
import { Helmet } from 'react-helmet';
import messages from './messages';

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 2em 0;
`;

// eslint-disable-next-line react/prefer-stateless-function
export class HomePage extends React.Component {
  render() {
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
        <section>No content</section>
      </Wrapper>
    );
  }
}

HomePage.propTypes = {
  intl: intlShape.isRequired,
};

export default injectIntl(HomePage);
