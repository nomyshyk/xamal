package kg.indie;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    List<String> strings = new ArrayList<>();

    public String parse(String xamalText, int level) {
        if(xamalText.isBlank() || xamalText.isEmpty()) {
            return xamalText;
        }
        //System.out.printf("%s\n", xamalText);
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < xamalText.length(); i++) {
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

    public NodeTest<String> populateNodeTree(String s, NodeTest<String> nodeTest) {
        if(s.isBlank() || s.isEmpty()) {
            return nodeTest;
        }

        String value = s.substring(0, 1);
        s = s.substring(1);

        //NodeTest nodeTest1 = nodeTest;

        //nodeTest.setParent(new NodeTest<>(nodeTest, null, null));
        //NodeTest<String> nodeTest = new NodeTest<>()

        nodeTest.setData(value);
        nodeTest.setChildren(new ArrayList<>());
        for (int i = 1; i <= Integer.parseInt(value); i++) {
            NodeTest<String> child = new NodeTest<>();
            child.setParent(nodeTest);
            nodeTest.getChildren().add(child);
            populateNodeTree(s, child);
        }

        return nodeTest;
    }
    public String getNextString(String text, List<String> boundStrings) {
        StringBuilder sb = new StringBuilder();
        outer:
        for (int i = 0; i < text.length(); i++) {
            for (String boundString : boundStrings) {
                if (i + boundString.length() > text.length()) {
                    break;
                }
                if (boundString.equals(text.substring(i, i + boundString.length()))) {
                    break outer;
                }
            }
            sb.append(text.charAt(i));
        }
        return sb.toString();
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


    @Override
    public String toString() {
        return "NodeTest{" +
                "parent=" + (parent == null ? null : parent.data) +
                ", data=" + data +
                ", children=" + children +
                '}';
    }

    public NodeTest() {
    }

    public NodeTest(NodeTest<T> parent, T data, List<NodeTest<T>> children) {
        this.parent = parent;
        this.data = data;
        this.children = children;
    }

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
        //this.parent = this;
        this.children = children;
    }
}
