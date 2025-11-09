package CryptoFront.Front.Dtos;

public class BilleteraMonedaDto {
    private Long billeteraId;
    private Long monedaId;
    private String nombreMoneda;
    private double saldo;

    public BilleteraMonedaDto() {}

    public BilleteraMonedaDto(Long billeteraId, Long monedaId, String nombreMoneda, double saldo) {
        this.billeteraId = billeteraId;
        this.monedaId = monedaId;
        this.nombreMoneda = nombreMoneda;
        this.saldo = saldo;
    }

    public Long getBilleteraId() { return billeteraId; }
    public void setBilleteraId(Long billeteraId) { this.billeteraId = billeteraId; }

    public Long getMonedaId() { return monedaId; }
    public void setMonedaId(Long monedaId) { this.monedaId = monedaId; }

    public String getNombreMoneda() { return nombreMoneda; }
    public void setNombreMoneda(String nombreMoneda) { this.nombreMoneda = nombreMoneda; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return nombreMoneda + " → Saldo: $" + String.format("%.2f", saldo);
    }
}