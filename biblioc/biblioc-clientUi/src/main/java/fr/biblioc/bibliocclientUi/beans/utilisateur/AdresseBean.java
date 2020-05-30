package fr.biblioc.bibliocclientUi.beans.utilisateur;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Bean adresse coté client contenant l'adresse complète pour les utilisateurs et pour les bibliothèques.
 */
public class AdresseBean {

    //------------------------- ATTRIBUTS -------------------------

    private int id_adresse;

    @NotNull(message = "ne peux être vide")
    //@Pattern(regexp = "[0-9]{0,3}[a-zA-Z]{0,3}", message = "3 chiffres suivi de 3 lettres maximum")
    private String num;

    @NotNull(message = "ne peux être vide")
    @Length(max = 100, message = "ne peux dépasser 100 caractères")
    private String rue;

    @Pattern(regexp = "^[0-9]{5}", message = "veuillez entrer un code postal à 5 chiffres")
    private String code_postal;

    @NotNull(message = "ne peux être vide")
    @Length(max = 100, message = "ne peux dépasser 100 caractères")
    private String commune;

    //------------------------- CONSTRUCTEUR -------------------------

    /**
     * constructeur
     */
    public AdresseBean() {
    }

    /**
     * Constructeur avec parametres
     * @param code_postal String code postal
     * @param rue String rue
     * @param num String numéro
     * @param commune String commune
     */
    public AdresseBean(@NotNull String code_postal, @NotNull String rue, @NotNull String num, @NotNull String commune) {
        this.code_postal = code_postal;
        this.rue = rue;
        this.num = num;
        this.commune = commune;
    }

    //------------------------- GETTER/SETTER -------------------------

    public int getid_adresse() {
        return id_adresse;
    }

    public void setid_adresse(int id_adresse) {
        this.id_adresse = id_adresse;
    }

    public String getcode_postal() {
        return code_postal;
    }

    public void setcode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    //------------------------- TO_STRING -------------------------

    @Override
    public String toString() {
        return "Adresse{" +
                "id_adresse=" + id_adresse +
                ", code_postal='" + code_postal + '\'' +
                ", rue='" + rue + '\'' +
                ", num='" + num + '\'' +
                ", commune='" + commune + '\'' +
                '}';
    }
}
