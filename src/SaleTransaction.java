public class SaleTransaction
{
    private int saleCode;
    private Product[] items;
    private double totalCost;

    public SaleTransaction()
    {
        items = new Product[3];
        totalCost = 0.0;
        saleCode = 1000;
    }

    public void addProduct(Product newProduct)
    {
        int numberOfProducts = 0;
        for (int i = 0; i < items.length; i++)
        {
            if (items[i] == null)
            {

                items[i] = newProduct;
                break;
            }
            else
            {
                numberOfProducts++;
                if (numberOfProducts == items.length)
                {
                    System.out.println("Product list is full. You can not add anymore.");
                }
            }
        }
    }

    public int getSaleCode()
    {
        return saleCode;
    }

    public void setSaleCode(int newSaleCode)
    {
        saleCode = newSaleCode;
    }

    public Product[] getItems()
    {
        return items;
    }

    public void setItems(Product[] newItems)
    {
        items = newItems;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double newTotalCost)
    {
        totalCost = newTotalCost;
    }
}
