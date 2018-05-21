// IBookManager.aidl
package com.yaopaine.androidart;

import java.util.List;
import com.yaopaine.androidart.chapter2.Book;
import com.yaopaine.androidart.INewBookArrivalListener;
// Declare any non-default types here with import statements

interface IBookManager {

   List<Book> getBookList();

   void addBook(in Book book);

   void registerListener(INewBookArrivalListener listener);

   void unRegisterListener(INewBookArrivalListener listener);
}
