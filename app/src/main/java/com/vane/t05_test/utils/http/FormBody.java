package com.vane.t05_test.utils.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by LG on 2016/10/16.
 * Tips:
 */

public final class FormBody extends RequestBody{
    private final List<String> encodedNames;
    private final List<String> encodedValues;
    private FormBody(List<String> encodedNames, List<String> encodedValues) {
        super(formContentType);
        this.encodedNames = encodedNames;
        this.encodedValues = encodedValues;
    }
    public List<String> getEncodedNames() {
        return encodedNames;
    }

    public List<String> getEncodedValues() {
        return encodedValues;
    }

    @Override
    public byte[] getBodyBytes() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encodedNames.size(); i++) {
            sb.append(encodedNames.get(i));
            sb.append("=");
            sb.append(encodedValues.get(i));
            sb.append("&");
        }
        if(sb.length() != 0){
            sb.deleteCharAt(sb.length()-1);
        }
        return getUTFBodyBytes(sb.toString());
    }


    public static final class Builder {
        private final List<String> names = new ArrayList<>();
        private final List<String> values = new ArrayList<>();

        public Builder add(String name, String value) {
            names.add(name);
            values.add(value);
            return this;
        }

        public Builder addEncoded(String name, String value) {
            try {
                names.add(URLEncoder.encode(name,"utf-8"));
                values.add(URLEncoder.encode(value,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return this;
        }

        public FormBody build() {
            return new FormBody(names, values);
        }
    }
}
