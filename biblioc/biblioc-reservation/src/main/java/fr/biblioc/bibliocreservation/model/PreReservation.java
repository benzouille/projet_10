package fr.biblioc.bibliocreservation.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "prereservation")
public class PreReservation {

    //------------------------- ATTRIBUTS -------------------------

    @Id
    @GeneratedValue
    private int id_prereservation;

    private int id_compte;

    @Column(name = "date")
    private Date datePreReservation;

    @Column(name = "id_liste_attente")
//    @ManyToOne(fetch = FetchType.LAZY)
    private int id_liste_attente;

    @Column(name = "id_exemplaire")
    private Integer id_exemplaire;

    private boolean expire;

    @Column(name = "mail_send")
    private boolean mailSend;

    @Column(name = "date_mail_send")
    private Date dateMailSend;

    //------------------------- CONSTRUCTEUR -------------------------

    public PreReservation() {
        expire = false;
        mailSend = false;
    }

    public PreReservation(int id_compte, Date datePreReservation, int id_liste_attente, boolean expire, boolean mailSend) {
        this.id_compte = id_compte;
        this.datePreReservation = datePreReservation;
        this.id_liste_attente = id_liste_attente;
        this.expire = expire;
        this.mailSend = mailSend;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getId_prereservation() {
        return id_prereservation;
    }

    public void setId_prereservation(int id_prereservation) {
        this.id_prereservation = id_prereservation;
    }

    public int getId_compte() {
        return id_compte;
    }

    public void setId_compte(int id_compte) {
        this.id_compte = id_compte;
    }

    public Date getDatePreReservation() {
        return datePreReservation;
    }

    public void setDatePreReservation(Date datePreReservation) {
        this.datePreReservation = datePreReservation;
    }

    public int getId_liste_attente() {
        return id_liste_attente;
    }

    public void setId_liste_attente(int id_liste_attente) {
        this.id_liste_attente = id_liste_attente;
    }

    public int getId_exemplaire() {
        return id_exemplaire;
    }

    public void setId_exemplaire(Integer id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public boolean isMailSend() {
        return mailSend;
    }

    public void setMailSend(boolean mailSend) {
        this.mailSend = mailSend;
    }

    public Date getDateMailSend() {
        return dateMailSend;
    }

    public void setDateMailSend(Date dateMailSend) {
        this.dateMailSend = dateMailSend;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "PreReservation{" +
                "id_prereservation=" + id_prereservation +
                ", id_compte=" + id_compte +
                ", datePreReservation=" + datePreReservation +
                ", id_liste_attente=" + id_liste_attente +
                ", id_exemplaire=" + id_exemplaire +
                ", expire=" + expire +
                ", mailSend=" + mailSend +
                ", dateMailSend=" + dateMailSend +
                '}';
    }
}
