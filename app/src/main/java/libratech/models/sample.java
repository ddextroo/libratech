/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libratech.models;

import java.util.Map;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.Attribute;

public final class sample {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+\\-#']+@[A-Z0-9.-]+\\.(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)$", Pattern.CASE_INSENSITIVE);

    public static void validate(final String email, final Map<String, String> hostCache) throws InvalidDataException {
        String[] invalidStrings = {
            "..", ".@"
        };
        for (String illegal : invalidStrings) {
            if (email.contains(illegal)) {
                throw new InvalidDataException("Email " + email + " may not contain '" + illegal + "'");
            }
        }

        Matcher m = EMAIL_PATTERN.matcher(email.trim());
        if (!m.matches()) {
            throw new InvalidDataException(email + " is an invalid email");
        }

        String[] split = email.split("@");
        if (split.length != 2) {
            throw new InvalidDataException("Missing @ symbol in email");
        }

        String tmpHost = split[1];
        String message = checkMX(tmpHost, hostCache);
        if (message != null) {
            throw new InvalidDataException(message);
        }
    }

    private static String checkMX(final String hostName, final Map<String, String> hostCache) {
        if (hostCache != null && hostCache.containsKey(hostName)) {
            return hostCache.get(hostName);
        }

        Hashtable<String, String> env = new Hashtable<>();
        env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");

        try {
            DirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(hostName, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            if (attr == null || attr.size() == 0) {
                String message = "No MX record found for " + hostName;
                if (hostCache != null) {
                    hostCache.put(hostName, message);
                }
                return message;
            }
        } catch (NamingException e) {
            String message = e.getMessage();
            if (hostCache != null) {
                hostCache.put(hostName, message);
            }
            return message;
        }

        return null;
    }

    private static class InvalidDataException extends Exception {

        public InvalidDataException(String message) {
            super(message);
        }
    }
}
