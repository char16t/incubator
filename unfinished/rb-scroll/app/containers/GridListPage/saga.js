import { call, put, select, takeLatest } from 'redux-saga/effects';

import request from 'utils/request';
import * as selectors from './selectors';
import * as types from './constants';
import * as actions from './actions';

export function* onListDataRequest() {
  const currentPage = yield select(selectors.makeSelectCurrentPage());
  console.warn('currentPage', currentPage);

  const requestURL =
    'https://randomuser.me/api/?results=5&inc=name,gender,email,nat&noinfo';

  try {
    // Call our request helper (see 'utils/request')
    const data = yield call(request, requestURL);
    yield put(actions.onListDataSuccess(data.results));
  } catch (err) {
    yield put(actions.onListDataFailure());
  }
}

export default function* listData() {
  yield takeLatest(types.ON_LIST_DATA_REQUEST, onListDataRequest);
}
