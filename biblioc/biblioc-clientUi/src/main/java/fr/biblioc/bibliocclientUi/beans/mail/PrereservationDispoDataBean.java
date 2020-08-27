package fr.biblioc.bibliocclientUi.beans.mail;

/**
 * POJO des données du mail a envoyer
 */
public class PrereservationDispoDataBean {

        //------------------------- ATTRIBUTS -------------------------

        private Integer id_exemplaire;

        private String email;

        private String prenom;

        private String bibliotheque;

        private String titre;

        //------------------------- CONSTRUCTEUR -------------------------

        /**
         * Constructeur
         */
        public PrereservationDispoDataBean() {
        }

        /**
         * Constructeur avec parametres
         * @param id_exemplaire int
         * @param email string
         * @param prenom string
         * @param bibliotheque string
         * @param titre string
         */
        public PrereservationDispoDataBean(Integer id_exemplaire, String email, String prenom, String bibliotheque, String titre) {
            this.id_exemplaire = id_exemplaire;
            this.email = email;
            this.prenom = prenom;
            this.bibliotheque = bibliotheque;
            this.titre = titre;
        }

        //------------------------- GETTER/SETTER -------------------------

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
            return "PrereservationDispoDataBean{" +
                    ", id_exemplaire=" + id_exemplaire +
                    ", email='" + email + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", bibliotheque='" + bibliotheque + '\'' +
                    ", titre='" + titre + '\'' +
                    '}';
        }
    }

