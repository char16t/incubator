import React from 'react';
import { render } from 'react-testing-library';
import { IntlProvider } from 'react-intl';

import PasswordRestorePage from '../index';

describe('<PasswordRestorePage />', () => {
  it('should render and match the snapshot', () => {
    const {
      container: { firstChild },
    } = render(
      <IntlProvider locale="en">
        <PasswordRestorePage />
      </IntlProvider>,
    );
    expect(firstChild).toMatchSnapshot();
  });
});
