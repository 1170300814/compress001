package compression;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;



public class GUI {

  private JFrame frame;
  private JTextArea txtArea;
  private JTable jTable;
  static int howlong;
  double number = 0;

  public GUI() {
    initialUI();
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          GUI window = new GUI();
          window.frame.setVisible(true);
        } catch (Exception e) {
          // TODO: handle exception
          e.printStackTrace();
        }

      }
    });

  }

  public void initialUI() {

    frame = new JFrame("压缩器");
    frame.setResizable(false);
    frame.setBounds(100, 100, 1100, 930);
    Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    Dimension framesize = frame.getSize();
    frame.setLocation((screensize.width - framesize.width) / 2,
        (screensize.height - framesize.height) / 2);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(32, 178, 170));
    panel.setBounds(0, 0, 1080, 879);
    frame.getContentPane().add(panel);
    panel.setLayout(null);

    txtArea = new JTextArea();
    JScrollPane textsp = new JScrollPane();
    textsp.setViewportView(txtArea);
    textsp.setBounds(42, 77, 420, 372);
    panel.add(textsp);
    txtArea.setBackground(new Color(255, 255, 255));
    txtArea.setColumns(30);

    JTextArea txtArea24 = new JTextArea();
    JScrollPane textsp24 = new JScrollPane();
    textsp24.setViewportView(txtArea24);
    textsp24.setBounds(42, 459, 420, 372);
    panel.add(textsp24);
    txtArea24.setBackground(new Color(255, 255, 255));
    txtArea24.setColumns(30);

    final JButton NEWButton_1 = new JButton("选择压缩文件");
    NEWButton_1.setFont(new Font("宋体", Font.BOLD, 15));
    NEWButton_1.setBackground(new Color(32, 178, 170));
    NEWButton_1.setBounds(42, 30, 420, 34);
    NEWButton_1.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser chooser = new JFileChooser();
        int returnval = chooser.showOpenDialog(NEWButton_1);
        if (returnval == JFileChooser.APPROVE_OPTION) {
          String filepath = chooser.getSelectedFile().getAbsolutePath();
          System.out.println("选择了 " + filepath);
          File file = new File(filepath);
          String dongxiString = txt2String(file);
          howlong = dongxiString.length() - 3;// 总是多出3个
          // System.out.println("howlong " + howlong);
          txtArea.setText(dongxiString);

          try {

            Bianma2 bianma = new Bianma2();
            ArrayList<CharStr> prob = bianma.comprise(filepath);
            number = bianma.result1;
            txtArea24.setText(arraylist2String(prob));
            System.out.println(prob.size());
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }
      }
    });
    panel.add(NEWButton_1);

    JTextArea txtArea2 = new JTextArea();
    JScrollPane textsp2 = new JScrollPane();
    textsp2.setViewportView(txtArea2);
    textsp2.setBounds(550, 77, 420, 372);
    panel.add(textsp2);
    txtArea2.setBackground(new Color(255, 255, 255));
    txtArea2.setColumns(30);

    JTextArea txtArea23 = new JTextArea();
    JScrollPane textsp23 = new JScrollPane();
    textsp23.setViewportView(txtArea23);
    textsp23.setBounds(550, 459, 420, 372);
    panel.add(textsp23);
    txtArea23.setBackground(new Color(255, 255, 255));
    txtArea23.setColumns(30);

    final JButton NEWButton_2 = new JButton("选择解压缩文件");
    NEWButton_2.setFont(new Font("宋体", Font.BOLD, 15));
    NEWButton_2.setBackground(new Color(32, 178, 170));
    NEWButton_2.setBounds(550, 30, 420, 34);
    NEWButton_2.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser chooser = new JFileChooser();
        int returnval = chooser.showOpenDialog(NEWButton_2);
        if (returnval == JFileChooser.APPROVE_OPTION) {
          String filepath = chooser.getSelectedFile().getAbsolutePath();
          System.out.println("选择了 " + filepath);
          File file = new File(filepath);
          String dongxi = txt2String(file);
          txtArea2.setText(dongxi);

          try {
            Bianma2 call = new Bianma2();
            ArrayList<CharStr> probTable = call.takeProbabilityInput();
            Double result3 = 0.0;
            result3 = Double.parseDouble(dongxi);

            // System.out.println(result3);
            String result2 = call.decompress(probTable, number, howlong);
            txtArea23.setText(result2);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        }
      }
    });
    panel.add(NEWButton_2);
  }

  public static String arraylist2String(ArrayList arrayList) {
    String tempString = "";
    for (int i = 0; i < arrayList.size(); i++) {
      tempString += arrayList.get(i).toString() + "\n";
    }

    return tempString;

  }

  public static String txt2String(File file) {
    StringBuilder result = new StringBuilder();
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
      String s = null;
      while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
        result.append(s + System.lineSeparator());
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    result.append(' ');
    return result.toString();
  }


}

