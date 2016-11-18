package com.vane.t05_test.utils.http;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LG on 2016/10/16.
 * Tips:
 */

public class Headers {
    private List<String> namesAndValues;
    private Headers(List<String> namesAndValues){
        this.namesAndValues = namesAndValues;
    }

    public List<String> getNamesAndValues() {
        return namesAndValues;
    }

    public static class Builder{
        private List<String> namesAndValues =  new ArrayList<>(20);
        public Builder addHeader(String name, String value){
            namesAndValues.add(name);
            namesAndValues.add(value);
            return this;
        }
        public Headers build(){
            return new Headers(namesAndValues);
        }
    }
}
