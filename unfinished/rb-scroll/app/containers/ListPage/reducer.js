import produce from 'immer';
import * as types from './constants';

export const initialState = {
  data: [],
  loading: false,
  loadedRowsMap: {},
  currentPage: 1,
};

/* eslint-disable default-case, no-param-reassign */
const listPageReducer = (state = initialState, action) =>
  produce(state, draft => {
    switch (action.type) {
      case types.ON_LIST_DATA_REQUEST:
        draft.loading = true;
        break;
      case types.ON_LIST_DATA_SUCCESS:
        draft.data = draft.data.concat(action.data);
        draft.currentPage += 1;
        draft.loading = false;
        break;
      case types.ON_LIST_DATA_FAILURE:
        draft.loading = false;
        break;
      case types.ON_LOADED_ROWS_MAP_UPDATE:
        draft.loadedRowsMap = { ...draft.loadedRowsMap, ...action.pair };
        break;
    }
  });

export default listPageReducer;
