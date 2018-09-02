import java.util.Random;

public class RandomNumberGenerator
{
    private int minimumValue;
    private int maximumValue;

    public RandomNumberGenerator()
    {
        minimumValue = 0;
        maximumValue = 0;
    }

    public RandomNumberGenerator(int newMinVal, int newMaxVal)
    {
        minimumValue = newMinVal;
        maximumValue = newMaxVal;
    }

    public int getRandomNumber()
    {
        Random generator = new Random();
        int randomNumber = generator.nextInt(maximumValue - minimumValue + 1) + minimumValue;
        return randomNumber;
    }

    public int getMinimumValue()
    {
        return minimumValue;
    }

    public void setMinimumValue(int newMinVal)
    {
        minimumValue = newMinVal;
    }

    public int getMaximumValue()
    {
        return maximumValue;
    }

    public void setMaximumValue(int newMaxVal)
    {
        maximumValue = newMaxVal;
    }
}
