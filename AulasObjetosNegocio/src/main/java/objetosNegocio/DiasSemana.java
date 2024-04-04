
package objetosNegocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author t1pas
 */
@Entity
@Table(name = "dias_semana")
public class DiasSemana implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dias_semana_id")
    private Long id;

    @Column(name = "valor_binario", nullable = true)
    private int valorBinario; // 0 o 1 para representar el día de la semana

    // Relación con la entidad Evento
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiasSemana() {
    }

    public DiasSemana(int valorBinario, Evento evento) {
        this.valorBinario = valorBinario;
        this.evento = evento;
    }

    public DiasSemana(int valorBinario) {
        this.valorBinario = valorBinario;
    }
    
    public int getValorBinario() {
        return valorBinario;
    }

    public void setValorBinario(int valorBinario) {
        this.valorBinario = valorBinario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    
    
}
