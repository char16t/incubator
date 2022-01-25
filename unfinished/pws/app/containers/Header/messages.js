import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.Header';

export default defineMessages({
  name: {
    id: `${scope}.name`,
    defaultMessage: 'Title',
  },
  home: {
    id: `${scope}.home`,
    defaultMessage: 'Home',
  },
  about: {
    id: `${scope}.about`,
    defaultMessage: 'About',
  },
  contacts: {
    id: `${scope}.contacts`,
    defaultMessage: 'Contacts',
  },
  login: {
    id: `${scope}.login`,
    defaultMessage: 'Log in',
  },
  registration: {
    id: `${scope}.registration`,
    defaultMessage: 'Join',
  },
  logout: {
    id: `${scope}.logout`,
    defaultMessage: 'Log out',
  },
  profile: {
    id: `${scope}.profile`,
    defaultMessage: 'Profile',
  },
});
