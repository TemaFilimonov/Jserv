package com.itrasition.jserv;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by Nox on 20.09.2016.
 */
public class Interpretator {
    private static final Pattern CONTROL_PATTERN = Pattern.compile("\\{%(.*?)%\\}");

    public static String interpretate(String input, String... var) {
        int statText = 0;
        int endText;
        StringBuilder outputString = new StringBuilder();
        for (String variableString : var) {
            outputString.append(variableString).append(';');
        }
        Matcher matcher = CONTROL_PATTERN.matcher(input);
        int depth = 0;
        while (matcher.find()) {
            endText = matcher.start();
            String group = matcher.group(1);
            if (statText < endText) {
                outputString.append("System.out.print(\"").append(input.substring(statText, endText)).append("\");");
            }
            switch (group.charAt(0)) {
                case '@':
                    if (group.length() > 1) {
                        outputString.append("for(int i").append(++depth).append(" = 0; i").append(depth)
                                .append("<").append(group.substring(1)).append("; ++i").append(depth).append("){");
                    } else {
                        depth--;
                        outputString.append("}");
                    }
                    break;
                case '=':
                    outputString.append("System.out.print(").append(group.substring(1)).append(");");
                    break;
                case '?':
                    if (group.length() > 1) {
                        outputString.append("if(").append(group.substring(1)).append("){");
                    } else {
                        outputString.append("}");
                    }
                    break;
                default:
                    outputString.append(group);
            }
            statText = matcher.end();
        }
        if (statText < input.length()) {
            outputString.append("System.out.print(\"").append(input.substring(statText)).append("\");");
        }
        return outputString.toString();
    }


}
