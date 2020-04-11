package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import data.Data;

public final class Main {
    public static void main(final String[] args) {
        List<Data> list = new ArrayList<Data>();
        // CHECKSTYLE:OFF ignore magic number
        list.add(new Data("田中", "次郎", 20));
        list.add(new Data("田中", "一郎", 25));
        list.add(new Data("山田", "花子", 15));
        list.add(new Data("山田", "次郎", 15));
        list.add(new Data("鈴木", "明美", 15));
        // CHECKSTYLE:ON

        System.out.println("========== 第1キーを firstName, 第2キーを lastName で昇順で並び替え");
        sortWith2Keys(list);
    }

    /**
     * 第1キーを firstName, 第2キーを lastName で昇順で並び替え
     */
    private static void sortWith2Keys(final List<Data> list) {
        // ソートにラムダ式を使用
        List<Data> sortedWith2Keys = list.parallelStream().sorted((x, y) -> {
            int i = x.getFirstName().compareTo(y.getFirstName());
            if (i != 0) {
                return i;
            }

            return x.getLastName().compareTo(y.getLastName());
        }).collect(Collectors.toList());
        System.out.println("===== ラムダ式を利用");
        sortedWith2Keys.forEach(data -> System.out.println(data));

        // ソートに Collections を使用
        Collections.sort(
            list,
            new Comparator<Data>() {

                @Override
                public int compare(final Data o1, final Data o2) {
                    int i = o1.getFirstName().compareTo(o2.getFirstName());
                    if (i != 0) {
                        return i;
                    }

                    return o1.getLastName().compareTo(o2.getLastName());
                }
            });
        System.out.println("===== Collections を使用");
        list.forEach(data -> System.out.println(data));
    }

    private Main() {
    }
}
