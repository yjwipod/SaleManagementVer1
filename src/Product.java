public class Product
{

    private String name;
    private String desc;
    private double price;
    private int qtyOnHand;
    private int minOrderQty;

    public Product()
    {
        name = "computer";
        desc = "NA";
        price = 100.0;
        qtyOnHand = 1;
        minOrderQty = 1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String newDesc)
    {
        desc = newDesc;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double newPrice)
    {
        price = newPrice;
    }

    public int getQtyOnHand()
    {
        return qtyOnHand;
    }

    public void setQtyOnHand(int newQtyOnHand)
    {
        qtyOnHand = newQtyOnHand;
    }

    public int getMinOrderQty()
    {
        return minOrderQty;
    }

    public void setMinOrderQty(int newMinOrderQty)
    {
        minOrderQty = newMinOrderQty;
    }
}
