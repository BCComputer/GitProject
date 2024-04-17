package shoppingCart;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop"));
        products.add(new Product(2, "Table"));
        products.add(new Product(3, "Lights"));

        Map<Product, Integer> cart = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available products: Laptop, Table, Lights");
        System.out.println("Enter the products and quantities you'd like to add to cart (e.g., Laptop 5, Table 3, Lights 2):");
        String input = scanner.nextLine();
        String[] selectedProducts = input.split(",");

        for(String product : selectedProducts) {
            String[] parts = product.trim().split(" ");
            if (parts.length == 2) {
                String selectedProduct = parts[0];
                int quantity = Integer.parseInt(parts[1]);

                switch (selectedProduct) {
                    case "Laptop":
                        cart.put(products.get(0), quantity);
                        break;
                    case "Table":
                        cart.put(products.get(1), quantity);
                        break;
                    case "Lights":
                        cart.put(products.get(2), quantity);
                        break;
                    default:
                        System.out.println("Invalid product: " + selectedProduct);
                }
            }
        }

        System.out.println("Products in cart:");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.println(entry.getKey() + " - Quantity: " + entry.getValue());
        }
    }
}