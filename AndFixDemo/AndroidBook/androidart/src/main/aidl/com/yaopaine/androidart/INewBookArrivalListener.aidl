// INewBookArrival.aidl
package com.yaopaine.androidart;

import com.yaopaine.androidart.chapter2.Book;

interface INewBookArrivalListener {
   void onNewBookArrived(in Book book);
}
