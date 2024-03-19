import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class TableroBuscaMinas {

    Casilla[][] casillas;

    int numFila;
    int numColumnas;
    int numMinas;

    Consumer<List<Casilla>> eventoPartidaPerdida;



    public TableroBuscaMinas(int numFila, int numColumnas, int numMinas) {
        this.numFila = numFila;
        this.numColumnas = numColumnas;
        this.numMinas=numMinas;
        this.inicializarCasillas();
    }


    public void inicializarCasillas() {
        casillas = new Casilla[this.numFila][this.numColumnas];

        for(int i = 0; i < casillas.length; i++){
            for(int j = 0; j < casillas[i].length; j++){
                casillas[i][j] =  new Casilla(i, j);
            }
        }
        generarMinas();
    }


    private void generarMinas(){
        int minasGeneradas=0;
        while(minasGeneradas!=numMinas){
            int posTmpFila=(int)(Math.random()*casillas.length);
            int posTmpColumna=(int)(Math.random()*casillas[0].length);
            if (!casillas[posTmpFila][posTmpColumna].isMina()){
                casillas[posTmpFila][posTmpColumna].setMina(true);
                minasGeneradas++;
            }
        }
        actualizarNumeroMinasAlrededor();
    }


    private void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].isMina()?"*":"0");
            }
            System.out.println("");
        }
    }

    private void imprimirPistas() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].getNumMinasAlrededor());
            }
            System.out.println("");
        }
    }

    private void actualizarNumeroMinasAlrededor() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].isMina()) {
                    List<Casilla> casillasAlrededor = obtenerCasillasAlrededor( i, j);
                    casillasAlrededor.forEach((c) -> c.incrementarNumeroMinasAlrededor()) ;
                }
            }
        }
    }

    private List<Casilla> obtenerCasillasAlrededor(int posFila, int posColumna) {
        List<Casilla> listaCasillas = new LinkedList<>();
        for (int i = 0; i < 8; i++){
            int tmpPosFila = posFila;
            int tmpPosColumna = posColumna;
            switch(i) {
                case 0: tmpPosFila --; break; // ARRIBA
                case 1: tmpPosFila --; break; // ARRIBA DERECHA
                case 2: tmpPosColumna ++; break; // DERECHA
                case 3: tmpPosColumna ++; tmpPosFila ++; break; // DERECHA ABAJO
                case 4: tmpPosFila ++; break; // ABAJO
                case 5: tmpPosFila ++; tmpPosColumna --; break; // ABAJO IZQUIERDA
                case 6: tmpPosColumna --; break; // IZQUIERDA
                case 7: tmpPosFila --; tmpPosColumna --; break; // IZQUIERDA ARRIBA
            }

            if( tmpPosFila >= 0 && tmpPosFila < this.casillas.length && tmpPosColumna >= 0 && tmpPosColumna < this.casillas[0].length) {
                listaCasillas.add(this.casillas[tmpPosFila] [tmpPosColumna]);
            }

        }
        return listaCasillas;
    }

    public void seleccionarCasilla(int posFila, int posColumna){
        if (this.casillas[posFila][posColumna].isMina()) {
            List<Casilla> casillasConMinas = new LinkedList<>();
            for (int i = 0; i < casillas.length; i++) {
                for (int j = 0; j < casillas[i].length; j++) {
                    if (casillas[i][j].isMina()) {
                        casillasConMinas.add(casillas[i][j]);
                    }
                }
            }

            eventoPartidaPerdida.accept(casillasConMinas);

        }
    }

    public void setEventoPartidaPerdida(Consumer<List<Casilla>> eventoPartidaPerdida) {
        this.eventoPartidaPerdida = eventoPartidaPerdida;;
    }

    public static void main(String[] args) {
        TableroBuscaMinas tablero = new  TableroBuscaMinas(5, 5, 5);
        //System.out.println("---");
        tablero.imprimirTablero();
        System.out.println("--");
        tablero.imprimirPistas();
    }
}
