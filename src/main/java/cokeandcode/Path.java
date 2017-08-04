package cokeandcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Path {
    private List<Step> steps = new ArrayList();
    int getLength() {
        return steps.size();
    }

    private Step getStep(int index) {
        return steps.get(index);
    }

    int getY(int index) {
        return getStep(index).y;
    }

    int getX(int index) {
        return getStep(index).x;
    }

    void appendStep(int x, int y) {
        steps.add(new Step(x,y));
    }

    void prependStep(int x, int y) {
        steps.add(0,new Step(x,y));
    }

    boolean contains(int x, int y) {
        return steps.contains(new Step(x,y));
    }

    public class Step {
        private int x;
        private int y;

        Step(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Step step = (Step) o;
            return x == step.x &&
                    y == step.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
