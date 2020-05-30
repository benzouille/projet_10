package fr.biblioc.bibliocreservation.dto;

public class ReservationExpireDto {
    //------------------------- ATTRIBUTS -------------------------

    private Integer id_reservation;

    private Integer id_exemplaire;

    private String email;

    private String prenom;

    private String bibliotheque;

    private String titre;

    //------------------------- CONSTRUCTEURS -------------------------

    public ReservationExpireDto() {
    }

    //------------------------- GETTER/SETTER -------------------------

    public Integer getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Integer id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Integer getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(Integer id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(String bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "ReservationExpire{" +
                "id_reservation=" + id_reservation +
                ", id_exemplaire=" + id_exemplaire +
                ", email='" + email + '\'' +
                ", prenom='" + prenom + '\'' +
                ", bibliotheque='" + bibliotheque + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
