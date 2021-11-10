
package rayoflight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.border.Border;

public class LowerHalfPanel extends javax.swing.JPanel 
{
    // LowerHalfPanel contains JLabel for images and other
    // JLabels to display current and previous angle distance information.
    // It demonstrates the use of GridBagLayout.
    
    // User can use PREVIOUS-NEXT buttons or ComboBox to select various angles
    JButton previousBtn = new JButton ("PREVIOUS");
    JButton nextBtn = new JButton ("NEXT");
    JPanel controlPanel = new JPanel ( );
    int width, height;
    String feetOrMiles, result;
    JLabel distanceLbl, infoLbl, iconLbl, j1, j2, j3, j4, j5;
    JComboBox <String> cb;
    //Array strings will be image paths for creating ImageIcons
    String [] iconLblStrings = {"/resources/Blank.png",
                                "/resources/Infield1.png",
                                "/resources/FootballField.png",
                                "/resources/Augusta.png",
                                "/resources/NewIndy.png",
                                "/resources/KansasCityToNewYork.png",
                                "/resources/StLouisToPerth2.png",
                                "/resources/HalfwayToMoon.png",
                                "/resources/EarthToSun.png",
                                "/resources/EarthToSaturn.png",
                                "/resources/AlphaCentauri.png",
                                "/resources/AlphaCentauri.png ",
                                "/resources/AlphaCentauri.png "};
    JPanel infoPanel;
    Font myFont = new Font ("Times New Roman", Font.PLAIN, 16);
    String comboBoxItem;
    GridBagConstraints gbc;
    DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
    double [] distances; //will hold distances of opposite side for various angles
    BigDecimal bd; //have to use BigDecimal to display final angle 89.999999999999999 with fifteen decimal places
    ImageIcon [] images;
    int numOfImages = 11;
    int imageIndex = 0; // imageIndex keeps track of which image to show 
 
    //Various angles to select in ComboBox
    String angles [] = {"Select Angle", "75.0", "85.0", "89.0", "89.9", "89.9999", "89.99999",
                        "89.999999", "89.999999999", "89.9999999999", "89.999999999999999"}; 
 
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        
        width = getWidth();
        height = getHeight();
        // Components will be scaled to global width and height variables when screen is resized. 
        iconLbl.setIcon(images[imageIndex]);
    }
    
    // create array of images using iconLblStrings [] paths
    public void CreateImageLabels ( ) 
    {
        images = new ImageIcon[numOfImages];
        
        for ( int i = 0; i < numOfImages; i++)
        {
            images[i] = new ImageIcon(getClass().getResource(iconLblStrings[i]));   
        }
    }
   
    public void CreateLowerHalfPanel ( int width, int height)
    {
        setLayout(new GridBagLayout( )); 
        Color teal = new Color (32, 178, 170);
        gbc = new GridBagConstraints();
        
        controlPanel.setBackground(teal);
        controlPanel.setLayout(new FlowLayout( ));
        
        previousBtn.setPreferredSize(new Dimension (150, 30));
        previousBtn.setLocation(100, 5);
        previousBtn.setVisible(true);
        // PREVIOUS button is disabled until User selects NEXT button
        previousBtn.setEnabled(false);
        
        previousBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                imageIndex -= 1;
                String s = "Distance Along The Surface: ";
                
                cb.setSelectedIndex(imageIndex);
               
                if (imageIndex < 4)
                    feetOrMiles = " Feet";
                else
                    feetOrMiles = " Miles";
   
                double distance = distances[imageIndex];
       
                String answer = String.valueOf(decimalFormat1.format(distance));
                answer = answer.concat(feetOrMiles);
                s = s.concat(answer);
                distanceLbl.setText(s);
     
                if (imageIndex == 0) // no angle selected
                {
                    j1.setText("");
                    j2.setText("");
                    j3.setText("");
                    j4.setText("");
                    j5.setText("");
                    distanceLbl.setText("Distance Along The Surface:");  
                    repaint();
                }   
                else
                {
                    ComputeDifference(imageIndex, cb);  
                    repaint();
                }
            }
        });
       
        nextBtn.setPreferredSize(new Dimension(150, 30));
        nextBtn.setVisible(true);
        nextBtn.setEnabled(true);
        
        nextBtn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                String s = "Distance Along The Surface: ";
               
                imageIndex += 1;
                // images 1 - 3 (baseball infield - Augusta 16th hole) will be measured in feet
                // images 4 - 10 (all other images) will be measured in miles
                if (imageIndex < 4)
                    feetOrMiles = " Feet";
                else
                    feetOrMiles = " Miles";
   
                double distance = distances[imageIndex];
       
                String answer = String.valueOf(decimalFormat1.format(distance));
                answer = answer.concat(feetOrMiles);
                s = s.concat(answer);
                distanceLbl.setText(s);
     
                if (imageIndex < 1) // no angle selected
                {
                    j1.setText("");
                    j2.setText("");
                    j3.setText("");
                    j4.setText("");
                    j5.setText("");
                    distanceLbl.setText("Distance Along The Surface:");    
                }   
                else
                {
                    ComputeDifference(imageIndex, cb);
                    repaint();
                }
                
                cb.setSelectedIndex(imageIndex);
            }
        });
        
        cb = new JComboBox <> (angles);
        distances = ComputeDistances(angles, cb);
   
        cb.setModel(new javax.swing.DefaultComboBoxModel(angles));
        cb.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Angles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                                                                  new java.awt.Font("Arial Narrow", 0, 14))); 
        cb.setMaximumRowCount(11);
        cb.setBackground(teal);
        cb.setVisible(true);
        // cb constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets (20, 50, 0, 0);
       
        add(cb, gbc);
        
        distanceLbl = new JLabel ("Distance Along The Surface: ", JLabel.CENTER);
        
        distanceLbl.setPreferredSize(new Dimension(width / 4, 30));
        distanceLbl.setOpaque(true);
        distanceLbl.setBackground(teal);
        distanceLbl.setForeground(Color.black);
        distanceLbl.setFont(myFont);
        Border insideBorder = BorderFactory.createLineBorder(teal, 1);
        Border outsideBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, teal);
        distanceLbl.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        distanceLbl.setVisible(true);
        // distanceLbl constraints
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.ipadx = 50;
     
        add(distanceLbl, gbc);
        
        infoLbl = new JLabel("Difference In Distance Between Current and Previous Angles", JLabel.CENTER);
        
        infoLbl.setPreferredSize(new Dimension(width / 4, 30));
        infoLbl.setForeground(Color.black);
        infoLbl.setBackground(teal);
        infoLbl.setOpaque(true);
        infoLbl.setFont(myFont);
        insideBorder = BorderFactory.createLineBorder(teal, 1);
        outsideBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, teal);
        infoLbl.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        infoLbl.setVisible(true);
        // infoLbl constraints
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets (20, 0, 0, 50);
  
        add(infoLbl, gbc); 
        
        iconLbl = new JLabel( );
        iconLbl.setBackground(teal);
        iconLbl.setOpaque(true);
        iconLbl.setPreferredSize(new Dimension(650, 250));
        insideBorder = BorderFactory.createLineBorder(teal, 1);
        outsideBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, teal);
        iconLbl.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        iconLbl.setVisible(true);
        // Icon Label constraints
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets (0, 100, 100, 0);
        
        add(iconLbl, gbc);     
        
        infoPanel = new JPanel();
        
        // These labels will contain infomation about curret and previous angles,
        // comparing differences.
        j1 = new JLabel();
        j1.setBackground(Color.WHITE);
        j1.setFont(myFont);
        j1.setOpaque(true);
        j1.setVerticalAlignment(JLabel.BOTTOM);
        j1.setForeground(Color.red);
        j2 =  new JLabel();
        j2.setBackground(teal);
        j2.setOpaque(true);
        j2.setFont(myFont);
        j2.setVerticalAlignment(JLabel.BOTTOM);
        j2.setForeground(Color.red);
        j3 =  new JLabel();
        j3.setBackground(Color.WHITE);
        j3.setOpaque(true);
        j3.setFont(myFont);
        j3.setVerticalAlignment(JLabel.BOTTOM);
        j3.setForeground(Color.red);
        j4 =  new JLabel();
        j4.setBackground(teal);
        j4.setOpaque(true);
        j4.setFont(myFont);
        j4.setVerticalAlignment(JLabel.BOTTOM);
        j4.setForeground(Color.red); 
        j5 =  new JLabel();
        j5.setBackground(Color.WHITE);
        j5.setOpaque(true);
        j5.setFont(myFont);
        j5.setVerticalAlignment(JLabel.BOTTOM); 
        j5.setForeground(Color.red); 
        
        infoPanel.setPreferredSize(new Dimension(width / 4, height / 4));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setFont(myFont);
        infoPanel.setForeground(Color.RED);
        infoPanel.setLayout(new GridLayout (5, 1));
        insideBorder = BorderFactory.createLineBorder(teal, 1);
        outsideBorder = BorderFactory.createMatteBorder(5, 5, 5, 5, teal);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
    
        infoPanel.add(j1);
        infoPanel.add(j2);
        infoPanel.add(j3);
        infoPanel.add(j4);
        infoPanel.add(j5); 
        infoPanel.setVisible(true);
        // infoPanel constraints
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 160, 100, 0);
        
        add(infoPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        
        controlPanel.add(previousBtn);
        controlPanel.add(nextBtn);
        // controlPanel constraints
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.ipadx = 150;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 50, 50);

        add(controlPanel, gbc);
        
        // ItemListener processes angle selected in ComboBox
        ItemListener itemListener = new ItemListener() 
        {
            public void itemStateChanged(ItemEvent event) 
            {
                imageIndex = cb.getSelectedIndex();
                // disable previousBtn if at Select Angle
                boolean buttonState = (imageIndex == 0) ? false : true;
                previousBtn.setEnabled(buttonState);
                // disable nextBtn if at image 10
                buttonState = (imageIndex == cb.getItemCount() - 1) ? false : true;
                nextBtn.setEnabled(buttonState);
                
                String s = "Distance Along The Surface: ";
                
                if (imageIndex < 4)
                    feetOrMiles = " Feet";
                else
                    feetOrMiles = " Miles";
   
                double distance = distances[imageIndex];
       
                String answer = String.valueOf(decimalFormat1.format(distance));
                answer = answer.concat(feetOrMiles);
                s = s.concat(answer);
                distanceLbl.setText(s);
     
                if (imageIndex < 1) // no angle selected
                {
                    j1.setText("");
                    j2.setText("");
                    j3.setText("");
                    j4.setText("");
                    j5.setText("");
                    distanceLbl.setText("Distance Along The Surface:");    
                }   
                else
                    ComputeDifference(imageIndex, cb);    
            }        
        };
        
        cb.addItemListener(itemListener); 
    }
    
    public LowerHalfPanel(int width, int height) 
    {
        initComponents();
        setBackground(Color.blue);
        //This method does the work to create lower panel
        CreateLowerHalfPanel( width, height);
        CreateImageLabels();
    }
    
    private double AngleDistance (String angleStr )
    {
        double distance;
        double angle = 0.0, angleInRadians;
     
        // I have to manually enter this angle because Double with fifteen decimal palces rounds up to 90.0 degrees
        // which is undefined for Tangent function
        if (angleStr == "89.999999999999999")
        {
            angle = Double.valueOf(angleStr);
            // convert degrees to radians
            angleInRadians = Math.toRadians(89.999999999999999); // (angle * Math.PI) / 180
            // Trig formula solving angle's opposite side using Tangent ratio
            distance =  10 * Math.tan(angleInRadians);
        }
        else
        {
            angle = Double.valueOf(angleStr);
            // convert degrees to radians
            angleInRadians = Math.toRadians(angle); // (angle * Math.PI) / 180; 
            // Trig formula to solve for opposite side of angle using Tangent ratio
            distance =  10 * Math.tan(angleInRadians);
        }
       
        if (angle > 89.0)
        {
            // Once angle exceeds 89.0 degerees, results need to be in miles.
             return distance / 5280;
        }  
        else 
        {
            // distance in feet
            return distance;
        }
        
    }
    // This function stores all distances in array at beginning of program.
    public double [] ComputeDistances (String [] angles, JComboBox cb)
    {
        distances = new double [angles.length];
        
        for (int i = 1; i < distances.length; i++)
        {
            comboBoxItem = (String) cb.getItemAt(i);
            distances[i] = AngleDistance(comboBoxItem);
        }
        
        return distances;
    }
    // This function computes the differeneces between the current and previous angles.
    private void ComputeDifference (int selectedItem, JComboBox cb)
    {
        double angle1, angle2 = 0.0, distance1, distance2, difference;
      
        String distanceOneStr, distanceTwoStr, answer, line1Str, line2Str, line3Str, line4Str, line5Str;
        
        comboBoxItem = (String) cb.getItemAt(selectedItem);
        
        if ( selectedItem == 10)
        {
            bd = new BigDecimal(comboBoxItem);
        }
        else
           angle2 = Double.parseDouble(comboBoxItem);
            
        if (selectedItem == 1)
           angle1 = 0.0;
        else
        {
            comboBoxItem = (String) cb.getItemAt(selectedItem - 1);
            angle1 = Double.parseDouble(comboBoxItem);
        }
        
        distance2 =  distances[selectedItem];
        distance1 =  distances[selectedItem - 1];
        
        if ( angle2 == 89.9 )
            distance1 /= 5280;
       
        difference = distance2 - distance1;   
        distanceTwoStr =  decimalFormat1.format(distance2);
        distanceOneStr = decimalFormat1.format(distance1);
        
        answer = String.valueOf(decimalFormat1.format(difference));
        
        if ( selectedItem < 4 ) // from Indy Racetrack (4th item in ComboBox) forward , distance is displayed in miles. 
            feetOrMiles = " Feet";
        else
            feetOrMiles = " Miles";
            
        answer = answer.concat(feetOrMiles);
        distanceOneStr = distanceOneStr.concat(feetOrMiles);
        distanceTwoStr = distanceTwoStr.concat(feetOrMiles);
        
        if (selectedItem == 1)
        {
            line1Str = "     Previous Angle:";
            line2Str = "     Distance:"; 
            line5Str = "     Difference:";
        } 
        else
        {
            line1Str = "     Previous Angle: " + angle1;
            line2Str = "     Distance: " + distanceOneStr;
            line5Str = "     Difference: " + answer;
        }
            
        if ( selectedItem == 10)
            line3Str = "     Current Angle: " + bd;
        else
            line3Str = "     Current Angle: " + angle2;
            
        line4Str = "     Distance: " + distanceTwoStr;
        // Set JLabel's text with appropriate info 
        j1.setText(line1Str);
        j2.setText(line2Str);
        j3.setText(line3Str);
        j4.setText(line4Str);
        j5.setText(line5Str);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
