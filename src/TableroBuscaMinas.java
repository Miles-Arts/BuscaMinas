public class TableroBuscaMinas {

    Casilla[][] casillas;

    int numFila;
    int numColumnas;
    int numMinas;



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
    }



    private void imprimirTablero() {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].isMina()?"*":"0");
            }
            System.out.println("");
        }
    }


    public static void main(String[] args) {
        TableroBuscaMinas tablero = new  TableroBuscaMinas(6, 10, 15);
        //System.out.println("---");
        tablero.imprimirTablero();
    }
}
