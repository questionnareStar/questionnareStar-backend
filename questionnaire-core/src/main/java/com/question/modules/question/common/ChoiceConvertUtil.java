package com.question.modules.question.common;

import java.util.Arrays;
import java.util.List;

public class ChoiceConvertUtil {
    private static final String divideString = "`";
    public static String ChoicesToString(List<String> choices){
        if (choices.size()==0){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(choices.get(0));
        for (int i = 1; i < choices.size(); i++) {
            stringBuilder.append(divideString).append(choices.get(i));
        }
        return stringBuilder.toString();
    }
    public static List<String>  StringToChoices (String string){
        String[] strings = string.split(divideString);
        return Arrays.asList(strings);
    }
}
