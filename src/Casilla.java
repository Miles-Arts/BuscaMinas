public class Casilla {

    int posfila;
    int posColumna;
    boolean mina;

    public Casilla(int posfila, int posColumna) {
        this.posfila = posfila;
        this.posColumna = posColumna;
    }

    public int getPosfila() {
        return posfila;
    }

    public void setPosfila(int posfila) {
        this.posfila = posfila;
    }

    public int getPosColumna() {
        return posColumna;
    }

    public void setPosColumna(int posColumna) {
        this.posColumna = posColumna;
    }

    public boolean isMina() {
        return mina;
    }

    public void setMina(boolean mina) {
        this.mina = mina;
    }
}
