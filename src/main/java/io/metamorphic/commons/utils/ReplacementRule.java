package io.metamorphic.commons.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacementRule {

    private Pattern p;
    private Matcher m;
    private String r;

    public ReplacementRule(String regexp, String replacement) {
        p = Pattern.compile(regexp);
        r = replacement;
    }

    public boolean find(String word) {
        m = p.matcher(word);
        return m.find();
    }

    public String replace(String word) {
        m = p.matcher(word);
        return m.replaceAll(this.r);
    }

}
