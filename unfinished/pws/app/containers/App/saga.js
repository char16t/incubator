import { put, takeLatest } from 'redux-saga/effects';
import * as types from 'containers/App/constants';
import { push } from 'connected-react-router';

export function* redirectToProfile() {
  yield put(push('/profile'));
}

export default function* appSaga() {
  yield takeLatest(types.LOGIN_USER, redirectToProfile);
}
