import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

public class FrmJuego extends javax.swing.JFrame {

    int numFilas = 10;
    int numColumnas = 10;
    int numMinas = 10;

    JButton[][] botonesTablero;

    TableroBuscaMinas tableroBuscaMinas;
    private JButton nuevoJuegoButton;
    private JButton juegoButton;
    private JButton tamañoButton;


    public FrmJuego() {
        frameInit();
       // initComponents();
        juegoNuevo();
        tamañoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    void descargarControler() {
        if(botonesTablero != null) {
            for (int i = 0; i < botonesTablero.length ; i++) {
                for (int j = 0; j < botonesTablero[i].length; j++ ){
                    if (botonesTablero[i][j] != null ) {
                        getContentPane().remove(botonesTablero[i][j]);
                    }
                }
            }
        }
    }

    private void juegoNuevo(){
        descargarControler();
        cargarControles();
        crearTableroBuscaMinas();
        repaint();
    }

    private void crearTableroBuscaMinas() {
        tableroBuscaMinas = new TableroBuscaMinas(numFilas, numColumnas, numMinas);
        tableroBuscaMinas.setEventoPartidaPerdida(new Consumer<List<Casilla>>() {
            @Override
            public void accept(List<Casilla> t) {

                for(Casilla casillaConMina : t ) {
                    botonesTablero[casillaConMina.getPosFila()] [casillaConMina.getPosColumna()].setText("*");
                }
            }
        });

        tableroBuscaMinas.setEventoPartidaGanada(new Consumer<List<Casilla>>() {
            @Override
            public void accept(List<Casilla> t) {
                for(Casilla casillaConMina: t){
                    botonesTablero[casillaConMina.getPosFila()][casillaConMina.getPosColumna()].setText(":)");
                }
            }
        });


        tableroBuscaMinas.setEventoCasillaAbierta(new Consumer<Casilla>() {
            @Override
            public void accept(Casilla t) {
                botonesTablero[t.getPosFila()][t.getPosColumna()].setEnabled(false);
                botonesTablero[t.getPosFila()][t.getPosColumna()]
                        .setText(t.getNumMinasAlrededor()==0?"":t.getNumMinasAlrededor()+"");
            }
        });
        tableroBuscaMinas.imprimirTablero();


    }


    // @org.jetbrains.annotations.Contract(pure = true)
    private void cargarControles() {
        int posXReferencia = 25;
        int posYReferencia = 25;
        int anchoControl = 30;
        int altoControl = 30;


        botonesTablero = new JButton[numFilas][numColumnas];

        for (int i = 0; i < botonesTablero.length; i++) {
            for (int j = 0; j < botonesTablero[i].length; j++) {
                botonesTablero[i][j] = new JButton();
                botonesTablero[i][j].setName(i + "," + j);
                botonesTablero[i][j].setBorder(null);
                if (i == 0 && j == 0) {
                    botonesTablero[i][j].setBounds(posXReferencia, posYReferencia, anchoControl, altoControl);
                } else if (i == 0 && j != 0) {
                    botonesTablero[i][j].setBounds(botonesTablero[i][j - 1].getX() + botonesTablero[i][j - 1].getWidth(), posYReferencia, anchoControl, altoControl);
                } else {
                    botonesTablero[i][j].setBounds(
                            botonesTablero[i - 1][j].getX(), botonesTablero[i - 1][j].getY() + botonesTablero[i - 1][j].getHeight(), anchoControl, altoControl);
                }
                botonesTablero[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }

                });
                getContentPane().add(botonesTablero[i][j]);

                // botonesTablero[i][j].setBounds(j, j, WIDTH, HEIGHT);
            }
        }
    }

        public void btnClick (ActionEvent e){
            JButton btn = (JButton)e.getSource();
            String[]  coordenada = btn.getName().split(",");
            int posFila =  Integer.parseInt(coordenada[0]);
            int posColumna = Integer.parseInt((coordenada[1]));
            JOptionPane.showMessageDialog(rootPane, posFila + "," + posColumna);
            tableroBuscaMinas.seleccionarCasilla(posFila,posColumna);
        }

        private void createUIComponents () {
            // Inicializa y configura los componentes personalizados aquí
        }

        private void menuNuevoJuegoActionPerformed(java.awt.event.ActionEvent evt){
            juegoNuevo();
        }
        private void menuTamanoActionPerformed(java.awt.event.ActionEvent evt){
           int num = Integer.parseInt(JOptionPane.showInputDialog("Digita Tamaño de la Matriz, n*n"));
           this.numFilas = num;
           this.numColumnas = num;
           juegoNuevo();
        }

}
