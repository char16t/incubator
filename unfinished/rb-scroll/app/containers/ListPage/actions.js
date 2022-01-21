import * as types from './constants';

export function onListDataRequest() {
  return {
    type: types.ON_LIST_DATA_REQUEST,
  };
}

export function onListDataSuccess(data) {
  return {
    type: types.ON_LIST_DATA_SUCCESS,
    data,
  };
}

export function onListDataFailure() {
  return {
    type: types.ON_LIST_DATA_FAILURE,
  };
}

export function onLoadedRowsMapUpdate(key, value) {
  return {
    type: types.ON_LOADED_ROWS_MAP_UPDATE,
    pair: { [key]: value },
  };
}
