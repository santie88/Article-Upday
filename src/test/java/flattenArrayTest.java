import org.junit.Assert;
import org.junit.Test;

public class flattenArrayTest {

    @Test
    public void testFlattenArrayCase1(){
        Object[] array = new Object[]{new Object[]{1,2,new Object[] {3}},4};

        int[] expected = {1,2,3,4};

        Assert.assertArrayEquals(expected, flattenArray.getFlattenArray(array));
    }

    @Test
    public void testFlattenArrayCase2(){
        Object[] array = new Object[]{7, new Object[]{1,2,new Object[] {3}},4};

        int[] expected = {7,1,2,3,4};

        Assert.assertArrayEquals(expected, flattenArray.getFlattenArray(array));
    }

    @Test
    public void testFlattenArrayCase3(){
        Object[] array = new Object[]{7, new Object[]{1,2,new Object[] {3}},4, new Object[]{1,2}};

        int[] expected = {7,1,2,3,4,1,2};

        Assert.assertArrayEquals(expected, flattenArray.getFlattenArray(array));
    }

}
