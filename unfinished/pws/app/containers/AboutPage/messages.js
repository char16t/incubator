/*
 * FeaturePage Messages
 *
 * This contains all the text for the FeaturePage component.
 */
import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.AboutPage';

export default defineMessages({
  title: {
    id: `${scope}.title`,
    defaultMessage: 'About',
  },
  description: {
    id: `${scope}.description`,
    defaultMessage: 'A React.js application about page',
  },
});
