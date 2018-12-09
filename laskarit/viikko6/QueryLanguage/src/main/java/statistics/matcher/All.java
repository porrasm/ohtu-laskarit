
package statistics.matcher;

import statistics.Player;

public class All implements Matcher {
    public boolean matches(Player p) {
        return true;
    }
}


/*
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class All implements Matcher {

    private String fieldName;
    private Object expectedValue;

    public All(String category, Object expected) {
        fieldName = "get"+Character.toUpperCase(category.charAt(0))+category.substring(1, category.length());
        this.expectedValue = expected;
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(fieldName);
            Object playersValue = method.invoke(p);
            return playersValue.equals(expectedValue);

        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field "+fieldName.substring(3, fieldName.length()).toLowerCase());
        }

    }

}
*/
