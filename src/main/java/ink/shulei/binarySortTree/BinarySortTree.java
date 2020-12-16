package ink.shulei.binarySortTree;

public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTrees binarySortTrees = new BinarySortTrees();
        for (int i = 0;i < arr.length;i++){
            binarySortTrees.add(new Node(arr[i]));
        }
//        binarySortTrees.midOrder();

        binarySortTrees.delNode(7);
        binarySortTrees.midOrder();
        // 中序遍历二叉排序树
    }
}

// 创建二叉排序树
class BinarySortTrees{
    private Node root;
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

}