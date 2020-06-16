package DataStructure.list;
import java.util.*;


public class 简化路径 {
    public String simplifyPath(String path) {
        String prePath = path.replaceAll("//*", "/");
        if (prePath.charAt(prePath.length() - 1) == '/') {
            prePath = prePath.substring(0, prePath.length() - 1);
        }
        LinkedList<String> stack = new LinkedList();
        int slashIndex = 0;  // 第一个/在0索引处
        while (slashIndex != prePath.length()) {
            int nextSlashIndex = prePath.indexOf("/", slashIndex + 1);
            if (nextSlashIndex == -1) {
                nextSlashIndex = prePath.length();
            }
            String dir = prePath.substring(slashIndex + 1, nextSlashIndex);
            if (".." == dir) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if ("." != dir) {
                stack.push(dir);
            }
            slashIndex = nextSlashIndex;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/");
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}


