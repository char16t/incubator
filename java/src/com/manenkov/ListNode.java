package com.manenkov;

public interface ListNode<T> {
    T getData();
    void setData(final T data);
    ListNode<T> getPrevious();
    void setPrevious(ListNode<T> previous);
    ListNode<T> getNext();
    void setNext(ListNode<T> next);
}
