import java.util.stream.Stream;

/**
 * @author : xiaoyureed
 * 2020/10/22
 */
public class StreamDebug {

    public static void main(String[] args) {
        Stream.of(10, 20, 30, 40, 50)
                .map(i -> i * 100)
                .filter(i -> i > 2000)
                .forEach(System.out::println);
    }
}
