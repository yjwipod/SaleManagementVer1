public class ProductList
{

    private Product[] listOfProducts;

    public ProductList()
    {
        listOfProducts = new Product[5];

    }

    public ProductList(Product[] newListOfProducts)
    {
        listOfProducts = newListOfProducts;
    }

    public void addProduct(Product newProduct)
    {
        int numberOfProducts = 0;
        for (int i = 0; i < listOfProducts.length; i++)
        {
            if (listOfProducts[i] == null)
            {

                listOfProducts[i] = newProduct;
                break;
            }
            else
            {
                numberOfProducts++;
                if (numberOfProducts == listOfProducts.length)
                {
                    System.out.println("Product list is full. You can not add anymore.");
                }
            }
        }
    }

    public boolean isProdExists(String newProdName)
    {
        boolean exist = false;
        for (int i = 0; i < listOfProducts.length; i++)
        {
            if (listOfProducts[i] != null)
            {
                String prodName = listOfProducts[i].getName();
                if (prodName.equals(newProdName.toLowerCase()))
                {
                    System.out.println("This product exists, can not add again.");
                    exist = true;
                    break;
                }
            }
        }
        return exist;
    }

    public void printProductList()
    {
        int numberOfNull = 0;
        for (int i = 0; i < listOfProducts.length; i++)
        {
            if (listOfProducts[i] != null)
            {
                System.out.println("Product no." + (i + 1) + ": " + listOfProducts[i].getName());
                System.out.println("  Description" + ": " + listOfProducts[i].getDesc());
                System.out.println("  Quantity" + ": " + listOfProducts[i].getQtyOnHand());
                System.out.println("  Price" + ": " + listOfProducts[i].getPrice());
                System.out.println("  Min Order Quantity" + ": " + listOfProducts[i].getMinOrderQty());
                System.out.println("");
            }
            else
            {
                numberOfNull++;
                if (numberOfNull == listOfProducts.length)
                {
                    System.out.println("Product list is empty.");
                }
            }
        }
    }

    public Product[] getListOfProducts()
    {
        return listOfProducts;
    }

    public void setListOfProducts(Product[] newListOfProducts)
    {
        listOfProducts = newListOfProducts;
    }
}
