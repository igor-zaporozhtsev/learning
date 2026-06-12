package interview.aggregate_data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryReportService {
    /*
       Analyze product inventory and return statistics per category.

       Return Map where:
       Key is Category Name (String)
       Value is Total Price (Double) of ALL products in that category

       Rules:
       1. Sum prices of all products in each category
       2. If product has discount > 0, apply it: finalPrice = price * (1 - discount)
       3. Categories with no products should have value 0.0
       4. Skip products with invalid data:
          - price <= 0
          - discount < 0 or discount >= 1.0
    */

	public static Map<String, Double> calculateCategoryTotals(
		List<Category> categories,
		List<Product> products
	) {
		Map<String, Double> totals = new HashMap<>();

		Map<Integer, String> categoryNameById = categories.stream()
			.collect(Collectors.toMap(Category::getId, Category::getName));

		for (Category category : categories) {
			totals.put(category.name, 0.0);
		}

		for (Product product : products) {

			if (product.price <=0 || product.discount < 0 || product.discount >= 1.0){
				break;
			}

			int categoryId = product.categoryId;
			String categoryName = categoryNameById.get(categoryId);

			double finalPrice = product.price * (1 - product.discount);

			totals.computeIfPresent(categoryName, (name, price) -> price + finalPrice);
		}

		return totals;
	}

	public static class Category {

		int id;
		String name;

		public Category(
			int id,
			String name
		) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}
	}

	public static class Product {

		String sku;
		int categoryId;
		double price;
		double discount;  // 0.0 to 0.99 (e.g., 0.15 = 15% off)

		public Product(
			String sku,
			int categoryId,
			double price,
			double discount
		) {
			this.sku = sku;
			this.categoryId = categoryId;
			this.price = price;
			this.discount = discount;
		}

		public String getSku() {
			return sku;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public double getPrice() {
			return price;
		}

		public double getDiscount() {
			return discount;
		}
	}

	public static void main(String[] args) {
		List<Category> categories = List.of(
			new Category(1, "Electronics"),
			new Category(2, "Books"),
			new Category(3, "Clothing")
		);

		List<Product> products = List.of(
			new Product("LAPTOP-1", 1, 1000.0, 0.1),   // $900 after discount
			new Product("PHONE-1", 1, 500.0, 0.0),     // $500 no discount
			new Product("BOOK-1", 2, 20.0, 0.2),       // $16 after discount
			new Product("SHIRT-1", 3, -50.0, 0.0),     // INVALID: negative price
			new Product("BOOK-2", 2, 30.0, 1.5),       // INVALID: discount > 1.0
			new Product("HAT-1", 999, 25.0, 0.0)       // INVALID: category doesn't exist
		);

		Map<String, Double> result = calculateCategoryTotals(categories, products);
		System.out.println(result);

        /* Expected output:
        {
          "Electronics": 1400.0,  // 900 + 500
          "Books": 16.0,          // 16 (one product skipped)
          "Clothing": 0.0         // empty (invalid product skipped)
        }
        */
	}
}
