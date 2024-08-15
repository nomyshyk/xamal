package kg.indie;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    List<String> strings = new ArrayList<>();

    public String parse(String xamalText, int level) {
        if(xamalText.isBlank() || xamalText.isEmpty()) {
            return xamalText;
        }
        //System.out.printf("%s\n", xamalText);
        StringBuilder word = new StringBuilder();
        for (int i = 0; i<xamalText.length(); i++) {
            if(xamalText.charAt(i) == '(') {
                System.out.println("My parent is " + word);
                xamalText = parse(xamalText.substring(i+1), ++level);
            }
            else if(xamalText.charAt(i) == ')') {
                //xamalText = parse(xamalText.substring(0, i));
                xamalText = parse(xamalText.substring(i+1), --level);
            }
            if (i < xamalText.length()) {
                word.append(xamalText.charAt(i));
            }
            if (xamalText.length() == 0 && !word.isEmpty()) {
                strings.add(word.toString());
            }
            System.out.printf("word = %s level=%s\n", word, level);
        }
        System.out.println(strings);
        return xamalText;
    }

//    public String parseStack(String xamalText) {
//        Stack<String> val = new Stack<>();
//        NodeTest<String> nodeTree = new NodeTest<>();
//        for (int i=0; i < xamalText.length(); i++) {
//            if(xamalText.charAt(i)=='(') {
//                NodeTest<String> nodeChild = new NodeTest<>();
//                if(nodeTree.getChildren() == null) {
//                    nodeTree.setChildren(List.of(nodeChild));
//                } else {
//                    nodeTree.getChildren().add(nodeChild);
//                }
//                xamalText.substring(i+1);
//            }
//        }
//    }
}

class NodeTest<T> {
    private NodeTest<T> parent;
    private T data;
    private List<NodeTest<T>> children;

    public NodeTest<T> getParent() {
        return parent;
    }

    public void setParent(NodeTest<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<NodeTest<T>> getChildren() {
        return children;
    }

    public void setChildren(List<NodeTest<T>> children) {
        this.children = children;
    }
}
