package com.jhjz.emr.lstd_public.utils;

import com.alibaba.fastjson.serializer.NameFilter;

public class PascalNameFilter implements NameFilter {

    public String process(Object source, String name, Object value) {
        if (name == null || name.length() == 0) {
            return name;
        }

        char[] chars = name.toCharArray();
        chars[0]= Character.toUpperCase(chars[0]);

        String pascalName = new String(chars);
        return pascalName;
    }
}
