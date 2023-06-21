import java.util.ArrayList;
import java.util.Scanner;

class ManufacturingController {
    private static ArrayList<String> products = new ArrayList<>();

    public static String requestProduct(String product) {
        products.add(product);
        return String.format("%d. Requested %s", products.size(), product);
    }

    public static int getNumberOfProducts() {
        return products.size();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}