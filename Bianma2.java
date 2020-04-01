package compression;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bianma2 {
  BufferedWriter writer = new BufferedWriter(new FileWriter("CODE.txt"));
  double lower;
  double higher;
  static double result1;

  public Bianma2() throws IOException {}


  public ArrayList<CharStr> comprise(String path) throws IOException {

    Bianma2 call = new Bianma2();
    File file = new File(path);
    Scanner input = new Scanner(file);
    String seq;

    String result2;
    double result3;
    ArrayList<CharStr> probTable;

    probTable = call.takeProbabilityInput();
    seq = input.next();
    result1 = call.compress(seq, probTable);
    result2 = call.decompress(probTable, result1, seq.length());
    call.writer.write(result1 + "");
    call.writer.newLine();
    call.writer.close();

    input.close();
    return probTable;
  }

  public ArrayList<CharStr> takeProbabilityInput() throws FileNotFoundException {
    String path = "src\\test_1.txt";
    File file = new File(path);
    Scanner input = new Scanner(file);
    ArrayList<CharStr> chars = new ArrayList<>();
    double prob;
    String word = null;
    int count = 0;


    while (input.hasNext()) {
      CharStr charStr = new CharStr();
      word = input.next();
      prob = input.nextDouble();
      chars.add(charStr);
      charStr.setelement(word);

      if (count == 0) {
        charStr.setLow(0);
        charStr.setHigh(prob);
        charStr.setRange(charStr.getHigh() - charStr.getLow());
      } else {
        charStr.setelement(word);
        charStr.setLow(chars.get(count - 1).getHigh());
        charStr.setHigh(chars.get(count - 1).getHigh() + prob);
        charStr.setRange(charStr.getHigh() - charStr.getLow());
      }
      count++;
    }
    input.close();
    return chars;
  }

  private double compress(String seq, ArrayList<CharStr> probTable) throws IOException {
    double low = 0;
    double high = 1;
    double range = 1;
    String tmp;
    CharStr element;
    for (int i = 0; i < seq.length(); i++) {
      tmp = String.valueOf(seq.charAt(i));
      element = getCharStr(probTable, tmp);
      high = low + (range * element.getHigh());
      low = low + (range * element.getLow());
      range = high - low;
    }

    lower = low;
    higher = high;
    return (Math.random() * ((high - low))) + low;
  }

  public String decompress(ArrayList<CharStr> probTable, double code, int howlong) {
    String output = "";
    while (output.length() != howlong) {
      for (int i = 0; i < probTable.size(); i++) {
        if ((code < probTable.get(i).getHigh()) && (code > probTable.get(i).getLow())) {
          output += probTable.get(i).getelement();
          code = (code - probTable.get(i).getLow()) / (probTable.get(i).getRange());
          break;
        }
      }
    }
    return output;
  }

  private CharStr getCharStr(ArrayList<CharStr> probTable, String s) {
    for (int i = 0; i < probTable.size(); i++) {
      if (s.equals(probTable.get(i).getelement()))
        return probTable.get(i);
    }
    return null;
  }

}
