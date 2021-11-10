
package rayoflight;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class UpperHalfPanel extends JPanel
{   
    // UpperHalfPanel contains TextArea that explains program and JLabel 
    // with ImageIcon that shows ten-foot pole emitting a ray of light
    // UpperHalfPanel demonstrates GridBagLayout
    
    int width, height; // global width and height for drawing of resized screen
    JTextArea ta;
    JLabel iconLbl;
    Font myFont = new Font ("Times New Roman", Font.PLAIN, 18);
    ImageIcon imageIcon;
    
    String message = "This program attempts to solve the following problem: \n\n"
                + "A ray of light is emitted from the top of a ten-foot pole at a 75.0 degree angle towards some surface. "
                + "The pole forms a 90.0 degree angle with the surface and the ray of light is the hypotenuse of a right triangle. "
                + "Using the trigonometric tangent ratio [opposite side (line along surface) ÷ adjacent side (ten-foot pole)], the program calculates "
                + "distances for the opposite side using various angles from 75.0 to 89.999999999999999 degrees. The question is at what distance does "
                + "the the ray of light strike the surface just before it reaches 90.0 degrees and is horizontal to the surface?";
    
    GridBagConstraints gbc = new GridBagConstraints();
    Color teal = new Color (32, 178, 170);
    
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        
        width = getWidth();
        height = getHeight(); 
        
        iconLbl.setIcon(imageIcon);
    }     
     
    public UpperHalfPanel ( )
    {
        setBackground(teal);
        setLayout(new GridBagLayout()); 
       
        ta = new JTextArea();
        ta.setBackground(Color.white);
        ta.setForeground(new Color(65, 128, 128));
        ta.setFont(myFont);
        ta.setEditable(false); // Text area cannot be edited.
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        
        Border insideBorder = BorderFactory.createLineBorder(Color.WHITE, 10);
        Border outsideBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE);
        ta.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        ta.setText(message);
        ta.setVisible(true);
        // Text Area constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.ipady= 30;
        gbc.insets = new Insets(10, 10, 10, 10); // Place space between text and frame sides.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        add(ta, gbc);
        
        iconLbl = new JLabel( ); 
        imageIcon = new ImageIcon(getClass().getResource("/resources/RayOfLightCB.png"));
     
        iconLbl.setVisible(true);
    
        // Icon Label constraints
        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.CENTER;
        
        add(iconLbl, gbc);
    }
}
