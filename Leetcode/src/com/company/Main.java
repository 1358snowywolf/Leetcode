package com.company;

import javax.print.attribute.IntegerSyntax;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Solution155III ha = new Solution155III();
        ha.push(10);
        ha.push(7);
        System.out.println(ha.peek());
        System.out.println(ha.getMin());
        ha.push(-1);
        ha.push(3);
        ha.pop();
        System.out.print(ha.getMin());
        ha.pop();

    }
}

class Solution001 {
    Hashtable<Integer, Integer> count = new Hashtable<>();

    public int singleNumber(int[] nums){
        for(int i = 0; i < nums.length; i++){               //TO PUT EVERYTHING INTO A HASHTABLE
            if(count.containsKey(nums[i])){
                count.put(nums[i], count.get(nums[i]) + 1);
            }
            else{
                count.put(nums[i], 1);
            }
        }

        for(int key: count.keySet()){
            if(count.get(key) == 1){
                return key;
            }
        }

        return 0;
    }
}

class Solution002 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int minimum = Integer.MAX_VALUE;
        int maximum = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] > maximum){
                maximum = nums[i];
            }

            if(nums[i] < minimum){
                minimum = nums[i];
            }
        }

        if(k < (maximum - minimum)){
            return false;
        }
        else{
            return true;
        }

    }
}

class Solution002II{
    HashMap<Integer, LinkedList<Integer>> hash = new HashMap<>();

    public boolean containsNearbyDuplicate(int[] nums, int k){
        for(int i = 0; i < nums.length; i++){
            if(!hash.containsKey(nums[i])){                     //if not yet in the hashTable
                LinkedList<Integer> node = new LinkedList<>();
                node.addFirst(i);                              //The index
                node.addLast(0);                            //amount of times that have seen it
                hash.put(nums[i], node);                       //the number that we're looking at
            }
            else{
                LinkedList<Integer> node = hash.get(nums[i]);
                if(k < (i - node.getFirst())){                 //if the difference is greater than k
                    if(node.getLast() > 0){                    //if this is the second time it doesn't work
                        return false;                          //return false
                    }
                    else{
                        int num = node.removeLast();
                        node.add(num + 1);
                        hash.put(nums[i], node);
                    }
                }
            }
        }

        return true;
    }
}

class Solution002III{
    public boolean containsNearbyDuplicate(int[] nums, int k){
        for(int i = 0; i < nums.length; i++){
            for(int d = i; d < nums.length; d++){
                if(nums[i] == nums[d]){
                    if(Math.abs(i - d) > k){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

class Solution002IV{
    HashMap<Integer, Integer> hash = new HashMap<>();

    public boolean containsNearbyDuplicate(int[] nums, int k){
        for(int i = 0; i < nums.length; i++){
            if(!hash.containsKey(nums[i])){
                hash.put(nums[i], i);
            }
            else{
                if(k < Math.abs(i - hash.get(nums[i]))){
                    hash.put(nums[i], i);
                }
                else{
                    return true;
                }
            }
        }

        return false;
    }
}

class Solution003{
    public void rotate(int[] nums, int k){
        int[] result = new int[nums.length];

        if(nums.length == 0){
            result = nums;
        }
        else{
            for(int i = 0; i < nums.length; i++){
                result[(i + k)%(nums.length)] = nums[i];
            }
        }

        nums = result;
    }
}

class Solution003II{
    public void rotate(int[] nums, int k){
        for(int i = 0; i < (k%nums.length); i++){
            nums = shiftOne(nums);
        }
    }

    private int[] shiftOne(int[] arr){
        int tmp = arr[0];

        for(int i = 1; i < arr.length; i++){
            if(tmp == arr[arr.length - 1]){
                arr[0] = arr[i];
            }
            else{
                int othertmp = arr[i];
                arr[i] = tmp;
                tmp = othertmp;
            }
        }

        arr[0] = tmp;

        return arr;
    }
}

class Solution003III{
    public void rotate(int[] nums, int k){
        for(int i = 0; i < (k % nums.length); i++){
            nums = shiftOne(nums);
        }
    }

    // this is just for fun
    private int[] shiftOne(int[] arr){
        int tmp = arr[arr.length - 1];

        for(int i = arr.length - 1; i > 0; i--){
            arr[i] = arr[i - 1];
        }

        arr[0] = tmp;

        return arr;
    }
}

class Solution003IV{
    public void rotate(int[] nums, int k){
        k = k % nums.length;

        if(nums.length != 1){
            nums = swap(nums, 0, nums.length - k - 1);
            nums = swap(nums, nums.length - k, nums.length - 1);
            nums = swap(nums, 0, nums.length - 1);
        }

    }

    public int[] swap(int[] arr, int startIndex, int endIndex){
        while(startIndex < endIndex) {
            int tmp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = tmp;
            startIndex++;
            endIndex--;
        }
        return arr;
    }
}

class Solution004{
    Stack<Integer> numbers = new Stack<>();

    public int calPoints(String[] ops) {
        int sum = 0;

        for(int i = 0; i < ops.length; i++){
            if(ops[i].equals("D")){                       //double
                int duo = numbers.peek() * 2;
                sum = sum + duo;
                numbers.push(duo);
            }
            else if(ops[i].equals("C")){                   //cancel previous
                int substract = numbers.pop();
                sum = sum - substract;
            }
            else if(ops[i].equals("+")){                   //add previous two
                int first = numbers.pop();
                int second = numbers.pop();
                sum = sum + first + second;
                numbers.push(second);
                numbers.push(first);
                numbers.push(first + second);
            }
            else{                                          //Integer just add
                sum = sum + Integer.parseInt(ops[i]);
                numbers.push(Integer.parseInt(ops[i]));
            }
        }
        // just for kicks

        return sum;
    }
}

class Solution599{
    Hashtable<String, Integer> hash = new Hashtable<>();

    public String findRestaurant(String[] list1, String[] list2){
        for(int i = 0; i < list1.length; i++){
            hash.put(list1[i], i);
        }

        String result = " ";
        int smallest = Integer.MAX_VALUE;

        for(int i = 0; i < list2.length; i++){
            if(hash.containsKey(list2[i])){
                if(i + hash.get(list2[i]) < smallest){
                    result = list2[i];
                    smallest = i + hash.get(list2[i]);
                }
            }
        }

        return result;
    }
}

class Solution599II{
    Hashtable<String, Integer> hash = new Hashtable<>();

    public String[] findRestaurant(String[] list1, String[] list2){
        for(int i = 0; i < list1.length; i++){
            hash.put(list1[i], i);
        }

        List<String> result = new LinkedList<>();
        int smallest = Integer.MAX_VALUE;

        for(int i = 0; i < list2.length; i++){
            if(hash.containsKey(list2[i])){
                if(i + hash.get(list2[i]) == smallest){
                    result.add(list2[i]);
                }
                if (i + hash.get(list2[i]) < smallest){
                    result.clear();
                    smallest = i + hash.get(list2[i]);
                    result.add(list2[i]);
                }
            }
        }

        return result.toArray(new String[result.size()]);
    }
}

class Solution155{
    Stack<Integer> stack;

    public Solution155(){
        stack = new Stack<>();
    }

    public void push(int x){
        stack.push(x);
    }

    public void pop(){
        stack.pop();
    }

    public int top(){
        return stack.peek();
    }

    public int getMin(){
        int min = Integer.MAX_VALUE;
        List<Integer> numbers = new LinkedList<>();

        while(!stack.empty()){
            int pop = stack.pop();
            if(pop < min){
                min = pop;
            }
            ((LinkedList<Integer>) numbers).addFirst(pop);
        }

        for(int i = 0; i < numbers.size(); i++){
            stack.push(numbers.get(i));
        }

        return min;
    }
}

class Solution155II{
    Stack<Integer> stack;
    List<Integer> min;

    public Solution155II(){
        stack = new Stack<>();
        min = new LinkedList<>();
    }

    public void push(int x){
        stack.push(x);

        for(int i = 0; i < min.size(); i++){
            if(min.get(i) > x){
                min.add(i - 1, x);
            }
        }
    }

    public void pop(){
        int pop = stack.pop();

        min.remove(pop);
    }

    public int top(){
        return stack.peek();
    }

    public int getMin(){
        if(!stack.empty()){
            return min.get(0);
        }
        return -1;
    }
}

class Solution155III{
    Stack<Integer> stack;
    Stack<Integer> min;

    public Solution155III(){
        stack = new Stack<>();
        min = new Stack<>();
    }

    public void push(int x){
        stack.push(x);

        if(!min.empty()){
            if(x <= min.peek()){
                min.push(x);
            }
        }
        else{
            min.push(x);
        }
    }

    public void pop(){
        int pop = stack.pop();

        if(min.peek() == pop){
            min.pop();
        }
    }

    public int peek(){
        return stack.peek();
    }

    public int getMin(){
        return min.peek();
    }
}

class SolutionTest {
    public void callbyreference(int[] array) {
        if (array.length >0) {

            array[0] += 10;
        }
    }

    public void callbyvalue(int a) {
        a += 10;
    }
}