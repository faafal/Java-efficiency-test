import java.util.Objects;

class Color implements Comparable<Color>{
    int r,g,b,sum;

    public Color() {
        this.r = (int) Math.floor(Math.random() * 256);
        this.g = (int) Math.floor(Math.random() * 256);
        this.b = (int) Math.floor(Math.random() * 256);
        this.sum = this.r + this.g + this.b;
    }
    public int getSum() {
        return sum;
    }
    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return sum == color.sum;
    }
    @Override
    public int compareTo(Color o) {
        return sum - o.getSum();
    }
}