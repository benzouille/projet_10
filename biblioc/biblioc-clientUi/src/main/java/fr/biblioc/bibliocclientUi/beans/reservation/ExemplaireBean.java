package fr.biblioc.bibliocclientUi.beans.reservation;

import fr.biblioc.bibliocclientUi.beans.bibliotheque.LivreBean;

import javax.validation.constraints.NotNull;

/**
 * Bean exemplaire côté client
 */
public class ExemplaireBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_exemplaire;

    @NotNull
    private int id_livre;

    private LivreBean livre;

    @NotNull
    private BibliothequeBean bibliotheque;

    private boolean disponible;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public ExemplaireBean() {
    }

    /**
     * Constructeur avec paramètres
     * @param id_livre id livre
     * @param bibliotheque Objet bibliothèque
     * @param disponible boolean
     */
    public ExemplaireBean(@NotNull int id_livre, @NotNull BibliothequeBean bibliotheque, boolean disponible) {
        this.id_livre = id_livre;
        this.bibliotheque = bibliotheque;
        this.disponible = disponible;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_exemplaire() {
        return id_exemplaire;
    }

    public void setid_exemplaire(int id_exemplaire) {
        this.id_exemplaire = id_exemplaire;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    public BibliothequeBean getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(BibliothequeBean bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "ExemplaireBean{" +
                "id_exemplaire=" + id_exemplaire +
                ", id_livre=" + id_livre +
                ", livre=" + livre +
                ", bibliotheque=" + bibliotheque +
                ", disponible=" + disponible +
                '}';
    }
}
