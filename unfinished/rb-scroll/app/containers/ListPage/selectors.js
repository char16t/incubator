import { createSelector } from 'reselect';
import { initialState } from './reducer';

export const selectListPage = state => state.listPage || initialState;

export const makeSelectData = () =>
  createSelector(
    selectListPage,
    listPageState => listPageState.data,
  );

export const makeSelectLoading = () =>
  createSelector(
    selectListPage,
    listPageState => listPageState.loading,
  );

export const makeSelectLoadedRowsMap = () =>
  createSelector(
    selectListPage,
    listPageState => listPageState.loadedRowsMap,
  );

export const makeSelectCurrentPage = () =>
  createSelector(
    selectListPage,
    listPageState => listPageState.currentPage,
  );
