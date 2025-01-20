package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Boj15662 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n = Integer.parseInt(br.readLine());
    ArrayList<LinkedList<Integer>> list = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      list.add(new LinkedList<>());
    }

    for (int i = 1; i <= n; i++) {
      String s = br.readLine();
      LinkedList<Integer> list1 = list.get(i);
      for (int j = 0; j < 8; j++) {
        list1.add(s.charAt(j) - '0');
      }
    }

    int k = Integer.parseInt(br.readLine());

    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());

      int num = Integer.parseInt(st.nextToken());
      int loc = Integer.parseInt(st.nextToken());

      LinkedList<Integer> list1 = list.get(num);
      int right = list1.get(2);
      int left = list1.get(6);

      if (loc == 1) {
        int remove = list1.remove(7);
        list1.add(0, remove);
      } else {
        int remove = list1.remove(0);
        list1.add(remove);
      }

      rotateRight(list, num + 1, loc * -1, right);
      rotateLeft(list, num - 1, loc * -1, left);
    }

    int count = 0;
    for (int i = 1; i <= n; i++) {
      LinkedList<Integer> list1 = list.get(i);
      if (list1.get(0) == 1) {
        count++;
      }
    }
    System.out.println(count);
  }

  private static void rotateLeft(ArrayList<LinkedList<Integer>> list, int num, int loc, int prev) {
    if (num < 1) {
      return;
    }

    LinkedList<Integer> list1 = list.get(num);
    if (list1.get(2) == prev) {
      return;
    }

    int temp = list1.get(6);

    if (loc == 1) {
      int remove = list1.remove(7);
      list1.add(0, remove);
    } else {
      int remove = list1.remove(0);
      list1.add(remove);
    }
    rotateLeft(list, num - 1, loc * -1, temp);
  }

  private static void rotateRight(ArrayList<LinkedList<Integer>> list, int num, int loc, int prev) {
    if (num > list.size() - 1) {
      return;
    }

    LinkedList<Integer> list1 = list.get(num);
    if (prev == list1.get(6)) {
      return;
    }

    int temp = list1.get(2);

    if (loc == 1) {
      int remove = list1.remove(7);
      list1.add(0, remove);
    } else {
      int remove = list1.remove(0);
      list1.add(remove);
    }

    rotateRight(list, num + 1, loc * -1, temp);
  }

}
