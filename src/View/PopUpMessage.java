package View;

    import javax.swing.JOptionPane;

    public class PopUpMessage
    {

        public static void infoBox(String infoMessage, String titleBar)
        {
            JOptionPane.showMessageDialog(null, infoMessage, "--->" + titleBar, JOptionPane.INFORMATION_MESSAGE);
        }
    }