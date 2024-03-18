import javax.swing.*;

public class FrmJuego extends javax.swing.JFrame {

    int numFilas = 10;
    int numColumnas = 10;
    int numMinas = 20;

    JButton[][] botonesTablero;


    public FrmJuego() {
        initComponents();
        cargarControles();
    }

    private void cargarControles() {
        int posXReferencia = 25;
        int posYReferencia = 25;
        int anchoControl = 30;
        int altoControl = 30;
    }

    private void cargarControles() {
        botonesTablero = new JButton[numFilas][numColumnas];

        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                if ( i == 0 && j == 0){
                    botonesTablero[i][j].setBounds(posXReferencia, posYReferencia, anchoControl, altoControl);
                }
               // botonesTablero[i][j].setBounds(j, j, WIDTH, HEIGHT);
            }
        }
    }
}