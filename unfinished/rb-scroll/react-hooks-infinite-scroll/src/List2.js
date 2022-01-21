import React, { useState } from "react";
import useInfiniteScroll from "./useInfiniteScroll";

const List2 = () => {
  const [listItems, setListItems] = useState(
    Array.from(Array(30).keys(), n => n + 1)
  );
  const [isFetching, setIsFetching] = useInfiniteScroll(fetchMoreListItems);

  function fetchMoreListItems() {
    setTimeout(() => {
      setListItems(prevState => [
        ...prevState,
        ...Array.from(Array(20).keys(), n => n + prevState.length + 1)
      ]);
      if (
        window.innerHeight * 1.1 + document.documentElement.scrollTop >=
        document.documentElement.offsetHeight
      ) {
        fetchMoreListItems();
      } else {
        setIsFetching(false);
      }
    }, 500);
  }

  return (
    <>
      <ul className="list-group mb-2">
        {listItems.map(listItem => (
          <li className="list-group-item">List Item {listItem}</li>
        ))}
      </ul>
      {isFetching && "Fetching more list items..."}
    </>
  );
};

export default List2;
