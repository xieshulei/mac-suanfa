package ink.shulei.avlTree;

import java.util.Arrays;

public class AVLTree {
    public static void main(String[] args) {
        int[] arr1 = {4,3,6,5,7,8};
        int[] arr = {10,12,8,9,7,6};
//        int[] arr = {}
        AVLTreeDemo avlTree = new AVLTreeDemo();
        for (int i = 0;i<arr.length;i++){
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("+++++++++++++++++++++");
        avlTree.midOrder();
        System.out.println("树的高度");
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().left.height());
        System.out.println(avlTree.getRoot().right.height());
    }
}

class AVLTreeDemo{

        private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node){
            if (root == null){
                root = node;
            }
            root.addNode(node);
        }
        public void midOrder(){
            if (root != null){
                root.midOrder();
            }
            if (root == null){
                System.out.println("当前二叉排序树是空的，不可以遍历");
            }
        }

        public Node search(int value){
            if (root == null){
                return null;
            }else {
                return root.search(value);
            }
        }

        public Node searchParent(int value){
            if (root == null){
                return null;
            }else {
                return root.searchParent(value);
            }
        }

        public int delRightTreeMini(Node node){
            Node target = node;
            // 循环查找左节点 找到最小值
            while (target.left!=null){
                target = target.left;
            }
            delNode(target.value);
            return target.value;
        }
        // 删除节点
        public void delNode(int value){
            if (root == null){
                return;
            }else {
                Node targetNode = search(value);
                if (targetNode == null){
                    System.out.println("mei you a");
                    return;
                }
                if (root.left == null&&root.right == null){
                    root = null;
                    return;
                }
                Node parent = searchParent(value);
                // 如果要删除的节点是叶子节点
                if (targetNode.left == null && targetNode.right == null){
                    // 判断targetNode 是父节点的左还是右
                    if (parent.left != null&&parent.left.value == value){
                        parent.left = null;
                    }else if (parent.right != null&&parent.right.value==value){
                        parent.right = null;
                    }
                }else if (targetNode.left != null&&targetNode.right!=null){

                    int miniValue =  delRightTreeMini(targetNode.right);
                    targetNode.value = miniValue;

                }else {
                    if (targetNode.left!= null){
                        if (parent.left.value == value){
                            parent.left = targetNode.left;
                        }else {
                            parent.right = targetNode.left;
                        }
                    }else {
                        if (parent.left.value == value){
                            parent.left = targetNode.right;
                        }else {
                            parent.right = targetNode.right;
                        }
                    }
                }

            }
        }

}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.height();
    }

    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.height();
    }

    public int height(){
        return Math.max(left == null?0: left.height(), right == null?0: right.height()) + 1;
    }

    // 递归形式添加节点
    public void addNode(Node node){
        if (node == null){
            return;
        }
        if (node.value < this.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.addNode(node);
            }
        }
        if (node.value > this.value){
            if (this.right == null){
                this.right = node;
            }else {
                this.right.addNode(node);
            }
        }
        if (rightHeight() - leftHeight() > 1){
            // 如果right
                // 现对右子树进行旋转
            if (right != null&&right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1){
            if (left != null&&left.rightHeight()>left.leftHeight()){
                left.leftRotate();
            }else {
                rightRotate();
            }
            return;
        }
        return;
    }

    public void midOrder(){
        if (this.left != null){
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.midOrder();
        }
    }
    // value希望删除节点的值 找到就返回该节点
    public Node search(int value){
        if (value==this.value){
            return this;
        }else if (value < this.value){
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }
    public Node searchParent(int value){
        if ((this.left != null && this.left.value == value)||(this.right
                != null && this.right.value == value)){
            return this;
        }else {
            if (value < this.value&&this.left!=null){
                return this.left.searchParent(value);
            }else if (value >= this.value&&this.right!=null){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }

    // 左旋转的方法
    private void leftRotate(){
        // 创建新的节点
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
}
