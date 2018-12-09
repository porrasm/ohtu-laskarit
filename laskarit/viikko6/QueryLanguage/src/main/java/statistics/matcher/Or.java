
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class Or implements Matcher {

    private Matcher[] matchers;

    public Or(Matcher... matchers) {
        this.matchers = matchers;
    }

    @Override
    public boolean matches(Player p) {

        boolean matches = false;

        for (Matcher m : matchers) {
            if (m.matches(p)) {
                matches = true;
                break;
            }
        }

        return matches;
    }
}
