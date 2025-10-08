package com.codelab.basics;

import java.util.List;

public interface DB_Interface {
    List<DataModel> findAll();
    void incAccessCount(int number);
    DataModel getMostAccessed();
}
