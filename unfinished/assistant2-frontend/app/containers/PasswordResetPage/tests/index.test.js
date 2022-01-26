import React from 'react';
import { render } from 'react-testing-library';
import { IntlProvider } from 'react-intl';

import PasswordResetPage from '../index';

describe('<PasswordResetPage />', () => {
  it('should render and match the snapshot', () => {
    const {
      container: { firstChild },
    } = render(
      <IntlProvider locale="en">
        <PasswordResetPage />
      </IntlProvider>,
    );
    expect(firstChild).toMatchSnapshot();
  });
});
