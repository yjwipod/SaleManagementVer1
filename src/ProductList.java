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

    public void printProductList()
    {
        int numberOfNull = 0;
        for (int i = 0; i < listOfProducts.length; i++)
        {
            if (listOfProducts[i] != null)
                System.out.println("Product " + i + ": " + listOfProducts[i].getName());
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
