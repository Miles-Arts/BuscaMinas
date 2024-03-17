public class TableroBuscaMinas {

    Casilla[][] casillas;

    int numFila;
    int numColumnas;



    public TableroBuscaMinas(int numFila, int numColumnas) {
        this.numFila = numFila;
        this.numColumnas = numColumnas;
    }

    public void inicializarCasillas() {
        casillas = new Casilla[this.numFila][numColumnas];

        for(int i = 0; i < casillas.length; i++){

            for(int j = 0; j < casillas[i].length; i++){

                casillas[i][j] =  new Casilla(i,j);

            }
        }
    }


    private void generarMinas() {



    }

}
