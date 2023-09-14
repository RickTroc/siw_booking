package it.uniroma3.siw_booking.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Stanza {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    
    private int lettiSingoli;

    private int lettiMatrimoniali;

    @ManyToOne
    private Hotel hotel;

    @OneToMany(mappedBy = "stanza", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Pacchetto> pacchetti;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLettiSingoli() {
        return lettiSingoli;
    }

    public void setLettiSingoli(int lettiSingoli) {
        this.lettiSingoli = lettiSingoli;
    }

    public int getLettiMatrimoniali() {
        return lettiMatrimoniali;
    }

    public void setLettiMatrimoniali(int lettiMatrimoniali) {
        this.lettiMatrimoniali = lettiMatrimoniali;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public List<Pacchetto> getPacchetti() {
        return pacchetti;
    }

    public void setPacchetti(List<Pacchetto> pacchetti) {
        this.pacchetti = pacchetti;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + lettiSingoli;
        result = prime * result + lettiMatrimoniali;
        result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
        result = prime * result + ((pacchetti == null) ? 0 : pacchetti.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stanza other = (Stanza) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (lettiSingoli != other.lettiSingoli)
            return false;
        if (lettiMatrimoniali != other.lettiMatrimoniali)
            return false;
        if (hotel == null) {
            if (other.hotel != null)
                return false;
        } else if (!hotel.equals(other.hotel))
            return false;
        if (pacchetti == null) {
            if (other.pacchetti != null)
                return false;
        } else if (!pacchetti.equals(other.pacchetti))
            return false;
        return true;
    }
    

    

}
