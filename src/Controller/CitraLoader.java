/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CitraKeabuan;
import Model.CitraWarna;
import Model.Data;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author M.Hakim Amransyah
 */
public class CitraLoader extends SwingWorker {
    
    private File file;
    private JProgressBar progress;
    private ArrayList<Data> kumpulan_data;
    private JList list_image;
    private JLabel label_jumlah_data;
    private Prapengolahan pra_proses = new Prapengolahan();
    
    public CitraLoader(File f, JProgressBar p,JLabel label_jumlah_data){
            this.file = f;
            this.progress = p;
            this.label_jumlah_data = label_jumlah_data;
        }
        
    public void setData(ArrayList<Data> data){
        this.kumpulan_data = data;
    }

    public void setPanel_image(JList list_image){
        this.list_image = list_image;
    }
    
    @Override
    protected Object doInBackground() throws Exception {
         Data data;
            CitraKeabuan citra;
            int index = 0;
            this.progress.setVisible(true);
            this.progress.setMaximum(this.file.listFiles().length-1);
            for(File f : file.listFiles()){
                this.progress.setValue(index);
                data = new Data();
                data.setPlat_nomor(f.getName().split("\\.")[0]);
                citra = pra_proses.doBinerisasi(pra_proses.doInvers(pra_proses
                        .doGrayScale(new CitraWarna(ImageIO
                                .read(f.getAbsoluteFile())))), f.getName());
                ProfileProjection projector = new ProfileProjection(citra, f.getName());
                citra = projector.getProjectedImage();
                this.progress.setString((index/100*this.file.listFiles().length)+"%");
                data.setCitra(citra);
                kumpulan_data.add(data);
                index++;
            }     
            return null;
    }
    
     @Override
        protected void done(){
//          JOptionPane.showMessageDialog(null,"Prapengolahan citra selesai"," Selesai!!",JOptionPane.INFORMATION_MESSAGE);
          this.label_jumlah_data.setText(this.kumpulan_data.size()+"");
          DefaultListModel list_model = new DefaultListModel();
          int no = 0;
          ImageIcon icon,new_icon;
          Image img;
          for(Data data : this.kumpulan_data){
               icon = new ImageIcon(data.getCitra().getImg());
               img = icon.getImage().getScaledInstance(340, 200,java.awt.Image.SCALE_SMOOTH);
               new_icon = new ImageIcon(img);
               list_model.add(no, new_icon);
               no++;
          }
          this.list_image.setModel(list_model);
          this.list_image.setVisibleRowCount(0);
         
        }
    
}
