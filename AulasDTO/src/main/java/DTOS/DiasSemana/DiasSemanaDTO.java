
package DTOS.DiasSemana;

/**
 *
 * @author t1pas
 */
public class DiasSemanaDTO {
    
    int valorBinario;

    public DiasSemanaDTO(int valorBinario) {
        this.valorBinario = valorBinario;
    }

    public int getValorBinario() {
        return valorBinario;
    }

    public void setValorBinario(int valorBinario) {
        this.valorBinario = valorBinario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DiasSemanaDTO{");
        sb.append("valorBinario=").append(valorBinario);
        sb.append('}');
        return sb.toString();
    }
    
    
}
