package com.gym.sort.algorithm;

import java.util.Arrays;

/**
 * @author yeming.gao
 * @Description: 十大排序算法
 * @date 2019/5/16 10:11
 */
public class SortAlgorithm {

    /**
     * 冒泡排序
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
        return array;
    }

    /**********************************************************************************************************/

    /**
     * 选择排序
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                int tmp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = tmp;
            }
        }
        return array;
    }

    /**********************************************************************************************************/

    /**
     * 插入排序
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int preIndex = i - 1;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }

        return array;
    }

    /**********************************************************************************************************/

    /**
     * 希尔排序
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] shellSort(int[] array) {
        int length = array.length;
        int gap = length / 2;
        while (gap > 0) {
            for (int i = gap; i < length; i++) {
                int current = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < array[preIndex]) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }
            gap /= 2;
        }
        return array;
    }

    /**********************************************************************************************************/

    /**
     * 归并排序
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序——将两段排序好的数组结合成一个排序数组
     *
     * @param left
     * @param right
     * @return
     */
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }

        return result;
    }

    /**********************************************************************************************************/

    /**
     * 快速排序
     *
     * @param array      需要排序的int数组
     * @param startIndex 开始的索引位置
     * @param endIndex   结束的索引位置
     * @return 排序后的数组
     */
    public static int[] quickSort(int[] array, int startIndex, int endIndex) {
        if (array.length < 1 || startIndex < 0 || endIndex >= array.length || startIndex > endIndex) {
            return null;
        }
        int pivotIndex = partition(array, startIndex, endIndex);
        if (pivotIndex > startIndex) {
            quickSort(array, startIndex, pivotIndex - 1);
        }
        if (pivotIndex < endIndex) {
            quickSort(array, pivotIndex + 1, endIndex);
        }
        return array;
    }

    /**
     * 快速排序算法——partition
     *
     * @param array      分区的数组
     * @param startIndex 开始的索引位置
     * @param endIndex   结束的索引位置
     * @return
     */
    private static int partition(int[] array, int startIndex, int endIndex) {
        //随机获取一个基准（需要保证在数组内）
        int pivotIndex = (int) (startIndex + Math.random() * (endIndex - startIndex + 1));
        int i = startIndex;
        while (i <= endIndex) {
            if (i <= pivotIndex) {
                if (array[i] > array[pivotIndex]) {
                    swap(array, i, pivotIndex);
                    pivotIndex = i;
                }
            } else {
                if (array[i] < array[pivotIndex]) {
                    swap(array, i, pivotIndex);
                    int tmp = pivotIndex;
                    pivotIndex = i;
                    i = tmp;
                }
            }
            i++;
        }

        return pivotIndex;
    }

    /**
     * 交换数组内两个元素
     *
     * @param array 源元素数组
     * @param i     交换位置i
     * @param j     交换位置j
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


}
