package SwitchBlub;

import javax.swing.SwingUtilities;

public class Main {

	 public static void main(String[] args)
	    {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                Test program = new Test("Switches and bulbs");
	                program.setSize(400,300);
	                program.setLocationRelativeTo(null);
	                program.setVisible(true);
	            }
	        });
	    }
}
