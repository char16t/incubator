import React from 'react';
import { render } from 'react-testing-library';
import { IntlProvider } from 'react-intl';

import JoinPage from '../index';

describe('<JoinPage />', () => {
  it('should render and match the snapshot', () => {
    const {
      container: { firstChild },
    } = render(
      <IntlProvider locale="en">
        <JoinPage />
      </IntlProvider>,
    );
    expect(firstChild).toMatchSnapshot();
  });
});
