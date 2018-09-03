import java.util.Scanner;


public class Sale
{
    private ProductList prodList;
    private SaleTransaction transaction;

    public Sale()
    {
        prodList = new ProductList();
        RandomNumberGenerator saleCodeGen = new RandomNumberGenerator(1000,9999);
        int newSaleCode = saleCodeGen.getRandomNumber();
        transaction = new SaleTransaction(newSaleCode);
    }

    public void mainSystem()
    {
        int option = 0;
        while (option != 7)
        {
            Scanner console = new Scanner(System.in);
            display();
            option = Integer.parseInt(console.nextLine());
            options(option);
        }
    }

    public void options(int opt)
    {
        switch (opt)
        {
            case 1: registerProduct();
                    break;
            case 2: buyProductToCart();
                    break;
            case 3: removeProductFromCart();
                    break;
            case 4: viewAllProducts(); break;
            case 5: checkOut(); break;
            case 6: System.out.println("6"); break;
            case 7: System.out.println("System will be closed."); break;
            default: System.out.println("Input Error. You need to choose 1 to 7."); break;
        }
    }

    public void registerProduct()
    {
        Scanner console = new Scanner(System.in);
        //Enter product name
        System.out.print("Please enter the product's name: ");
        String prodName = console.nextLine().toLowerCase();
        while (!checkProdNameLength(prodName) || prodList.isProdExists(prodName))
        {
            System.out.print("Please enter the product's name: ");
            prodName = console.nextLine();
        }
        //Enter product description
        System.out.print("Please enter the description: ");
        String prodDesc = console.nextLine();
        while (prodDesc.length() < 1 || prodDesc.length() > 50)
        {
            System.out.println("Product's description must be between 1 and 50 characters long.");
            System.out.print("Please enter the description: ");
            prodDesc = console.nextLine();
        }
        //Enter product price
        System.out.print("Please enter the price: ");
        String priceStr = console.nextLine();
        double price = Double.parseDouble(priceStr);
        while (price <= 0)
        {
            System.out.println("Price must be greater than 0.");
            System.out.print("Please enter the price: ");
            priceStr = console.nextLine();
        }
        price = Double.parseDouble(priceStr);
        RandomNumberGenerator qtyOnHandGen = new RandomNumberGenerator(0,10);
        int qtyOnHand = qtyOnHandGen.getRandomNumber();
        RandomNumberGenerator minOrderQtyGen = new RandomNumberGenerator(1,5);
        int minOrderQty = minOrderQtyGen.getRandomNumber();
        //Create a new product object
        Product newProduct = new Product(prodName.toLowerCase(), prodDesc, price, qtyOnHand, minOrderQty);
        //Add the object to ProductList
        prodList.addProduct(newProduct);
    }

    public void buyProductToCart()
    {
        Scanner console = new Scanner(System.in);
        int prodNumber = 0;
        while (prodNumber != 6)
        {
            System.out.println("Please select from the following products which are available:");
            selectProductFromList(prodList.getListOfProducts());
            String prodNumberStr = console.nextLine();
            prodNumber = Integer.parseInt(prodNumberStr);
            while (prodNumber - 1 > 5 )
            {
                System.out.print("The number should be less than 7, please enter again: ");
                prodNumberStr = console.nextLine();
                prodNumber = Integer.parseInt(prodNumberStr);
            }
            if (prodNumber != 6)
            {
                if (prodList.getListOfProducts()[prodNumber - 1] != null)
                {
                    Product selectedProduct = prodList.getListOfProducts()[prodNumber - 1];
                    if (selectedProduct.getQtyOnHand() >= selectedProduct.getMinOrderQty())
                    {
                        transaction.addProduct(selectedProduct);
                    }
                    else
                    {
                        System.out.println("This product is out of stock. Can not be added to your cart.");
                    }
                }
                else
                    System.out.println("Product does not exist, please check the number.");
            }
        }
    }

    public void removeProductFromCart()
    {
        Scanner console = new Scanner(System.in);
        int prodNumber = 0;
        while (prodNumber != 4)
        {
            System.out.println("Please select from the following products which have been added to the cart:");
            selectProductFromList(transaction.getItems());
            String prodNumberStr = console.nextLine();
            prodNumber = Integer.parseInt(prodNumberStr);
            while (prodNumber - 1 > 3 )
            {
                System.out.print("The number should be less than 4, please enter again: ");
                prodNumberStr = console.nextLine();
                prodNumber = Integer.parseInt(prodNumberStr);
            }
            if (prodNumber != 4)
            {
                if (transaction.getItems()[prodNumber - 1] != null)
                {
                    transaction.removeProduct(prodNumber - 1);
                }
                else
                    System.out.println("Product does not exist in the cart, please check the number.");
            }
        }
    }

    public void selectProductFromList(Product[] list)
    {
        int i = 0;
        for (; i < list.length; i++)
        {

            if (list[i] != null)
            {
                System.out.println("Select Product " + (i + 1) + ": ");
                System.out.println("  Description" + ": " + list[i].getDesc());
                System.out.println("  Quantity" + ": " + list[i].getQtyOnHand());
                System.out.println("  Price" + ": " + list[i].getPrice());
                System.out.println("  Min Order Quantity" + ": " + list[i].getMinOrderQty());
                System.out.println("");
            }
        }
        System.out.println("Transaction " + transaction.getSaleCode() + ". Current Total cost is " + transaction.getTotalCost());
        System.out.println("Select " + (list.length + 1) + " to exit menu");
        System.out.print("Please enter selected product: ");

    }

    public void checkOut()
    {
        int duplicatedProductNumber = 1;
        Product[] cartList = transaction.getItems();
        Product[] productList = prodList.getListOfProducts();
        for (int i = 0; i < cartList.length; i++)
        {
            if (cartList[i] != null)
            {
                Product cartProduct = cartList[i];
                String cartProductName = cartProduct.getName();
                for (int j = i + 1; j < cartList.length; j++)
                {
                    if (cartList[j] != null)
                    {
                        String nextProductName = cartList[j].getName();
                        if (nextProductName.equals(cartProductName))
                        {
                            duplicatedProductNumber++;
                            cartList[i].setMinOrderQty(cartList[i].getMinOrderQty() + cartList[j].getMinOrderQty());
                            cartList[j] = null;
                        }
                    }
                }
                if (duplicatedProductNumber * cartProduct.getMinOrderQty() > cartProduct.getQtyOnHand())
                {
                    System.out.println("Product " + cartProductName + " can not be finalized because the number of the product is greater than its stock.");
                    cartList[i] = null;
                    transaction.setTotalCost(transaction.getTotalCost() - duplicatedProductNumber * cartProduct.getMinOrderQty() * cartProduct.getPrice());
                }
            }
        }
    }

    public boolean checkProdNameLength(String name)
    {
        boolean valid = false;
        if (name.length() < 3 || name.length() > 25)
            System.out.println("Product's name must be between 3 and 25 characters long.");
        else
            valid = true;
        return valid;
    }

    public void viewAllProducts()
    {
        prodList.printProductList();
    }

    public void display()
    {
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("Welcome to the Simple Inventory Management System");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=");
        System.out.println("");
        System.out.println("Please Select from the following options:");
        System.out.println("Press 1 to Register a Product for Sale");
        System.out.println("Press 2 to Buy a Product to the Cart");
        System.out.println("Press 3 to Remove a Product from the Cart");
        System.out.println("Press 4 to View all Available Products");
        System.out.println("Press 5 to Check out");
        System.out.println("Press 6 to Get Help");
        System.out.println("Press 7 to Exit");
        System.out.println("");
        System.out.print("Please Enter your Choice:");
    }

    public ProductList getProdList()
    {
        return prodList;
    }

    public void setProdList(ProductList newProdList)
    {
        prodList = newProdList;
    }

    public SaleTransaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction(SaleTransaction newTransaction)
    {
        transaction = newTransaction;
    }


    public static void main(String[] args) {
        Sale sale1 = new Sale();
        sale1.mainSystem();
    }
}
