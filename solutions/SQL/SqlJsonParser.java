package SQL;



import java.util.List;
import java.util.Map;

public class SqlJsonParser {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("input err!please input");
        }
        String sqlJson = args[0];
    }
}

class Input {
    // Map<表名, List<列名>>
    Map<String, List<String>> headers;
    // Map<表名, List<POJO类>>
    Map<String, List<Object>> rows;
}
