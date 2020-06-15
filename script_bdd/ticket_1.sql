CREATE SEQUENCE public.liste_attente_id_liste_attente_seq_1;

CREATE TABLE public.liste_attente (
                id_liste_attente INTEGER NOT NULL DEFAULT nextval('public.liste_attente_id_liste_attente_seq_1'),
                id_biblio INTEGER NOT NULL,
                id_livre INTEGER NOT NULL,
                CONSTRAINT liste_attente_pk PRIMARY KEY (id_liste_attente)
);


ALTER SEQUENCE public.liste_attente_id_liste_attente_seq_1 OWNED BY public.liste_attente.id_liste_attente;

CREATE SEQUENCE public.prereservation_id_prereservation_seq;

CREATE TABLE public.prereservation (
                id_prereservation INTEGER NOT NULL DEFAULT nextval('public.prereservation_id_prereservation_seq'),
                id_compte INTEGER NOT NULL,
                date DATE NOT NULL,
                id_liste_attente INTEGER,
                expire BOOLEAN NOT NULL,
                CONSTRAINT prereservation_pk PRIMARY KEY (id_prereservation)
);


ALTER SEQUENCE public.prereservation_id_prereservation_seq OWNED BY public.prereservation.id_prereservation;

ALTER TABLE public.liste_attente ADD CONSTRAINT bibliotheque_liste_attente_fk
FOREIGN KEY (id_biblio)
REFERENCES public.bibliotheque (id_biblio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.liste_attente ADD CONSTRAINT livre_liste_attente_fk
FOREIGN KEY (id_livre)
REFERENCES public.livre (id_livre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.prereservation ADD CONSTRAINT liste_attente_prereservation_fk
FOREIGN KEY (id_liste_attente)
REFERENCES public.liste_attente (id_liste_attente)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.prereservation ADD CONSTRAINT compte_prereservation_fk
FOREIGN KEY (id_compte)
REFERENCES public.compte (id_compte)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;