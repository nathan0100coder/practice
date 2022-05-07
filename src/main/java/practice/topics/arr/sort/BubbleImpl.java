package practice.topics.arr.sort;










public class BubbleImpl implements IBubbleSort {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] score) {
        int length = score.length;
        for(int i = 1;i < length;i++){
            for(int j = 0;j < length - i ; j++){
                if(score[j].compareTo(score[ j + 1 ]) >0){
                    T temp = score[j];
                    score[j] = score[j + 1];
                    score[j + 1] = temp;
                }
            }
        }
        return score;
    }

}
