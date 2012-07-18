package org.testingsoftware.selrunner;

import org.openqa.selenium.Keys;

import fitnesse.slim.Converter;

public class KeysConverter implements Converter {

    public String toString(Object o) {
        return ((Keys) o).name();
    }

    public Object fromString(String key_value) {
        return Keys.valueOf(key_value);
    }

}
