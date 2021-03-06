package com.gym.sort.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            }
            if (minIndex != i) {
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
     * @param left  需要归并的左数组
     * @param right 需要归并的右数组
     * @return 归并后的数组
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
     * @return 返回分区过后的数组
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

    /**********************************************************************************************************/

    /**
     * 堆排序
     *
     * @param array      需要排序的int数组
     * @return 排序后的数组
     */
    private static int len;

    public static int[] heapSort(int[] array) {
        len = array.length;
        if (len < 1) {
            return array;
        }
        //1.构建一个最大堆
        buildMaxHeap(array);
        //2.循环将堆首位（最大值）与末位交换，然后在重新调整最大堆
        while (len > 0) {
            swap(array, 0, len - 1);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    /**
     * 建立最大堆
     *
     * @param array 需要构建的数组
     */
    private static void buildMaxHeap(int[] array) {
        //从最后一个非叶子节点开始向上构造最大堆
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    /**
     * 调整使之成为最大堆
     *
     * @param array
     * @param i
     */
    private static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        //如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (i * 2 < len && array[i * 2] > array[maxIndex]) {
            maxIndex = i * 2;
        }
        //如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            swap(array, maxIndex, i);
            adjustHeap(array, maxIndex);
        }
    }

/**********************************************************************************************************/

    /**
     * 计数排序（仅支持整数排序）
     *
     * @param array 需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] countingSort(int[] array) {
        //首先取出array数组中最大，最小的两个元素
        int maxElement = array[0];
        int minElement = array[0];
        for (int anArray : array) {
            if (anArray > maxElement) {
                maxElement = anArray;
            }
            if (anArray < minElement) {
                minElement = anArray;
            }
        }
        //定义一个额外空间数组；长度为最大元素与最小元素之间存在的整数个数
        int[] extraArray = new int[maxElement - minElement + 1];
        Arrays.fill(extraArray, 0);
        for (int anArray : array) {
            extraArray[anArray - minElement]++;
        }
        //反向填充目标数组
        int index = 0, i = 0;
        while (index < array.length) {
            if (extraArray[i] != 0) {
                array[index] = i + minElement;
                extraArray[i]--;
                index++;
            } else {
                i++;
            }
        }
        return array;
    }

    /**********************************************************************************************************/
    /**
     * 桶排序
     * 桶排序中：无序数组有个要求,就是成员隶属于固定(有限的)的区间,如范围为0-9
     *
     * @param array      需要排序的int数组
     * @param bucketSize 桶的大小
     * @return 排序后的数组
     */
    public static ArrayList<int[]> bucketSort(int[] array, int bucketSize) {
        //首先取出array数组中最大，最小的两个元素
        int maxElement = array[0];
        int minElement = array[0];
        for (int anArray : array) {
            if (anArray > maxElement) {
                maxElement = anArray;
            }
            if (anArray < minElement) {
                minElement = anArray;
            }
        }
        //计算出桶的数量
        int bucketCount = (maxElement - minElement) / bucketSize + 1;
        //定义桶
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        //装载桶
        for (int anArray : array) {
            //根据值计算出所在的桶索引
            int bucketIndex = anArray / bucketSize;
            buckets.get(bucketIndex).add(anArray);
        }
        //对每个桶利用快排（其他方式排序也是可以的）进行排序
        ArrayList<int[]> sortedList = new ArrayList<>(bucketCount);
        for (List<Integer> bucket : buckets) {
            int[] bucketArray = new int[bucket.size()];
            for (int i = 0; i < bucket.size(); i++) {
                bucketArray[i] = bucket.get(i);
            }
            sortedList.add(quickSort(bucketArray, 0, bucketArray.length - 1));
        }

        return sortedList;
    }

    /**********************************************************************************************************/
    /**
     * 基数排序
     *
     * @param array      需要排序的int数组
     * @return 排序后的数组
     */
    public static int[] radixSort(int[] array) {
        //先获取最大数
        int maxElement = array[0];
        for (int anArray : array) {
            if (anArray > maxElement) {
                maxElement = anArray;
            }
        }
        //计算其对应的位数
        int maxDigit = 0;
        while (maxElement != 0) {
            maxElement /= 10;
            maxDigit++;
        }
        //定义长度为10的桶radix
        List<List<Integer>> radixList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            radixList.add(new ArrayList<>());
        }
        //从最低位开始取每个位组成radix数组；
        int mod = 10, div = 1;
        for(int i=0;i<maxDigit;i++){
            for(int anArray : array){
                int num = (anArray % mod) / div;
                radixList.get(num).add(anArray);
            }

            int index = 0;
            for (int j = 0; j < radixList.size(); j++) {
                for (int k = 0; k < radixList.get(j).size(); k++) {
                    array[index++] = radixList.get(j).get(k);
                }
                radixList.get(j).clear();
            }
            mod *= 10; div *= 10;
        }

        return array;
    }

}
