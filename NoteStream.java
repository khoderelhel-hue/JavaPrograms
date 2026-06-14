import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;


public class NotDefteri {
    public static void main(String[] args) {
        JFrame screen = new JFrame();
        screen.setTitle("My Notes");
        screen.setSize(500, 400);
        screen.setLocationRelativeTo(null);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screen.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // EKSİK OLAN SATIR BUYDU: Yazı alanını ekranın merkezine ekliyoruz
        screen.add(scrollPane, BorderLayout.CENTER);

        JButton clearButton = new JButton("Clear");
        JButton SaveButton = new JButton("Save");
        JButton showButton = new JButton("Write the file");

        JPanel ButtonPanel = new JPanel();
        ButtonPanel.add(clearButton);
        ButtonPanel.add(SaveButton);
        ButtonPanel.add(showButton);

        screen.add(ButtonPanel, BorderLayout.SOUTH);

        clearButton.addActionListener(e->{
            textArea.setText("");
        });

        SaveButton.addActionListener(e->{
            try{
                FileWriter fileWriter = new FileWriter("myNotes.txt");

                fileWriter.write(textArea.getText());
                fileWriter.close();

                System.out.println("Notes have been saved");
            } catch (IOException Hata){
                System.out.println("the file couldnt save: " + Hata.getMessage());
            }
        });
        showButton.addActionListener(e->{
            try{
                FileReader fileReader = new FileReader("myNotes.txt");
                textArea.read(fileReader,null);
                fileReader.close();
                System.out.println("Notes have been successfully loaded from the file!");
            }catch (IOException Hata){
                System.out.println("the file couldnt read: " + Hata.getMessage());
            }
        });

        screen.setVisible(true);
    }
}
