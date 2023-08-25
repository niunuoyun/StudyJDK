package com.nuo.studyjdk.algorithm;

import java.util.*;

public class Algorithm {

    public static void main(String[] args) {
        List<LinkedList<Integer>> datas = new ArrayList<>();
        Random random = new Random(100);
        for (int i=0;i<3;i++){
            LinkedList<Integer> linkedList= new LinkedList<>();
            for (int j = 0;j<random.nextInt(3)+2;j++){
                linkedList.add(random.nextInt(15));
            }
            Collections.sort(linkedList);
            datas.add(linkedList);
        }
        System.out.println(datas);
        mergeList(datas);
        System.out.println(datas.get(0));
    }

    private static List<Integer> mergeList(List<LinkedList<Integer>> datas){
        if (datas == null) return null;
        if (datas.isEmpty()) return new LinkedList<>();
        if (datas.size()==1) return datas.get(0);
        LinkedList<Integer> list1 = datas.get(0);
        int  index = list1.size()/2;
        int lastInsertIndex;
        for(int i=1;i<datas.size();i++){
            LinkedList<Integer>  data = datas.get(i);
            index = getIndex(data.get(0),list1,index);
            lastInsertIndex = index;
            for (int j = 1; j < data.size(); j++) {
                index = getIndex(data.get(j),list1,index);
            }
            index =lastInsertIndex;
        }

        return null;
    }

    private static int getIndex(int data,LinkedList<Integer> datas,int index){
        if (index == 0 ) {
            if (data < datas.get(0)) {
                datas.addLast( data);
                return 0;
            }else return index+1;
        }
        if ( index+1>=datas.size()){
            if (data >= datas.get(index)) {
                datas.addLast(data);
                return index;
            }else return index;

        }

        if(data <= datas.get(index) && data >= datas.get(index-1)){
            datas.add(index , data);
            return index;
        }
        if (data>=datas.get(index) && data <=datas.get(index+1)){
            datas.add(index + 1, data);
            return index+1;
        }
        if (data>datas.get(index)){
            return  getIndex(data,datas,index+1);
        }else {
            return   getIndex(data,datas,index/2);
        }
    }
}
