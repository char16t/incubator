import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.LoginPage';

export default defineMessages({
  title: {
    id: `${scope}.title`,
    defaultMessage: 'Log in',
  },
  description: {
    id: `${scope}.description`,
    defaultMessage: 'A React.js application login page',
  },
});
