package statistics.matcher;

import java.util.Stack;

public class QueryBuilder {

    Stack<Matcher> stack;

    public QueryBuilder() {
        stack = new Stack<>();
    }
    private QueryBuilder(Stack<Matcher> stack) {
        this.stack = stack;
    }

    public QueryBuilder playsIn(String team) {
        stack.add(new PlaysIn(team));
        return this;
    }
    public QueryBuilder hasAtLeast(int amount, String category) {
        stack.add(new HasAtLeast(amount, category));
        return this;
    }
    public QueryBuilder hasFewerThan(int amount, String category) {
        stack.add(new HasFewerThan(amount, category));
        return this;
    }
    public QueryBuilder oneOf(Matcher... matchers) {
        stack.add(new Or(matchers));
        return this;
    }

    public Matcher build() {

        Matcher[] matchers = new Matcher[stack.size()];

        for (int i = 0; i < matchers.length; i++) {
            matchers[i] = stack.pop();
        }

        reset();
        return new And(matchers);
    }
    public void reset() {
        this.stack = new Stack<>();
    }
    private Stack<Matcher> getStack() {
        Stack<Matcher> returnable = this.stack;
        return returnable;
    }
}
