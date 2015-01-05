/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listoffiles;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author KagurazakaYashi
 */
public class FileList extends Thread {
    
    private ArrayList<String> filelist = new ArrayList<String>();
    private MainWindow mw;
    private String filePath;
    private boolean b_cdir;
    private boolean b_dirname;
    
    public FileList(MainWindow mwobj, String filePatho, boolean b_cdiro, boolean b_dirnameo) {
        this.mw = mwobj;
        this.b_cdir = b_cdiro;
        this.b_dirname = b_dirnameo;
        this.filePath = filePatho;
    }

    private void list(String filePath) { //filelistData //String filePath, boolean b_cdir, boolean b_dirname
        String[] arr = null;
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                    if (b_cdir) {
                        list(file.getAbsolutePath()); //file.getAbsolutePath(),b_cdir,b_dirname
                    }
                    if (b_dirname) {
                        System.out.println(file.getName());
                        filelist.add(file.getName());
                        mw.setNum(false);
//                        mw.filelistData.addElement(file.getName());
                    }
                    //System.out.println(file.getName());
            }else{
                System.out.println(file.getName());
                filelist.add(file.getName());
                mw.setNum(false);
//                mw.filelistData.addElement(file.getName());
            } 
            mw.setNum(true);
        }
        //System.out.println(filelist);
        //return filelist;
    }
    
    public synchronized void startThread() {
        try {
            list(filePath);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "创建列表时出现了一些问题，但可能并不影响使用。", "警告：" + ex, JOptionPane.WARNING_MESSAGE);
        }
        
        mw.okList(filelist);
    }
    
    @Override
    public void run() {
        
        startThread();
    }
}
