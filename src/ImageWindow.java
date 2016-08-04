/**
 * Created by kevin on 8/4/16.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImageWindow extends JFrame {

    private String folder;
    private String file;
    //private int width;
    //private int height;
    //private int xpos;
    //private int ypos;
    private  JLabel lbl;

    public ImageWindow(String folder, String file,  int width, int height, int xpos, int ypos) {

        this.folder = folder;
        this.file = file;
        setUndecorated(true);
        setSize(width, height);
        setLocation(xpos, ypos);
        lbl = new JLabel();
        updateImage();
        add(lbl);
        setVisible(true);
        setWatchService();

    }
  private void updateImage()
  {
      BufferedImage img = null;
      try {
          img = ImageIO.read(new File(folder+"/"+file));
      }
      catch (IOException e) {
          e.printStackTrace();
      }
      Image scaledImage = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
      ImageIcon icon = new ImageIcon(scaledImage);
      lbl.setIcon(icon);
  }

  private void setWatchService()
  {
      WatchService watcher = null;
      try {
          watcher = FileSystems.getDefault().newWatchService();
          Path dir = Paths.get("/home/kevin/Pictures");
          dir.register(watcher,StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
      }
      catch (IOException e) {
          e.printStackTrace();
      }

      while(true) {
          WatchKey key = null;
          try {
              key = watcher.take();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          if(key != null) {
              for (WatchEvent<?> watchEvent : key.pollEvents()) {
                  WatchEvent.Kind<?> kind = watchEvent.kind();
                  Path changedFile = (Path) watchEvent.context();
                  if(changedFile.toString().equals(file)) {
                      updateImage();
                      setWatchService();
                  }
              }
          }
      }
  }



}
