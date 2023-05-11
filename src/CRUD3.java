import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Прайсы 2
*/

public class CRUD3 {
    public static class Product {
        int id;
        String name;
        String price;
        String quantity;

        public Product(int id, String name, String price, String quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%-8d%-30s%-8s%-4s", id, name, price, quantity);
        }
    }

    public static Product getProduct(String string) {
        String id = string.substring(0, 8).trim();
        String name = string.substring(8, 38).trim();
        String price = string.substring(38, 46).trim();
        String quantity = string.substring(46, 50).trim();
        return new Product(Integer.parseInt(id), name, price, quantity);
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String name = read.readLine();

        BufferedReader reader = new BufferedReader(new FileReader(name));
        List<Product> products = new ArrayList<>();

        while (reader.ready()) {
            Product product = getProduct(reader.readLine());
            products.add(product);
        }
        read.close();
        reader.close();

        switch (args[0]) {
            case "-c":
                int id = 0;
                for (Product prod : products) {
                    if (prod.id > id) {
                        id = prod.id;
                    }
                }
                id += 1;

                String productName = "";
                for (int i = 1; i < args.length - 2; i++) {
                    productName += args[i] + " ";
                }
                if (productName.length() > 30) {
                    productName = productName.substring(0, 30);
                }
                productName = productName.trim();

                String price = args[args.length - 2];
                if (price.length() > 8) {
                    price = price.substring(0, 8);
                }

                String quantity = args[args.length - 1];
                if (quantity.length() > 4) {
                    quantity = quantity.substring(0, 4);
                }

                Product product = new Product(id, productName, price, quantity);

                FileWriter writer = new FileWriter(name, true);
                writer.write("\n");
                writer.write(product.toString());
                writer.close();
                break;

            case "-u":
                int id_U = Integer.parseInt(args[1]);

                String name_U = "";
                for (int i = 2; i < args.length - 2; i++) {
                    name_U += args[i] + " ";
                }

                if (name_U.length() > 30) {
                    name_U = name_U.substring(0, 30);
                }

                String price_U = args[args.length - 2];
                if (price_U.length() > 8) {
                    price_U = price_U.substring(0, 8);
                }

                String quantity_U = args[args.length - 1];
                if (quantity_U.length() > 4) {
                    quantity_U = quantity_U.substring(0, 4);
                }

                Product productToUpdate = null;
                for (Product product_new : products) {
                    if (product_new.id == id_U) productToUpdate = product_new;
                }

                if (productToUpdate != null) {
                    productToUpdate.name = name_U;
                    productToUpdate.price = price_U;
                    productToUpdate.quantity = quantity_U;
                }
                break;
            case "-d":
                int id_del = Integer.parseInt(args[1]);
                Product prod_delete = null;

                for (Product prod_del : products) {
                    if (prod_del.id == id_del) {
                        prod_delete = prod_del;
                    }
                }

                if (prod_delete != null){
                    products.remove(prod_delete);
                }
                break;
        }

        FileWriter writer = new FileWriter(name);
        for (Product prods : products){
            writer.write(prods.toString());
            writer.write("\n");
        }
        writer.close();
    }
}