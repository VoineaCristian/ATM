package model;

public class Receipt {

    private int unLeu;
    private int cinciLei;
    private int zeceLei;
    private int cinciZeciLei;
    private int oSutaLei;

    public Receipt(int unLeu, int cinciLei, int zeceLei, int cinciZeciLei, int oSutaLei) {
        this.unLeu = unLeu;
        this.cinciLei = cinciLei;
        this.zeceLei = zeceLei;
        this.cinciZeciLei = cinciZeciLei;
        this.oSutaLei = oSutaLei;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "unLeu=" + unLeu +
                ", cinciLei=" + cinciLei +
                ", zeceLei=" + zeceLei +
                ", cinciZeciLei=" + cinciZeciLei +
                ", oSutaLei=" + oSutaLei +
                '}';
    }
}
