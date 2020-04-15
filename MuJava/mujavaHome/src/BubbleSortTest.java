import org.junit.Assert;
import org.junit.Test;
public class BubbleSortTest {
    @Test
    public void BubbleSort() {
        int[] result = {1,3,4,6,7,7,8};
        int[] para = {6,3,7,1,4,8,7};
        Assert.assertArrayEquals(result, BubbleSort.BubbleSort(para));
    }
}