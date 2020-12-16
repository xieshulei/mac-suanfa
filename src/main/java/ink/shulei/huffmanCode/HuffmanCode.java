package ink.shulei.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) throws IOException {
//        String srcFile = "/Users/shulei/Downloads/桌面壁纸/IMG_3326.jpg";
//        String dstFile = "/Users/shulei/Downloads/桌面壁纸/IMG_3326.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("ok_________+++++++++++++++");

        String zipFile = "/Users/shulei/Downloads/桌面壁纸/IMG_3326.zip";
        String dstFile = "/Users/shulei/Downloads/桌面壁纸/laopo.jpg";
        unZipFiled(zipFile,dstFile);
        System.out.println("解压成功");
//        String content = "i like like cao houxiaoyun";
//        byte[] contentBytes = content.getBytes();
//
//
//        System.out.println(Arrays.toString(contentBytes));
//
//        byte[] huffmanCodesBytes = hufmanZip(contentBytes);
//        System.out.println(Arrays.toString(huffmanCodesBytes));
//        byte[] sourceByte = decode(huffmanCode,huffmanCodesBytes);
//        System.out.println(new String(sourceByte));

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println(nodes);

//        Node root = createHuffmanTree(nodes);
//        System.out.println(root);
//        preOrder(root);

//        getCode(root,"",stringBuilder);
//        System.out.println(huffmanCode);

//        byte[] huffmanCodeBytes = zip(contentBytes,huffmanCode);
//        System.out.println(Arrays.toString(huffmanCodeBytes));

    }
    // 编写方法将一个文件进行压缩
    // srcFile 传入文件的路径
    // dstFile 压缩后文件放到那个路径
    public static void zipFile(String srcFile,String dstFile){
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            byte[] b = new byte[is.available()];
            is.read(b);
            // 获取到文件对应的huffman编码表
            byte[] huffmanBytes = hufmanZip(b);
            // 穿件文件输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流相关的ObjectOutPutStream


            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCode);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void unZipFiled(String zipFile,String dstFile) throws IOException {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes,huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            os.close();
            ois.close();
            is.close();

        }
    }
    private static byte[] decode(Map<Byte,String> huffmanCode,byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i<huffmanBytes.length;i++){
            Byte b = huffmanBytes[i];
            Boolean flag = (i == huffmanBytes.length - 1);

            stringBuilder.append(byteToString(!flag,b));
        }
        // 得到map对调
        Map<String,Byte> map = new HashMap<String,Byte>();
        for (Map.Entry<Byte,String> entry: huffmanCode.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0;i<stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null){
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            i+=count;
        }
        byte[] b = new byte[list.size()];
        for (int i=0;i<b.length;i++){
            b[i] = list.get(i);
        }
        return b;
//        System.out.println(stringBuilder.toString());
    }

    private static String byteToString(boolean flag,byte b){
        int temp = b;
        // 需要补高位就按位或10000000
        if (flag){
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }
    }
    // 使用一个方法将前面的方法封装起来，便于调用
    // 输入原始数组 输出huffman压缩后的数组
    private static byte[] hufmanZip(byte[] bytes){

        List<Node> nodes = getNodes(bytes);

        Node node = createHuffmanTree(nodes);

        getCode(node,"",stringBuilder);


        byte[] huffmanCodeBytes = zip(bytes,huffmanCode);

        return huffmanCodeBytes;

    }

    // 编写一个方法将一个字符串对应的byte数组压缩成，返回字符串编码压缩后的byte数组
    private static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCode){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        // 判断长度 len
        int len;
        if (stringBuilder.length()%8==0){
            len = stringBuilder.length()/8;
        }else {
            len = stringBuilder.length()/8 + 1;
        }
//        System.out.println(stringBuilder);
        // for循环取出
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0;i<stringBuilder.length();i+=8){
            String strByte;
            if (i+8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;
    }
    static Map<Byte,String> huffmanCode = new HashMap<Byte,String>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static void getCode(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){
            if (node.data == null){
                getCode(node.left, "0",stringBuilder2);
                getCode(node.right, "1",stringBuilder2);
            }else {
                huffmanCode.put(node.data, stringBuilder2.toString());
            }
        }
    }
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("cnm");
        }
    }
    // 统计
    private static List<Node> getNodes(byte[] bytes){
        ArrayList<Node> nodes = new ArrayList<Node>();

        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b:bytes){
            Integer count = counts.get(b);
            if (count == null){
                counts.put(b,1);
            }else {
                counts.put(b,count + 1);
            }
        }
        for (Map.Entry<Byte,Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null,left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}


class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // 从小到大排序
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
