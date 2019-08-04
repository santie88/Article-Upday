import java.util.stream.Stream;

public class flattenArray {

    public static int[] getFlattenArray(Object[] array){
        return flattenArrayMethod(array)
                .mapToInt(object -> (Integer)object)
                .toArray();
    }

    private static Stream<Object> flattenArrayMethod(Object[] array) {
        return Stream.of(array)
                .flatMap(object -> object instanceof Object[]
                        ? flattenArrayMethod((Object[])object)
                        : Stream.of(object));
    }

}
