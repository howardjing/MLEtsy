import java.util.ArrayList;
import java.lang.Math;
import java.lang.Integer;

public class Helper {
    
    // takes two vectors as input
    // returns the cosine similarity (ranges from [-1,1] where 0 is independent)
    public static double similarity (ArrayList<Double> a, ArrayList<Double> b) {
        // TDL: check if lengths of vectors are the same
        if (a.size() != b.size()) {
            System.out.println("ERROR: VECTORS ARE NOT THE SAME LENGTH");
        }
        double numerator = dotProduct(a,b);
        double denominator = l2Norm(a)*l2Norm(b);
        return (numerator/denominator);
    }
    
    // returns the dot product of two vectors
    public static double dotProduct (ArrayList<Double> a, ArrayList<Double> b) {
        double dotProduct = 0;
        for (int i=0; i<a.size(); i++) {
            dotProduct = dotProduct + a.get(i)*b.get(i);
        }    
        return dotProduct;
    }
    // returns the l2 norm of a vector
    public static double l2Norm (ArrayList<Double> a) {
        // could also do summation = dotProduct(a,a);
        double summation = 0;
        for (Double value : a) {
            summation = summation + Math.pow(value,2);
        }
        return Math.sqrt(summation);
    }
    
    /*
    public static void main(String[] args) {
        ArrayList<Double> a = new ArrayList<Double>();
        ArrayList<Double> b = new ArrayList<Double>();
        
        for (int i=0; i<10; i++) {
            a.add(new Integer(i).doubleValue());
            b.add(new Integer(-1*2*i).doubleValue());
        }
        
        double sim = similarity(a,b);
        System.out.println("Cos: " + sim);

    }
    */
}