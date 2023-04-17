import java.util.*;

// This class represents a single term in a polynomial.
class Polynomial {
    int coef;
    int exp;

    public Polynomial(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }
}

// This class represents a polynomial.
public class Polynomials {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the first polynomial.
        System.out.println("Enter the first polynomial. Enter 0 0 to quit.");
        List<Polynomial> poly1 = readPolynomial(scanner);

        // Read the second polynomial.
        System.out.println("Enter the second polynomial. Enter 0 0 to quit.");
        List<Polynomial> poly2 = readPolynomial(scanner);

        // Print the polynomials and their sum and product.
        System.out.println("Here are your two polynomials.");
        printPolynomial(poly1);
        printPolynomial(poly2);

        System.out.println("Here is their sum.");
        List<Polynomial> sum = addPolynomials(poly1, poly2);
        printPolynomial(sum);

        System.out.println("Here is their product");
        List<Polynomial> product = multiplyPolynomials(poly1, poly2);
        printPolynomial(product);

        // Close the scanner.
        scanner.close();
    }

    // This method reads a polynomial from the user.
    private static List<Polynomial> readPolynomial(Scanner scanner) {
        Map<Integer, Polynomial> polyMap = new HashMap<>();
    
        while (true) {
            int coef = scanner.nextInt();
            int exp = scanner.nextInt();
    
            // If the user enters 0 0, we're done.
            if (coef == 0 && exp == 0) {
                break;
            }

            // If the user enters a negative exponent, we ignore it.
            polyMap.putIfAbsent(exp, new Polynomial(0, exp));
            Polynomial p = polyMap.get(exp);
            p.coef += coef;
        }
        
        // Convert the map to a list and sort it.
        List<Polynomial> poly = new ArrayList<>(polyMap.values());
        poly.sort(Comparator.comparingInt(p -> -p.exp));
        return poly;
    }    

    // This method prints a polynomial.
    private static void printPolynomial(List<Polynomial> poly) {
        StringBuilder sb = new StringBuilder();

        // Loop through the terms in the polynomial.
        for (Polynomial term : poly) {
            if (term.coef == 0) {
                continue;
            }
            if (sb.length() > 0) {
                sb.append(" + ");
            }
            if (term.exp == 0) {
                sb.append(term.coef);
            } else {
                sb.append(term.coef).append("x^").append(term.exp);
            }
        }

        System.out.println(sb.toString());
    }

    // This method adds two polynomials.
    private static List<Polynomial> addPolynomials(List<Polynomial> poly1, List<Polynomial> poly2) {
        List<Polynomial> result = new ArrayList<>(poly1);

        // Loop through the terms in the second polynomial.
        for (Polynomial term : poly2) {
            boolean termExists = false;
            for (Polynomial p : result) {
                if (p.exp == term.exp) {
                    // If the term exists in the first polynomial, add the coefficients.
                    p.coef += term.coef;
                    termExists = true;
                    break;
                }
            }
            
            // If the term doesn't exist in the first polynomial, add it.
            if (!termExists) {
                result.add(new Polynomial(term.coef, term.exp));
            }
        }

        // Sort and return the result.
        result.sort(Comparator.comparingInt(p -> -p.exp));
        return result;
    }

    // This method multiplies two polynomials.
    private static List<Polynomial> multiplyPolynomials(List<Polynomial> poly1, List<Polynomial> poly2) {
        Map<Integer, Integer> resultMap = new HashMap<>();
    
        // Loop through the terms in the first polynomial.
        for (Polynomial term1 : poly1) {
            for (Polynomial term2 : poly2) {
                // Multiply the coefficients and add the exponents.
                int coef = term1.coef * term2.coef;
                int exp = term1.exp + term2.exp;
    
                // Add the result to the map.
                resultMap.put(exp, resultMap.getOrDefault(exp, 0) + coef);
            }
        }
    
        // Convert the map to a list.
        List<Polynomial> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            // If the coefficient is 0, we ignore it.
            if (entry.getValue() != 0) {
                result.add(new Polynomial(entry.getValue(), entry.getKey()));
            }
        }
    
        // Sort and return the result.
        result.sort(Comparator.comparingInt(p -> -p.exp));
        return result;
    }    
}