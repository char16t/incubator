/*
 * App Messages
 *
 * This contains all the text for the App component.
 */
import { defineMessages } from 'react-intl';

export const scope = 'boilerplate.containers.App';

export default defineMessages({
  titleTemplate: {
    id: `${scope}.titleTemplate`,
    defaultMessage: '%s - Title',
  },
  defaultTitle: {
    id: `${scope}.defaultTitle`,
    defaultMessage: 'Title',
  },
});
