package edu.acme.factory;

import java.util.List;

public interface AbstractFactory<T> {
    List<T> getSampleData();
}
