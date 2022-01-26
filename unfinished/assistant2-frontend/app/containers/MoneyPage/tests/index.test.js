import React from 'react';
import { render } from 'react-testing-library';
import { IntlProvider } from 'react-intl';

import MoneyPage from '../index';

describe('<MoneyPage />', () => {
  it('should render and match the snapshot', () => {
    const {
      container: { firstChild },
    } = render(
      <IntlProvider locale="en">
        <MoneyPage />
      </IntlProvider>,
    );
    expect(firstChild).toMatchSnapshot();
  });
});
