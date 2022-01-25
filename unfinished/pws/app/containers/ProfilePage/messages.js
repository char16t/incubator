import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.ProfilePage';

export default defineMessages({
  title: {
    id: `${scope}.title`,
    defaultMessage: 'Profile',
  },
  description: {
    id: `${scope}.description`,
    defaultMessage: 'A React.js application profile page',
  },
});
