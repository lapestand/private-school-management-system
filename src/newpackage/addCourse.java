/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;


import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author Kemal-PC-2
 */
public class addCourse extends javax.swing.JFrame {

    /**
     * Creates new form addCourse
     */
    Connect cn = new Connect();
    Connection con=cn.ConnectDB();
    Statement statement = null;
    String Name=null;
    
    
    public void addNewCoursetoCourseTable(String courseName){
        
        con = cn.ConnectDB();
        try {
            statement = con.createStatement();
            String addCourse="INSERT INTO course (course_id, course_name) VALUES (NULL, '"+courseName+"');";
            statement.executeUpdate(addCourse);
            cn.DisconnectDB(con);
            statement=null;
            //String addCtoDep="INSERT INTO involved (department_id, course_id) VALUES ("+depId+","+currentID+");";
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String findCourseID(){
        
        con=cn.ConnectDB();
        String findCurrentIDQuery="SELECT MAX(course_id) FROM course;";
        System.out.println(findCurrentIDQuery);
        String id=null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(findCurrentIDQuery);
            while(rs.next()){
                id = rs.getString("MAX(course_id)");
            //System.out.println(currentID);
            }
            rs.close();
            cn.DisconnectDB(con);
            statement=null;
        } catch (SQLException ex) {
        //System.out.println("dsaklfja");
        Logger.getLogger(addStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public void addCoursetoDep(String currentID, boolean[] dep){
        
        for(int i=0; i<dep.length;i++){
            if(dep[i]){
                con=cn.ConnectDB();
                String iStr = String.valueOf(i+1);
                //String addCtoDep="INSERT INTO involved (department_id, course_id) VALUES ("+iStr+","+currentID+");";
                try {
                    statement = con.createStatement();
                    statement.executeUpdate("INSERT INTO involved (department_id, course_id) VALUES ("+iStr+","+currentID+");");
                    cn.DisconnectDB(con);
                    statement = null;
                } catch (SQLException ex) {
                    Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public boolean controlEnrolledIn(String courseId, String dateId){
        
        con=cn.ConnectDB();
        boolean cntEI = false;
        String controlEI = "SELECT * FROM enrolled_in WHERE enrolled_in.course_id IN("+courseId+") AND enrolled_in.date_id IN("+dateId+")";
        try {
            statement=con.createStatement();
            ResultSet rs = statement.executeQuery(controlEI);
            if(rs.next()){
                cntEI = true;
            }
            cn.DisconnectDB(con);
            statement = null;
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cntEI;
    }
    
    public void addtoEnrolledIn(String courseId, String dateId){
        String addCtoEI = "INSERT INTO enrolled_in (course_id, date_id) VALUES ("+courseId+","+dateId+");";
        con=cn.ConnectDB();
        try {
            statement=con.createStatement();
            statement.executeUpdate(addCtoEI);
            cn.DisconnectDB(con);
            statement = null;
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String findClassId(String className){
        String findClassIdQuery="SELECT class_id FROM class WHERE class_name = '"+className+"';";
        con=cn.ConnectDB();
        String classId=null;
        try {
            statement=con.createStatement();
            ResultSet rs = statement.executeQuery(findClassIdQuery);
            while(rs.next()){
                classId=rs.getString("class_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classId;
    }
    
    public boolean controlEnrolledInThe(String courseId, String classId){
        
        con=cn.ConnectDB();
        boolean cntEIT = false;
        String controlEI = "SELECT * FROM enrolled_in WHERE enrolled_in.course_id IN("+courseId+") AND enrolled_in.date_id IN("+classId+")";
        try {
            statement=con.createStatement();
            ResultSet rs = statement.executeQuery(controlEI);
            if(rs.next()){
                cntEIT = true;
            }
            cn.DisconnectDB(con);
            statement = null;
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cntEIT;
    }
    
    public void addtoEnrolledInThe(String courseId, String classId){
        
        String addCtoEI = "INSERT INTO enrolled_in_the (course_id, class_id) VALUES ("+courseId+","+classId+");";
        con=cn.ConnectDB();
        try {
            statement=con.createStatement();
            statement.executeUpdate(addCtoEI);
            cn.DisconnectDB(con);
            statement = null;
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isInCurrentTable(String cName, String cId){
        DefaultTableModel curModel = (DefaultTableModel)allDataTable.getModel();
        int rowCount = curModel.getRowCount();
        String dateId, className;
        for(int cRow=0; cRow<rowCount; cRow++){
            
            dateId = (String) curModel.getValueAt(cRow, 0);
            className = (String) curModel.getValueAt(cRow, 3);
            if(dateId.equals(cId) && className.equals(cName)){
                return true;
            }
        }
        return false;
    }
    
    public String findCourseId(String name){
        Connection con=cn.ConnectDB();
        Statement statement = null;
        String id=null;
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT course_id FROM course WHERE course_name='"+name+"';");
            while(rs.next()){
                id=rs.getString("course_id");
            }
            rs.close();
            cn.DisconnectDB(con);
            statement = null;
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public addCourse() {
        initComponents();
        DefaultTableModel model = (DefaultTableModel)dateTable.getModel();
        model.setRowCount(0);
        try {
            statement = con.createStatement();
            String showDatesQuery = "Select * FROM date;";
            ResultSet rs = statement.executeQuery(showDatesQuery);
            while(rs.next()){
                int id = rs.getInt("date_id");
                String day = rs.getString("day");
                String sTime = rs.getString("start_time");
                Object[] adding = {id,day,sTime};
                model.addRow(adding);
            }
            rs.close();
            cn.DisconnectDB(con);
            statement = null;
        } catch (SQLException ex) {
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int mainMode=0;
    String mainId;
    
    public addCourse(String name) throws SQLException{
        Name=name;
        initComponents();
        
        DefaultTableModel currentModel = (DefaultTableModel)allDataTable.getModel();
        addButton.setText("UPDATE");
        mainMode = 1;
        mainId = findCourseId(name);
        Connection con=cn.ConnectDB();
        Statement statement = null;
        
        
        boolean[] cntDep = new boolean[3];
        Arrays.fill(cntDep, Boolean.FALSE);
        
        //SELECT department.department_name FROM involved JOIN department ON involved.department_id = department.department_id WHERE involved.course_id = "+mainId+";
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT department_id FROM involved WHERE course_id="+mainId+";");
            while(rs.next()){
                cntDep[rs.getInt("department_id")-1] = true;
            }
            cn.DisconnectDB(con);
            rs.close();
            statement = null;
        } catch (SQLException ex) {
            System.out.println("250 DE HATA VAR");
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(cntDep[0]) mfCBox.setSelected(true);
        if(cntDep[1]) tmCBox.setSelected(true);
        if(cntDep[2]) tsCBox.setSelected(true);
        con=cn.ConnectDB();
        try {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT course.course_name, date.date_id, date.day,date.start_time,class.class_name,class.size FROM course JOIN enrolled_in ON course.course_id = enrolled_in.course_id JOIN date ON enrolled_in.date_id = date.date_id JOIN enrolled_in_the ON enrolled_in_the.course_id = course.course_id JOIN class ON enrolled_in_the.class_id = class.class_id where course.course_id = "+ mainId +";");
            courseName.setText(Name);
            while(rs.next()){
                String dId = rs.getString("date.date_id");
                String day = rs.getString("date.day");
                String sTime = rs.getString("date.start_time");
                String cName = rs.getString("class.class_name");
                int cSize = rs.getInt("class.size");
                Object[] adding = {dId,day,sTime,cName,cSize};
                currentModel.addRow(adding);
            }
            cn.DisconnectDB(con);
            rs.close();
            statement = null;
        } catch (SQLException ex) {
            //System.out.println("burasi");
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DefaultTableModel model = (DefaultTableModel)dateTable.getModel();
        model.setRowCount(0);
        con=cn.ConnectDB();
        try {
            statement = con.createStatement();
            String showDatesQuery = "Select * FROM date;";
            ResultSet rs = statement.executeQuery(showDatesQuery);
            while(rs.next()){
                int id = rs.getInt("date_id");
                String day = rs.getString("day");
                String sTime = rs.getString("start_time");
                Object[] adding = {id,day,sTime};
                model.addRow(adding);
            }
            rs.close();
            cn.DisconnectDB(con);
            statement = null;
        } catch (SQLException ex) {
            System.out.println("306 YA BIR BAK");
            Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        //mfCBox.setSelected();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        courseName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dateTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        classTable = new javax.swing.JTable();
        ShowEC = new javax.swing.JButton();
        mfCBox = new javax.swing.JCheckBox();
        tmCBox = new javax.swing.JCheckBox();
        tsCBox = new javax.swing.JCheckBox();
        addToListButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        allDataTable = new javax.swing.JTable();
        deleteFromListButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Name:");

        jLabel3.setText("Department:");

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        dateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "dateID", "DAY", "START TIME"
            }
        ));
        jScrollPane1.setViewportView(dateTable);

        jLabel4.setText("Dates:");

        classTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS NAME", "CLASS SIZE"
            }
        ));
        jScrollPane2.setViewportView(classTable);

        ShowEC.setText("Show Empty Classes");
        ShowEC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowECActionPerformed(evt);
            }
        });

        mfCBox.setText("MF");

        tmCBox.setText("TM");

        tsCBox.setText("TS");

        addToListButton.setText("addToList");
        addToListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListButtonActionPerformed(evt);
            }
        });

        allDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "dateID", "DAY", "START TIME", "CLASS NAME", "CLASS SIZE"
            }
        ));
        jScrollPane3.setViewportView(allDataTable);

        deleteFromListButton.setText("deleteFromList");
        deleteFromListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFromListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mfCBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tmCBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tsCBox))
                            .addComponent(courseName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(ShowEC))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(deleteFromListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(addToListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addToListButton)
                            .addComponent(deleteFromListButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(courseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(mfCBox)
                            .addComponent(tmCBox)
                            .addComponent(tsCBox))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(ShowEC))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton)
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        DefaultTableModel currentModel = (DefaultTableModel)allDataTable.getModel();
        
        int rowCount = allDataTable.getRowCount(),cRow=0,curID,cnt=0;//rowCount = all rows count, cRow = current row
        String day,sTime,cName/*Current name*/,size,dateId,name = courseName.getText();
        
        boolean[] dep = new boolean[3];
        Arrays.fill(dep, Boolean.FALSE);

        if(mfCBox.isSelected())dep[0]=true;
        if(tmCBox.isSelected())dep[1]=true;
        if(tsCBox.isSelected())dep[2]=true;
        
        if(name.equals("")){
                JOptionPane.showMessageDialog(this, "COURSE NAME CAN'T BE EMPTY!");
        }else{
            String currentID=null;
            if(mainMode==0){//INSERT
                
                addNewCoursetoCourseTable(name);
                currentID=findCourseID();
                if(currentID!=null){
                    for(boolean value: dep) if(value==true) cnt=1;
                    
                    if(cnt==0){
                        addCoursetoDep(currentID,dep);
                    }
                    while(cRow<rowCount){
                        
                        dateId =  String.valueOf(currentModel.getValueAt(cRow, 0));
                        day = String.valueOf(currentModel.getValueAt(cRow, 1));
                        sTime = String.valueOf(currentModel.getValueAt(cRow, 2));
                        cName= String.valueOf(currentModel.getValueAt(cRow, 3));
                        size = String.valueOf(currentModel.getValueAt(cRow, 4));
                        
                        boolean controlEnrolledInB = controlEnrolledIn(currentID, dateId);
                        if(!controlEnrolledInB){
                            addtoEnrolledIn(currentID, dateId);
                        }
                        String cId = findClassId(cName);
                        boolean controlEnrolledInTheB = controlEnrolledInThe(currentID, cId);
                        if(!controlEnrolledInTheB){
                            addtoEnrolledInThe(currentID,cId);
                        }
                        cRow++;
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "ID NOT FOUND!");
                }
            }else{//UPDATE
                con=cn.ConnectDB();
                try {
                    statement=con.createStatement();
                    statement.executeUpdate("UPDATE course SET course_name = '"+courseName.getText()+"' WHERE course_name ='"+Name+"';");
                    cn.DisconnectDB(con);
                    statement=null;
                } catch (SQLException ex) {
                    Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                con=cn.ConnectDB();
                try {
                    statement=con.createStatement();
                    statement.executeUpdate("DELETE FROM enrolled_in WHERE course_id="+mainId+";");
                    cn.DisconnectDB(con);
                    statement=null;
                } catch (SQLException ex) {
                    Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                con=cn.ConnectDB();
                try {
                    statement=con.createStatement();
                    statement.executeUpdate("DELETE FROM enrolled_in_the WHERE course_id="+mainId+"");
                    cn.DisconnectDB(con);
                    statement=null;
                } catch (SQLException ex) {
                    Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
                con=cn.ConnectDB();
                try {
                    statement=con.createStatement();
                    cRow=0;
                    while(cRow<rowCount){
                        
                        dateId =  String.valueOf(currentModel.getValueAt(cRow, 0));
                        day = String.valueOf(currentModel.getValueAt(cRow, 1));
                        sTime = String.valueOf(currentModel.getValueAt(cRow, 2));
                        cName= String.valueOf(currentModel.getValueAt(cRow, 3));
                        size = String.valueOf(currentModel.getValueAt(cRow, 4));
                        
                        boolean controlEnrolledInB = controlEnrolledIn(mainId, dateId);
                        if(!controlEnrolledInB){
                            addtoEnrolledIn(mainId, dateId);
                        }
                        String cId = findClassId(cName);
                        boolean controlEnrolledInTheB = controlEnrolledInThe(mainId, cId);
                        if(!controlEnrolledInTheB){
                            addtoEnrolledInThe(mainId,cId);
                        }
                        cRow++;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(this, "Successful!");
            dispose();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void ShowECActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowECActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)dateTable.getModel();
        DefaultTableModel otherModel = (DefaultTableModel)classTable.getModel();
        otherModel.setRowCount(0);
        int selected_row = dateTable.getSelectedRow();
        if(selected_row == -1)  JOptionPane.showMessageDialog(this, "Select a Row!");
        else{
            
            String id = String.valueOf(model.getValueAt(selected_row, 0));
            statement = null;
            con = cn.ConnectDB();
            try {
                statement = con.createStatement();
                String cName;
                String cSize;
                String showEmptyClassesQuery = "SELECT class_name, size FROM class WHERE class_name NOT IN(SELECT class.class_name FROM enrolled_in JOIN enrolled_in_the ON enrolled_in.course_id=enrolled_in_the.course_id JOIN class ON class.class_id=enrolled_in_the.class_id WHERE date_id IN("+id+"));";
                ResultSet rs = statement.executeQuery(showEmptyClassesQuery);
                
                while(rs.next()){
                    cName = rs.getString("class_name");
                    cSize = rs.getString("size");
                    if(!isInCurrentTable(cName,id)){
                        Object[] adding = {cName,cSize};
                        otherModel.addRow(adding);
                    }  
                }
                cn.DisconnectDB(con);
                rs.close();
            } catch (SQLException ex) {
                System.out.println("SORUN 591 DE");
                Logger.getLogger(addCourse.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ShowECActionPerformed

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel model = (DefaultTableModel)dateTable.getModel();
        DefaultTableModel otherModel = (DefaultTableModel)classTable.getModel();
        DefaultTableModel currentModel = (DefaultTableModel)allDataTable.getModel();
        
        
        int selected_date = dateTable.getSelectedRow();
        int selected_class = classTable.getSelectedRow();
        if(selected_date==-1) JOptionPane.showMessageDialog(this, "Select a Date!");
        else if(selected_class==-1) JOptionPane.showMessageDialog(this, "Select a Class!");
        else{
            boolean control = true;
            int rows = allDataTable.getRowCount(), cRow=0;
            String dId = String.valueOf(dateTable.getValueAt(selected_date,0));
            String cNm= String.valueOf(classTable.getValueAt(selected_class,0));
            
            while(cRow<rows){
                if(dId.equals(String.valueOf(allDataTable.getValueAt(cRow, 0)))){
                    if( cNm.equals(String.valueOf(allDataTable.getValueAt(cRow,3)))){
                        control=false;
                    }
                }
                cRow++;
            }
            
            if(!control){
                JOptionPane.showMessageDialog(this, "This Option Have Been Already Added!");
            }else{
                String dateId =  String.valueOf(model.getValueAt(selected_date, 0));
                //String idStr = String.valueOf(dateId);
                String day = String.valueOf(model.getValueAt(selected_date, 1));
                String sTime = String.valueOf(model.getValueAt(selected_date, 2));
                String cName = String.valueOf(otherModel.getValueAt(selected_class, 0));
                String cSize = String.valueOf(otherModel.getValueAt(selected_class, 1));
                //String cSizeStr = String.valueOf(cSize);
                Object[] adding = {dateId,day,sTime,cName,cSize};
                currentModel.addRow(adding);
            }
        }
        //model.setRowCount(0);
        otherModel.setRowCount(0);
    }//GEN-LAST:event_addToListButtonActionPerformed

    private void deleteFromListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFromListButtonActionPerformed
        // TODO add your handling code here:
        DefaultTableModel currentModel = (DefaultTableModel)allDataTable.getModel();
        int selected = allDataTable.getSelectedRow();
        if(selected == -1) JOptionPane.showMessageDialog(this, "Select a Row!");
        else{
            currentModel.removeRow(selected);
        }
    }//GEN-LAST:event_deleteFromListButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addCourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ShowEC;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addToListButton;
    private javax.swing.JTable allDataTable;
    private javax.swing.JTable classTable;
    private javax.swing.JTextField courseName;
    private javax.swing.JTable dateTable;
    private javax.swing.JButton deleteFromListButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JCheckBox mfCBox;
    private javax.swing.JCheckBox tmCBox;
    private javax.swing.JCheckBox tsCBox;
    // End of variables declaration//GEN-END:variables
}
