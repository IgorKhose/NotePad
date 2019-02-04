
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class Main {
    private final String NAME="Новая заметка";
    private JFileChooser f=new JFileChooser();
    
    
    private JTabbedPane tabs =new JTabbedPane();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
    public Main(){
    JMenuBar menu=new JMenuBar();
    
    JMenu file=new JMenu("Заметка");
    
    JMenuItem newFile=new JMenuItem("Создать заметку");
    JMenuItem openFile=new JMenuItem("Открыть заметку");
    JMenuItem saveFile=new JMenuItem("Сохранить заметку");
    file.add(newFile);
    file.add(openFile);
    file.add(saveFile);
    
    menu.add(file);
    
    
    JFrame window =new JFrame("NotePad");
    window.setSize(800, 600);
    
    window.setJMenuBar(menu);
    window.add(tabs);
    
    
    window.setResizable(false);
    window.setLocationRelativeTo(null);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window .setVisible(true);
    
    newFile.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            JTextArea text =new JTextArea();
            
            Scroll scroll = new Scroll(text,false,"");
            
            tabs.addTab(NAME,scroll);
        }
    });
    
  ///////////
    saveFile.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            Scroll text= (Scroll) tabs.getSelectedComponent();
            
            String output = text.getText();
            
            if(tabs.countComponents()!=0) {
                
              if(text.isOpened()){
                 try {
                FileOutputStream writer =new FileOutputStream(text.getPath());
                writer.write(output.getBytes());
                } catch(IOException eq){eq.printStackTrace();}
           
            }else{
                
                f.showSaveDialog(null);
            
                File file =f.getSelectedFile();
            
            try {
            
                FileOutputStream writer =new FileOutputStream(file);
                writer.write(output.getBytes());
                } catch(IOException eq){eq.printStackTrace();}
            }
         }  
        
        }
    });

  
    openFile.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
         try {   
         f.showOpenDialog(null);
         File file=f.getSelectedFile();
           
                String   input = new String (Files.readAllBytes(Paths.get(file.getAbsolutePath())));
                JTextArea text=new JTextArea(input);
                
                Scroll scroll =new Scroll(text,true,file.getAbsolutePath());
                
                tabs.add(file.getName(),scroll);
            } catch (IOException el) {el.printStackTrace();}
           
        }
    });

    }

}