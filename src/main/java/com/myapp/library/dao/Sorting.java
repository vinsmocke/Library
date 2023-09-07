package com.myapp.library.dao;

import com.myapp.library.models.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
    public static void sortingByBookName(List<Book> books){

        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
