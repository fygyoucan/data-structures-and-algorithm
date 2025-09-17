package com.fyg.dataStructures.study01_sparsearray;

/**
 * @author fangdou
 */
public class SparseArray {
    public static void main(String[] args) {

        // 创建原始数组
        int[][] initArray = new int[11][11];
        initArray[1][1] = 1;
        initArray[2][2] = 2;
        initArray[8][9] = 3;
        initArray[6][2] = 5;

        int[][] sparseArray = getSparseArray(initArray);
        System.out.println("原始数组为：");
        printArray(initArray);
        System.out.println("稀疏数组为：");
        printArray(sparseArray);
        System.out.println("恢复后的原始数组为：");
        printArray(getInitArrFromSparseArr(sparseArray));
    }

    /**
     * 将原始数组转换为稀疏数组
     * @param initArray 原始数组
     * @return 稀疏数组
     */
    public static int[][] getSparseArray(int[][] initArray) {
        // 1、循环原始数组，得到原数组中有多少不为0的值
        int num = 0;
        for (int i = 0; i < initArray.length; i++) {
            for (int j = 0; j < initArray[i].length; j++) {
                if (initArray[i][j] != 0) {
                    num++;
                }
            }
        }
        //System.out.printf("原始数组中有%s个不为0的值", num);

        // 2、根据不为0的个数count初始化稀疏数组的大小
        int[][] sparseArray = new int[num + 1][3];

        // 3、给稀疏数组赋值
        sparseArray[0][0] = initArray.length;
        sparseArray[0][1] = initArray[0].length;
        sparseArray[0][2] = num;

        int rowIndex = 1;
        for (int i = 0; i < initArray.length; i++) {
            for (int j = 0; j < initArray[i].length; j++) {
                if (initArray[i][j] != 0) {
                    sparseArray[rowIndex][0] = i;
                    sparseArray[rowIndex][1] = j;
                    sparseArray[rowIndex][2] = initArray[i][j];
                    rowIndex++;
                }
            }
        }
        return sparseArray;
    }

    /**
     * 从稀疏数组恢复原始数组
     * @param sparseArr 稀疏数组
     * @return 原始数组
     */
    public static int[][] getInitArrFromSparseArr(int[][] sparseArr) {
        // 1、读取稀疏数组的第一行，初始化原始数组大小
        int[][] initArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2、读取第二至最后一行数组
        for(int i = 1; i < sparseArr.length; i++) {
            initArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        return initArr;
    }

    public static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int data : row) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }
}
