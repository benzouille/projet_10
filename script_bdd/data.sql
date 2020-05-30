

INSERT INTO auteur (prenom, nom, date_naissance, date_deces) VALUES 
('Victor', 'Hugo', '1802/02/26', '1885/05/22'),
('John Ronald Reuel', 'Tolkien ', '1892/01/03', '1973/09/02'),
('Isaac', 'Asimov', '1920/01/02', '1992/04/06'),
('Franck', 'Herbert', '1920/10/08', '1986/02/11'),
('Charle', 'Baudelaire', '1821/04/09', '1867/08/31');


INSERT INTO role (role) VALUES 
('usagé'),
('bibliothecaire'),
('administrateur');


INSERT INTO genre (genre) VALUES 
('historique'),
('science fiction'),
('fantaisie'),
('policier'),
('sociologie'),
('droit'),
('économie'),
('roman'),
('biographie'),
('sciences'),
('bande dessinée'),
('manga'),
('enfant'),
('médical'),
('informatique'),
('biologie'),
('géographie'),
('langues étrangères'),
('mathématiques'),
('physique'),
('chimie'),
('phylosophie'),
('géologie'),
('astronomie'),
('ésothérisme'),
('poésie');

INSERT INTO editeur (nom) VALUES 
('Folio'),
('Pocket'),
('Gléna'),
('Hachette'),
(E'L\'Ecole des Loisirs'),
('Larousse');


INSERT INTO livre (isbn13, id_genre, titre, resume, image, annee_parution, id_editeur) VALUES
('978-2211238465', 8, 'Les misérables', E'Valjean, l\'ancien forçat devenu bourgeois et protecteur des faibles ; Fantine, l\'ouvrière écrasée par sa condition ; le couple Thénardier, figure du mal et de l\'opportunisme ; Marius, l\'étudiant idéaliste ; Gavroche, le gamin des rues impertinent qui meurt sur les barricades ; Javert, la fatalité imposée par la société sous les traits d\'un policier vengeur... Et, bien sûr, Cosette, l\'enfant victime. Voilà comment une oeuvre immense incarne son siècle en quelques destins exemplaires, figures devenues mythiques qui continuent de susciter une multitude d\'adaptations.',
 'miserable.jpg', 2019, 5),
('978-2070360536', 2, 'Le cycle de Fondation, I : Fondation',E'En ce début de treizième millénaire, l\'Empire n\'a jamais été aussi puissant, aussi étendu à travers toute la galaxie. C\'est dans sa capitale, Trantor, que l\'éminent savant Hari Seldon invente la psychohistoire, une science nouvelle permettant de prédire l\'avenir. Grâce à elle, Seldon prévoit l\'effondrement de l\'Empire d\'ici cinq siècles, suivi d\'une ère de ténèbres de trente mille ans. Réduire cette période à mille ans est peut-être possible, à condition de mener à terme son projet : la Fondation, chargée de rassembler toutes les connaissances humaines. Une entreprise visionnaire qui rencontre de nombreux et puissants détracteurs...',
 'fondation_tome_1.jpg', 2009, 1),
('978-2070360550', 2, 'Le cycle de Fondation, II : Fondation et Empire', E'Tandis que les crises qui secouent l\'Empire redoublent de violence et annoncent son effondrement définitif, la Fondation créée par le psychohistorien Hari Seldon pour sauvegarder la civilisation devient de plus en plus puissante, suscitant naturellement convoitise et visées annexionnistes. En tout premier lieu, celles de Bel Riose, jeune général qui voit dans les secrets détenus par la Fondation le moyen de monter sur le trône.C\'est alors qu\'apparaît un mystérieux et invincible conquérant, surnommé le Mulet, que le plan de Seldon n\'avait pas prévu...',
 'fondation_tome_2.jpg', 2009, 1),
('978-2070360529', 2, 'Le cycle de Fondation, III : Seconde Fondation', E'Conçue par le psychohistorien Hari Seldon pour restreindre l\'ère de chaos résultant de la décadence de l\'Empire galactique, la Fondation est désormais aux mains du Mulet, un mutant imprévisible capable de manipuler les esprits et d\'imposer sa volonté à quiconque. Avec ses pouvoirs et les immenses ressources que lui procurent la Fondation, il s\'est donné pour objectif d\'étendre sa domination aux ultimes vestiges de l\'Empire défunt.Mais déjà une nouvelle légende prend forme : il existerait une seconde Fondation, consacrée aux sciences mentales, œuvrant de façon occulte pour garantir l\'accomplissement des desseins du légendaire Hari Seldon...',
 'fondation_tome_3.jpg', 2009, 1),
('978-2070360529', 3, 'Intégrale Le Seigneur des Anneaux (Nouvelle traduction)', E'Une contrée paisible où vivent les Hobbits. Un anneau magique à la puissance infinie. Sauron, son créateur, prêt à dévaster le monde entier pour récupérer son bien. Frodon, jeune Hobbit, détenteur de l\'Anneau malgré lui. Gandalf, le Magicien, venu avertir Frodon du danger. Et voilà déjà les Cavaliers Noirs qui approchent...C\'est ainsi que tout commence en Terre du Milieu entre le Comté et Mordor. C\'est ainsi que la plus grande légende est née.',
 'seignieur_des_anneaux.jpg', 2018, 2),
('978-2266121026', 3, 'Le Silmarillion', E'Les Premiers jours du Monde étaient à peine passés quand Fëanor, le plus doué des elfes, créa les trois Silmarils. Ces bijoux renfermaient la Lumière des Deux Arbres de Valinor. Morgoth, le premier Prince de la Nuit, était encore sur la Terre du Milieu, et il fut fâché d\'apprendre que la Lumière allait se perpétuer. Alors il enleva les Silmarils, les fit sertir dans son diadème et garder dans la forteresse d\'Angband. Les elfes prirent les armes pour reprendre les joyaux et ce fut la première de toutes les guerres. Longtemps, longtemps après, lors de la Guerre de l\'Anneau, Elrond et Galadriel en parlaient encore.',
 'silmarillion.jpg', 2018, 2),
('978-2266233200', 3, '1. Dune', E'Il n\'y a pas, dans tout l\'Empire, de planète plus inhospitalière que Dune. Partout des sables à perte de vue. Une seule richesse : l\'épice de longue vie, née du désert, et que tout l\'univers convoite.Quand Leto Atréides reçoit Dune en fief, il flaire le piège. Il aura besoin des guerriers Fremen qui, réfugiés au fond du désert, se sont adaptés à une vie très dure en préservant leur liberté, leurs coutumes et leur foi. Ils rêvent du prophète qui proclamera la guerre sainte et changera le cours de l\'Histoire.Cependant les Révérendes Mères du Bene Gesserit poursuivent leur programme millénaire de sélection génétique : elles veulent créer un homme qui réunira tous les dons latents de l\'espèce. Le Messie des Fremen est-il déjà né dans l\'Empire ?',
 'dune_1.jpg', 2012, 2),
('978-2266235815', 3, '2. Le messie de dune', E'Paul Atréides a triomphé de ses ennemis. En douze ans de guerre sainte, ses Fremen ont conquis l\'univers. Il est devenu l\'empereur Muad\' Dib. Presque un dieu, puisqu\'il voit l\'avenir. Ses ennemis, il les connaît. Il sait quand et comment ils frapperont. Ils vont essayer de lui reprendre l\'épice qui donne la prescience et peut-être de percer le secret de son pouvoir. Il peut déjouer leurs plans, mais voit plus loin encore. Il sait que tous les futurs possibles mènent au désastre et est hanté par la vision de sa propre mort. Peut-être n\'y a-t-il pas d\'autre liberté pour le prescient que celle du sacrifice...',
 'dune_2.jpg', 2012, 2),
('978-2266235822', 3, '3. Les enfants de Dune', E'Sur Dune, la planète des sables, les prophéties s\'accomplissent : le désert devient jardin. Mais les vers géants se font rares et l\'Épice de prescience vient à manquer. Tout ce qui reste de l\'épopée de Muad\'Did, c\'est un empire conquis, des guerriers déchus, des prêtres tentés par la théocratie. Et les jumeaux Leto et Ghanima, qui portent en eux les souvenirs d\'innombrables générations dont, peut-être, ceux de l\'antique Abomination, redoutée par les sœurs du Bene Gesserit et prête à revenir du passé génétique pour faire basculer l\'univers dans le cauchemar. Les morts dominent les vivants. Leto devra affronter les uns et les autres en un combat sans merci dont l\'enjeu est plus que la prescience, plus que la longévité : au moins la toute-puissance, et peut-être l\'immortalité.',
 'dune_3.jpg', 2012, 2),
('978-2266235839', 3, E'4. L\'Empereur-dieu de Dune', E'Leto II Atréides, l\'Empereur-Dieu de Dune, est désormais un ver de sable à face humaine. À peu près invulnérable et immortel, il a entrevu dans l\'avenir l\'extinction de l\'espèce humaine. Pour la conjurer, il fait respecter son ordre, le Sentier d\'Or. L\'empire a connu trente-cinq siècles de paix. La Guilde et le Bene Gesserit ont les mains liées : c\'est Leto qui contrôle sur Dune les dernières réserves de l\'indispensable épice. Les Ixiens lui envoient une femme parfaite, issue d\'une éprouvette et chargée à son insu de le séduire et de le détruire. Leto sait désormais qu\'il devra peut être se sacrifier et sacrifier la femme qu\'il aime et qui réveille d\'anciens souvenirs.',
 'dune_4.jpg', 2012, 2),
('978-2266235846', 3, '5. Les hérétiques de Dune', E'Leto II, le Tyran, l\'Empereur-Dieu, est mort depuis des milliers d\'années, mais son souvenir est dans toutes les mémoires. Sa disparition a entraîné la Grande Famine et la Dispersion de l\'humanité à travers les univers. Pourtant ces désordres ont assuré la survie de l\'humanité conformément aux plans du Tyran ; et ses Prêtres en tirent argument pour justifer leurs ambitions. Mais la Révérende Mère Taraza sait bien que le pouvoir vient de l\'Épice, source de la prescience. La planète Dune, devenue Rakis, restera-t-elle le centre de toutes les intrigues alors que le Bene Tleilax a appris à produire l\'Épice sans le secours des vers géants ? Les forces qui se mesurent dans l\'ombre sont à l\'affût du moindre signe.',
 'dune_5.jpg', 2012, 2),
('978-2266235853', 3, '6. La maison des mères', E'Dune est détruite, vitrifiée, atomisée. Sur tout l\'Empire déferlent les hordes furieuses des Honorées Matriarches, massacrant tout sur leur passage. Le Bene Gesserit reste la seule force organisée. Mais la solution n\'est peut-être pas dans le pouvoir des armes. Darwi Odrade, la Mère Supérieure, propose de négocier. La Très Honorée Matriarche accepte : elle s\'attend à une capitulation sans conditions. Mais Darwi a un plan. Elle sait bien que l\'entreprise est des plus risquées. Si elle parvenait à ramener la paix, elle provoquerait des tensions insupportables et peut-être une nouvelle Dispersion. Rien de moins.',
 'dune_6.jpg', 2012, 2),
('978-2035861566', 26, 'Les fleurs du mal', E'Pourquoi le recueil des Fleurs du mal a-t-il cette audience aujourd’hui ? Parce qu’il représente, depuis 1857, la naissance d’une poésie nouvelle. Baudelaire utilise les formes classiques – le sonnet, l’alexandrin – pour dire la modernité : la bizarrerie, les villes immenses, le malaise d’une existence douloureuse. Face à cette angoisse, il nous propose un moyen de vaincre le mal, le dégoût de soi et des autres, le « spleen » : l’idéal d’un langage qui nous montrerait un ailleurs rêvé, un monde enfin habitable.',
 'les_fleur_du_mal.jpg', 2011, 6);
 
 /*
 ('000-0000', id_genre, 'titre', E'resume',
 '.jpg', 2012, id_editeur);
 */
 


INSERT INTO adresse (code_postal, rue, num, commune) VALUES 
('57000','rue des cordonniers', 20, 'Metz'),
('57950','rue du général Patton', 45, 'Montigny-lès-Metz'),
('57950',E'place de l\'église', 18, 'Montigny-lès-Metz'),
('57950','rue des alliés', 7, 'Montigny-lès-Metz'),
('57950','rue du général Patton', 4, 'Montigny-lès-Metz');


INSERT INTO utilisateur (nom, prenom, telephone, id_adresse) VALUES
('admin', 'admin', '0123456789', 1),
('tata', 'manue', '9876543210', 2),
('simon', 'culçonet', '0000000000', 3);

INSERT INTO compte (email, password, id_role, id_utilisateur) VALUES
('admin@admin.com', 'admin', 3, 1),
('tata@tata.com', '1234', 1, 2),
('simon.culçonet@haha.com', '2345', 1, 3);

INSERT INTO bibliotheque (nom, id_adresse) VALUES
('bibliothèque du centre', 4),
('bibliothèque Robert Schumann', 5);


INSERT INTO exemplaire (id_livre, id_biblio) VALUES
(1, 1),
(1, 1),
(1, 2),
(1, 2),
(1, 2),
(1, 2),
(2, 1),
(2, 1),
(2, 1),
(2, 1),
(2, 2),
(2, 2),
(2, 2),
(3, 1),
(3, 1),
(3, 1),
(3, 1),
(3, 1),
(3, 2),
(3, 2);


INSERT INTO livre_auteur (id_livre, id_auteur) VALUES
(1, 1),
(2, 3),
(3, 3);


INSERT INTO reservation (id_compte, date_emprunt, extension, id_exemplaire) VALUES
(2, '2019/11/5', false, 7),
(2, '2019/11/5', false, 14),
(3, '2019/9/11', true, 8);

