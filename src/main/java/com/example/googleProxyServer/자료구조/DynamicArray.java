package com.example.googleProxyServer.자료구조;

public class DynamicArray {
    private int arr[];
    private int tail = 0;

    public DynamicArray() {
        arr = new int[20];
    }

    // 삽입(c)
    public void add(int data) {
        // 꽉 차지 않았으면
        if(tail < arr.length) {
            arr[tail++] = data;
            // 꽉 찼으면
            if(tail == arr.length) {
                // 배열 확장
                expandArray(this.arr);
            }
        }
    }


    // 배열 확장
    public void expandArray(int arr[]) {
        int newArr[] = new int[arr.length * 2];
        for(int i=0;i<arr.length;i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }


    // 배열 전체 출력
    public void printArray() {
        for(int i=0;i<tail;i++) {
            System.out.print(this.arr[i] + " ");
        }
    }


    // 조회(r)
    public int get(int index) {
        if(index < 0 || index >= tail) {
            throw new IllegalArgumentException();
        }
        return arr[index];
    }


    // 변경(u)
    public void update(int index, int newData) {
        arr[index] = newData;
    }


    // 삭제(d) -> 삭제 시에 전부 앞으로 당기는 식으로 구현
    public void delete(int index) {
        if(index < 0 || index >= arr.length) {
            throw new IllegalArgumentException();
        }
        for(int i=index; i < tail - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--tail] = 0;
    }
}