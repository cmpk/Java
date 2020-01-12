package sort;

import java.util.ArrayList;
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

        // 第1キーを firstName, 第2キーを lastName で昇順で並び替え
        List<Data> sortedWith2Keys = list.parallelStream().sorted((x, y) -> {
            int i = x.getFirstName().compareTo(y.getFirstName());
            if (i != 0) {
                return i;
            }

            return x.getLastName().compareTo(y.getLastName());
        }).collect(Collectors.toList());
        System.out.println("第1キーを firstName, 第2キーを lastName で昇順で並び替え");
        sortedWith2Keys.forEach(data -> System.out.println(data));
    }

    private Main() {
    }
}
