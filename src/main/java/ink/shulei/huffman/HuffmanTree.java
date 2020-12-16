package ink.shulei.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node node = createHuffmanTree(arr);
        preOrder(node);

    }
    public static void preOrder(Node node){
        if (node != null){
            node.preOrder();
        }else {
            System.out.println("cnm");
        }
    }
    public static Node createHuffmanTree(int[] arr){
        List<Node> nodes = new ArrayList<Node>();
        for (int value:arr){
            nodes.add(new Node(value));
        }
        while (nodes.size()>1){
            Collections.sort(nodes);
//            System.out.println(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);

            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);

            nodes.add(parent);

        }

        System.out.println(nodes);
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
//            System.out.println(this.left);
            this.left.preOrder();
        }
        if (this.right != null){
//            System.out.println(this.right);
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
