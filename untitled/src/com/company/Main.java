package com.company;

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 1, 4, 4, 3, 5};
        Disjoint joint = new Disjoint(arr);
        joint.union(1, 6);

        for(int i = 0; i < joint.rank.length; i++){
            System.out.println(joint.rank[i]);
        }

        System.out.println("...............................");

        for(int i = 0; i < joint.parents.length; i++){
            System.out.println(joint.parents[i]);
        }
    }
}

class Disjoint{
    int[] rank;
    int[] parents;

    public Disjoint(int[] arr){
        parents = arr;
        rank = new int[parents.length];
    }

//    public void makeSet(int i){
//        parents[i] = i;
//        rank[i] = 0;
//    }

    public int find(int i){
        if(parents[i] != i){
            parents[i] = find(parents[i]);
        }
        return parents[i];
    }

    public void union(int i, int j){
        int pi = find(i);
        int pj = find(j);

        if(rank[pi] > rank[pj]){
            parents[pj] = i;
        }
        else{
            parents[pj] = i;
            if(rank[pi] == rank[pj]){
                rank[pi]++;
            }
        }
    }
}