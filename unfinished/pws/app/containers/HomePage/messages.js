import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.HomePage';

export default defineMessages({
  title: {
    id: `${scope}.title`,
    defaultMessage: 'Home',
  },
  description: {
    id: `${scope}.description`,
    defaultMessage: 'A React.js application home page',
  },
});
