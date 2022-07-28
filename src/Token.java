public class Token {
    public String id;
    private int point;

    public Token(String id) {
        this.id = id;
    }

    public int getPoint(Token t) {
        this.point = Integer.parseInt(t.id.substring(1,2)) + Integer.parseInt(t.id.substring(t.id.length() - 1)) * 10;
        return point;
    }
}
