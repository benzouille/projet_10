package fr.biblioc.bibliocclientUi.beans.reservation;

import java.util.Date;

public class PreReservationBean {
    //------------------------- ATTRIBUTS -------------------------

    private int id_prereservation;

    private int id_compte;

    private String titre;

    private int numFileAttente;

    private Date datePreReservation;
    
    private int id_liste_attente;

    private Integer id_exemplaire;

    private boolean expire;

    private Date dateMail;

    private boolean mailSend;

    private Date dateMailSend;

    //------------------------- CONSTRUCTEUR -------------------------

    public PreReservationBean() {
        expire = false;
    }

    public PreReservationBean(int id_compte, Date datePreReservation, int id_liste_attente, boolean expire, Date dateMail, boolean mailSend) {
        this.id_compte = id_compte;
        this.datePreReservation = datePreReservation;
        this.id_liste_attente = id_liste_attente;
        this.expire = expire;
        this.dateMail = dateMail;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNumFileAttente() {
        return numFileAttente;
    }

    public void setNumFileAttente(int numFileAttente) {
        this.numFileAttente = numFileAttente;
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

    public void setDateMail(Date date) {
        this.dateMail = dateMail;
    }

    public Date getDateMail() {
        return dateMail;
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
        return "PreReservationBean{" +
                "id_prereservation=" + id_prereservation +
                ", id_compte=" + id_compte +
                ", id_exemplaire=" + id_exemplaire +
                ", titre='" + titre + '\'' +
                ", numFileAttente=" + numFileAttente +
                ", datePreReservation=" + datePreReservation +
                ", id_liste_attente=" + id_liste_attente +
                ", expire=" + expire +
                ", mailSend=" + mailSend +
                '}';
    }
}
