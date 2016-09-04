package io.metamorphic.commons.utils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * a port of the excellent Inflector class in ruby's ActiveSupport library
 *
 * @author $LastChangedBy: jared $
 * @version $LastChangedRevision: 5 $ <br>
 *          $LastChangedDate: 2008-05-03 13:44:24 -0600 (Sat, 03 May 2008) $
 */
public class Inflector {

    private static Pattern underscorePattern = Pattern.compile("_");
    private static Pattern doubleColonPattern = Pattern.compile("::");
    private static Pattern underscore1Pattern = Pattern.compile("([A-Z]+)([A-Z][a-z])");
    private static Pattern underscore2Pattern = Pattern.compile("([a-z\\d])([A-Z])");

    public static String camelize(String word, boolean firstLetterInUppercase) {
        return null;
    }

    /**
     * replace underscores with dashes in a string
     *
     * @param word
     * @return
     */
    public static String dasherize(String word) {
        Matcher m = underscorePattern.matcher(word);
        return m.replaceAll("-");
    }

    private static Pattern dashPattern = Pattern.compile("-");

    /**
     * replace dashes with underscores in a string
     *
     * @param word
     * @return
     */
    public static String underscorize(String word) {
        Matcher m = dashPattern.matcher(word);
        return m.replaceAll("_");
    }

    /**
     * The reverse of camelize. Makes an underscored form from the expression in
     * the string.
     * <p/>
     * Changes '::' to '/' to convert namespaces to paths.
     *
     * @param word
     * @return
     */
    public static String underscore(String word) {
        String out;
        Matcher m;

        m = doubleColonPattern.matcher(word);
        out = m.replaceAll("/");

        m = underscore1Pattern.matcher(out);
        out = m.replaceAll("$1_$2");

        m = underscore2Pattern.matcher(out);
        out = m.replaceAll("$1_$2");

        out = underscorize(out);

        return out.toLowerCase();
    }

    /**
     * return the plural form of word
     *
     * @param word
     * @return
     */
    public static String pluralize(String word) {
        if ((word.length() == 0)
                || (!uncountables.contains(word.toLowerCase()))) {
            for (ReplacementRule r : plurals) {
                if (r.find(word)) {
                    word = r.replace(word);
                    break;
                }
            }
        }
        return word;
    }

    /**
     * return the singular form of word
     *
     * @param word
     * @return
     */
    public static String singularize(String word) {
        if ((word.length() == 0)
                || (!uncountables.contains(word.toLowerCase()))) {
            for (ReplacementRule r : singulars) {
                if (r.find(word)) {
                    word = r.replace(word);
                    break;
                }
            }
        }
        return word;
    }

    public static void irregular(String singular, String plural) {
        String regexp, repl;

        if (singular.substring(0, 1).toUpperCase().equals(
                plural.substring(0, 1).toUpperCase())) {

            // singular and plural start with the same letter
            regexp = "(?i)(" + singular.substring(0, 1) + ")"
                    + singular.substring(1) + "$";
            repl = "$1" + plural.substring(1);
            plurals.add(0, new ReplacementRule(regexp, repl));

            regexp = "(?i)(" + plural.substring(0, 1) + ")"
                    + plural.substring(1) + "$";
            repl = "$1" + singular.substring(1);
            singulars.add(0, new ReplacementRule(regexp, repl));

        } else {

            // singular and plural don't start with the same letter
            regexp = singular.substring(0, 1).toUpperCase() + "(?i)"
                    + singular.substring(1) + "$";
            repl = plural.substring(0, 1).toUpperCase()
                    + plural.substring(1);
            plurals.add(0, new ReplacementRule(regexp, repl));

            regexp = singular.substring(0, 1).toLowerCase() + "(?i)"
                    + singular.substring(1) + "$";
            repl = plural.substring(0, 1).toLowerCase()
                    + plural.substring(1);
            plurals.add(0, new ReplacementRule(regexp, repl));

            regexp = plural.substring(0, 1).toUpperCase() + "(?i)"
                    + plural.substring(1) + "$";
            repl = singular.substring(0, 1).toUpperCase()
                    + singular.substring(1);
            singulars.add(0, new ReplacementRule(regexp, repl));

            regexp = plural.substring(0, 1).toLowerCase() + "(?i)"
                    + plural.substring(1) + "$";
            repl = singular.substring(0, 1).toLowerCase()
                    + singular.substring(1);
            singulars.add(0, new ReplacementRule(regexp, repl));
        }
    }

    private static ArrayList<ReplacementRule> plurals;
    private static ArrayList<ReplacementRule> singulars;
    private static ArrayList<String> uncountables;

    static {
        plurals = new ArrayList<ReplacementRule>(17) {{
            add(new ReplacementRule("$", "s"));
            add(new ReplacementRule("(?i)s$", "s"));
            add(new ReplacementRule("(?i)(ax|test)is$", "$1es"));
            add(new ReplacementRule("(?i)(octop|vir)us$", "$1i"));
            add(new ReplacementRule("(?i)(alias|status)$", "$1es"));
            add(new ReplacementRule("(?i)(bu)s$", "$1es"));
            add(new ReplacementRule("(?i)(buffal|tomat)o$", "$1oes"));
            add(new ReplacementRule("(?i)([ti])um$", "$1a"));
            add(new ReplacementRule("sis$", "ses"));
            add(new ReplacementRule("(?i)(?:([^f])fe|([lr])f)$", "$1$2ves"));
            add(new ReplacementRule("(?i)(hive)$", "$1s"));
            add(new ReplacementRule("(?i)([^aeiouy]|qu)y$", "$1ies"));
            add(new ReplacementRule("(?i)(x|ch|ss|sh)$", "$1es"));
            add(new ReplacementRule("(?i)(matr|vert|ind)(?:ix|ex)$", "$1ices"));
            add(new ReplacementRule("(?i)([m|l])ouse$", "$1ice"));
            add(new ReplacementRule("^(?i)(ox)$", "$1en"));
            add(new ReplacementRule("(?i)(quiz)$", "$1zes"));
        }};

        singulars = new ArrayList<ReplacementRule>(24) {{
            add(new ReplacementRule("s$", ""));
            add(new ReplacementRule("(n)ews$", "$1ews"));
            add(new ReplacementRule("([ti])a$", "$1um"));
            add(new ReplacementRule(
                    "((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$",
                    "$1$2sis"));
            add(new ReplacementRule("(^analy)ses$", "$1sis"));
            add(new ReplacementRule("([^f])ves$", "$1fe"));
            add(new ReplacementRule("(hive)s$", "$1"));
            add(new ReplacementRule("(tive)s$", "$1"));
            add(new ReplacementRule("([lr])ves$", "$1f"));
            add(new ReplacementRule("([^aeiouy]|qu)ies$", "$1y"));
            add(new ReplacementRule("(s)eries$", "$1eries"));
            add(new ReplacementRule("(m)ovies$", "$1ovie"));
            add(new ReplacementRule("(x|ch|ss|sh)es$", "$1"));
            add(new ReplacementRule("([m|l])ice$", "$1ouse"));
            add(new ReplacementRule("(bus)es$", "$1"));
            add(new ReplacementRule("(o)es$", "$1"));
            add(new ReplacementRule("(shoe)s$", "$1"));
            add(new ReplacementRule("(cris|ax|test)es$", "$1is"));
            add(new ReplacementRule("(octop|vir)i$", "$1us"));
            add(new ReplacementRule("(alias|status)es$", "$1"));
            add(new ReplacementRule("(ox)en$", "$1"));
            add(new ReplacementRule("(virt|ind)ices$", "$1ex"));
            add(new ReplacementRule("(matr)ices$", "$1ix"));
            add(new ReplacementRule("(quiz)zes$", "$1"));
        }};

        uncountables = new ArrayList<String>(8) {{
            add("equipment");
            add("information");
            add("rice");
            add("money");
            add("species");
            add("series");
            add("fish");
            add("sheep");
        }};

        irregular("person", "people");
        irregular("man", "men");
        irregular("child", "children");
        irregular("sex", "sexes");
        irregular("move", "moves");
        irregular("cow", "kine");
    }
}
