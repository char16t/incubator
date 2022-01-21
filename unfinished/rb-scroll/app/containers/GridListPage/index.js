import React, { useEffect, memo } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';
import { List, message, Avatar, Spin } from 'antd';
import {
  WindowScroller,
  AutoSizer,
  List as VList,
  InfiniteLoader,
} from 'react-virtualized';

import { useInjectReducer } from 'utils/injectReducer';
import { useInjectSaga } from 'utils/injectSaga';
import * as selectors from './selectors';
import * as actions from './actions';
import reducer from './reducer';
import saga from './saga';

import './style.css';

const key = 'listPage';

export function ListPage({
  data,
  loading,
  loadedRowsMap,
  onListDataRequest,
  onLoadedRowsMapUpdate,
}) {
  useInjectReducer({ key, reducer });
  useInjectSaga({ key, saga });

  useEffect(() => {
    if (data.length === 0) onListDataRequest();
  }, []);

  const handleInfiniteOnLoad = ({ startIndex, stopIndex }) => {
    for (let i = startIndex; i <= stopIndex; i += 1) {
      // 1 means loading
      onLoadedRowsMapUpdate(i, 1);
    }
    if (data.length > 50) {
      message.warning('Virtualized List loaded all');
      return;
    }
    onListDataRequest();
  };

  const isRowLoaded = ({ index }) => !!loadedRowsMap[index];

  const renderItem = ({ index, key, style }) => {
    const item = data[index];
    return (
      <List.Item key={key} style={style}>
        <List.Item.Meta
          avatar={<Avatar size="small" />}
          title={<a href="https://ant.design">{item.name.last}</a>}
          description={item.email}
        />
        <div>Content {index}</div>
      </List.Item>
    );
  };

  const vlist = ({
    height,
    isScrolling,
    onChildScroll,
    scrollTop,
    onRowsRendered,
    width,
  }) => (
    <VList
      autoHeight
      height={height}
      isScrolling={isScrolling}
      onScroll={onChildScroll}
      overscanRowCount={2}
      rowCount={data.length}
      rowHeight={73}
      rowRenderer={renderItem}
      onRowsRendered={onRowsRendered}
      scrollTop={scrollTop}
      width={width}
    />
  );

  const autoSize = ({
    height,
    isScrolling,
    onChildScroll,
    scrollTop,
    onRowsRendered,
  }) => (
    <AutoSizer disableHeight>
      {({ width }) =>
        vlist({
          height,
          isScrolling,
          onChildScroll,
          scrollTop,
          onRowsRendered,
          width,
        })
      }
    </AutoSizer>
  );

  const infiniteLoader = ({
    height,
    isScrolling,
    onChildScroll,
    scrollTop,
  }) => (
    <InfiniteLoader
      isRowLoaded={isRowLoaded}
      loadMoreRows={handleInfiniteOnLoad}
      rowCount={data.length}
    >
      {({ onRowsRendered }) =>
        autoSize({
          height,
          isScrolling,
          onChildScroll,
          scrollTop,
          onRowsRendered,
        })
      }
    </InfiniteLoader>
  );

  return (
    <List>
      {data.length > 0 && <WindowScroller>{infiniteLoader}</WindowScroller>}
      {loading && <Spin className="demo-loading" />}
    </List>
  );
}

ListPage.propTypes = {
  data: PropTypes.array.isRequired,
  loading: PropTypes.bool.isRequired,
  loadedRowsMap: PropTypes.object.isRequired,
  onListDataRequest: PropTypes.func.isRequired,
  onLoadedRowsMapUpdate: PropTypes.func.isRequired,
};

const mapStateToProps = createStructuredSelector({
  data: selectors.makeSelectData(),
  loading: selectors.makeSelectLoading(),
  loadedRowsMap: selectors.makeSelectLoadedRowsMap(),
});

export function mapDispatchToProps(dispatch) {
  return {
    onListDataRequest: () => dispatch(actions.onListDataRequest()),
    onLoadedRowsMapUpdate: (index, value) =>
      dispatch(actions.onLoadedRowsMapUpdate(index, value)),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

export default compose(
  withConnect,
  memo,
)(ListPage);
