package mountain;
import fractal.*;
import java.util.HashMap;

public class Mountain extends Fractal {
    private Point a;
    private Point b;
    private Point c;
    private double dev;
    private HashMap sides;

    public Mountain(Point a, Point b, Point c, double dev){
        super();
        this.a = a;
        this.b = b;
        this.c = c;
        this.dev = dev;
    }

    public String getTitle(){
        return "Mountain triangle";
    }

    public void draw(TurtleGraphics turtle) {
        fractalTriangle(turtle, order,  a,  b,  c);
    }

    private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c){
        if(order == 0){
            turtle.moveTo(a.getX(), a.getY());
            turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(a.getX(), a.getY());
        }
        else{
            sides = new HashMap<Side, Point>();
            Side ab = new Side(a,b);
            Side bc = new Side(b,c);
            Side ac = new Side(a,c);
            Point ab_mid;
            Point bc_mid;
            Point ac_mid;

            if(sides.containsKey(ab)){
                ab_mid = (Point)sides.get(ab);
                sides.remove(ab);
            }
            else{
                ab_mid = new Point((a.getX() + b.getX())/2, (int) ((a.getY() + b.getY())/2 +
                        RandomUtilities.randFunc(dev/Math.pow(2,this.order-order))));
                sides.put(ab, ab_mid);
            }
            if(sides.containsKey(bc)){
                bc_mid = (Point) sides.get(ab);
                sides.remove(bc);

            }
            else{
                bc_mid = new Point((c.getX() + b.getX())/2, (int) ((c.getY() + b.getY())/2 +
                        RandomUtilities.randFunc(dev/Math.pow(2,this.order-order))));
                sides.put(bc, bc_mid);
            }
            if(sides.containsKey(ac)){
                ac_mid = (Point) sides.get(ab);
                sides.remove(ac);

            }
            else{
                ac_mid = new Point((a.getX() + c.getX())/2, (int) ((a.getY() + c.getY())/2 +
                        RandomUtilities.randFunc(dev/Math.pow(2,this.order-order))));
                sides.put(ac,ac_mid);
            }

            fractalTriangle(turtle, order-1, a, ab_mid, ac_mid);
            fractalTriangle(turtle, order-1, b, ab_mid, bc_mid);
            fractalTriangle(turtle, order-1, c, bc_mid, ac_mid);
            fractalTriangle(turtle, order-1, bc_mid, ab_mid, ac_mid);

        }
    }

    private static class Side{
        Point p1;
        Point p2;

        private Side(Point p1, Point p2){
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() != Side.class)
                return false;
            Side o = (Side) obj;
            if(p1.equals(o.p1) && p2.equals(o.p2)){
                return true;
            }
            else if(p1.equals(o.p2) && p2.equals(o.p1)){
                return true;
            }
            else
                return false;


        }

        @Override
        public int hashCode() {
            return p1.hashCode() + p2.hashCode();
        }
    }

}
