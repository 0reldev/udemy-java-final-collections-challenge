package dev.lpa;

import java.util.*;

public class Store {

    private static Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;
    public static void main(String[] args) {

        Store myStore = new Store();
        myStore.stockStore();
        myStore.listInventory();
//        --------------------
//        Product[sku=R777, name=rice chex, manufacturer=Nabisco, category=CEREAL], $0,96 : [1000, 0]
//        Product[sku=P100, name=pear, manufacturer=local, category=PRODUCE], $0,65 : [1000, 0]
//        Product[sku=M201, name=milk, manufacturer=farm, category=DAIRY], $0,36 : [1000, 0]
//        Product[sku=L103, name=lemon, manufacturer=local, category=PRODUCE], $0,76 : [1000, 0]
//        Product[sku=G111, name=granola, manufacturer=Nat Valley, category=CEREAL], $0,94 : [1000, 0]
//        Product[sku=A100, name=apple, manufacturer=local, category=PRODUCE], $0,79 : [1000, 0]
//        Product[sku=C333, name=cheese, manufacturer=farm, category=DAIRY], $0,27 : [1000, 0]
//        Product[sku=B100, name=banana, manufacturer=local, category=PRODUCE], $0,15 : [1000, 0]
//        Product[sku=Y001, name=yogurt, manufacturer=farm, category=DAIRY], $0,89 : [1000, 0]
//        Product[sku=BB11, name=ground beef, manufacturer=butcher, category=MEAT], $0,80 : [1000, 0]
//        Product[sku=BC11, name=bacon, manufacturer=butcher, category=MEAT], $1,07 : [1000, 0]
//        Product[sku=BC77, name=coke, manufacturer=coca cola, category=BEVERAGE], $0,96 : [1000, 0]
//        Product[sku=BC88, name=coffee, manufacturer=value, category=BEVERAGE], $0,74 : [1000, 0]
//        Product[sku=BC99, name=tea, manufacturer=herbal, category=BEVERAGE], $0,19 : [1000, 0]
//        Product[sku=CC11, name=chicken, manufacturer=butcher, category=MEAT], $0,61 : [1000, 0]

        myStore.stockAisles();
        myStore.listProductsByCategory();
//        --------
//        PRODUCE
//        --------
//        apple
//        banana
//        lemon
//        pear
//        --------
//        DAIRY
//        --------
//        cheese
//        milk
//        yogurt
//        --------
//        CEREAL
//        --------
//        granola
//        rice chex
//        --------
//        MEAT
//        --------
//        bacon
//        chicken
//        ground beef
//        --------
//        BEVERAGE
//        --------
//        coffee
//        coke
//        tea

        myStore.manageStoreCarts();
        myStore.listProductsByCategory(false, true);
//        Cart{id=1, cartDate=2023-08-10, products={P100=5, BC88=1, A100=6}}
//        2 [pear]s removed
//        Cart{id=1, cartDate=2023-08-10, products={P100=3, BC88=1, A100=6}}
//        Product[sku=A100, name=apple, manufacturer=local, category=PRODUCE], $1,19 : [1000, 6]
//        Product[sku=B100, name=banana, manufacturer=local, category=PRODUCE], $0,02 : [1000, 0]
//        Product[sku=L103, name=lemon, manufacturer=local, category=PRODUCE], $0,07 : [1000, 0]
//        Product[sku=P100, name=pear, manufacturer=local, category=PRODUCE], $0,98 : [1000, 3]
//        Product[sku=C333, name=cheese, manufacturer=farm, category=DAIRY], $0,89 : [1000, 0]
//        Product[sku=M201, name=milk, manufacturer=farm, category=DAIRY], $0,14 : [1000, 0]
//        Product[sku=Y001, name=yogurt, manufacturer=farm, category=DAIRY], $0,69 : [1000, 0]
//        Product[sku=G111, name=granola, manufacturer=Nat Valley, category=CEREAL], $0,23 : [1000, 0]
//        Product[sku=R777, name=rice chex, manufacturer=Nabisco, category=CEREAL], $0,52 : [1000, 0]
//        Product[sku=BC11, name=bacon, manufacturer=butcher, category=MEAT], $0,68 : [1000, 0]
//        Product[sku=CC11, name=chicken, manufacturer=butcher, category=MEAT], $0,63 : [1000, 0]
//        Product[sku=BB11, name=ground beef, manufacturer=butcher, category=MEAT], $0,16 : [1000, 0]
//        Product[sku=BC88, name=coffee, manufacturer=value, category=BEVERAGE], $0,94 : [1000, 1]
//        Product[sku=BC77, name=coke, manufacturer=coca cola, category=BEVERAGE], $0,82 : [1000, 0]
//        Product[sku=BC99, name=tea, manufacturer=herbal, category=BEVERAGE], $0,31 : [1000, 0]
    }

    private void manageStoreCarts() {
        Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
        carts.add(cart1);
        InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
        cart1.addItem(item, 6);
        cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
        cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
        System.out.println(cart1);

        cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
        System.out.println(cart1);
    }

    private boolean checkOutCart(Cart cart) {
        return false;
    }

    private void abandonCarts() {
    }

    private void listProductsByCategory() {
        listProductsByCategory(true, false);
    }

    private void listProductsByCategory(boolean includeHeader, boolean includeDetail) {
        aisleInventory.keySet().forEach(k -> {
            if (includeHeader) System.out.println("--------\n" + k + "\n--------");
            if (!includeDetail) {
                aisleInventory.get(k).keySet().forEach(System.out::println);
            } else {
                aisleInventory.get(k).values().forEach(System.out::println);
            }
        });
    }

    private void stockStore() {
        inventory = new HashMap<>();
        List<Product> products = new ArrayList<>(List.of(
            new Product("A100", "apple", "local", Category.PRODUCE),
                new Product("A100", "apple", "local", Category.PRODUCE),
                new Product("B100", "banana", "local", Category.PRODUCE),
                new Product("P100", "pear", "local", Category.PRODUCE),
                new Product("L103", "lemon", "local", Category.PRODUCE),
                new Product("M201", "milk", "farm", Category.DAIRY),
                new Product("Y001", "yogurt", "farm", Category.DAIRY),
                new Product("C333", "cheese", "farm", Category.DAIRY),
                new Product("R777", "rice chex", "Nabisco", Category.CEREAL),
                new Product("G111", "granola", "Nat Valley", Category.CEREAL),
                new Product("BB11", "ground beef", "butcher", Category.MEAT),
                new Product("CC11", "chicken", "butcher", Category.MEAT),
                new Product("BC11", "bacon", "butcher", Category.MEAT),
                new Product("BC77", "coke", "coca cola", Category.BEVERAGE),
                new Product("BC88", "coffee", "value", Category.BEVERAGE),
                new Product("BC99", "tea", "herbal", Category.BEVERAGE)
        ));

        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, random.nextDouble(0, 1.25), 1_000, 5)));
    }

    private void stockAisles() {
        aisleInventory = new EnumMap<>(Category.class);
        for (InventoryItem item : inventory.values()) {
            Category aisle = item.getProduct().category();
            Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
            if (productMap == null) {
                productMap = new TreeMap<>();
            }
            productMap.put(item.getProduct().name(), item);
            aisleInventory.putIfAbsent(aisle, productMap);
        }
    }

    private void listInventory() {
        System.out.println("-".repeat(20));
        inventory.values().forEach((System.out::println));
    }
}
