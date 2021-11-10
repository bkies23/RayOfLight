
package rayoflight;

// @author Brian Kies

import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;
 
// Main Frame will hold upper and lower panels
// Main Frame uses BorderLayout for panels but each panel uses GridBagLayout
public class RayOfLight extends JFrame 
{
    Color teal = new Color (32, 178, 170);
  //  int imageIndex = 1;
    String feetOrMiles;
    DecimalFormat decimalFormat1 = new DecimalFormat("#,###.00");
   
    public RayOfLight(int width, int height)
    {
        setSize(new Dimension (width, height));
        setLayout(new BorderLayout());
        setTitle("Right Triangle Surface Distances");
        UpperHalfPanel upperHalfPnl = new UpperHalfPanel( );
        LowerHalfPanel lowerHalfPnl = new LowerHalfPanel(width, height);
        
        add(upperHalfPnl, BorderLayout.NORTH);
        add(lowerHalfPnl, BorderLayout.CENTER);
    }
    
    public static void main(String[] args) 
    {
        RayOfLight rayOfLight = new RayOfLight (1500, 950); // initial dimensions
        
        rayOfLight.setLocationRelativeTo(null);
        //Set minimum size for window in order for panels to work
        rayOfLight.setMinimumSize(new Dimension (1410, 940));
        rayOfLight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rayOfLight.setVisible(true);
    }   
}
