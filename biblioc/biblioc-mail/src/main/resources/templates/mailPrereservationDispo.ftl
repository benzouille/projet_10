<head>
    <meta charset="UTF-8">
    <style>
        body {
            background-image: url("https://www.publicdomainpictures.net/pictures/20000/velka/book-textbook.jpg");
            font-family: "Monstreet", sans-serif;
        }
        .box {
            margin: 80px 80px;
            padding: 40px;
            border-radius: 24px;
            background-color: rgba(0, 0, 0, 0.8);
            color: white;
        }
        .box h1 {
            font-size: 34px;
            margin: 0 0 20px 0;
            padding: 2px 0 2px 0;
            line-height: 1;
            color: #fff;
            font-weight: 500;
            letter-spacing: 3px;
            text-transform: uppercase;
        }
        .box p {
            background: none;
            display: block;
            margin: 20px auto;
            outline: none;
            color: white;
            transition: 0.25s;
        }
        .centered{
            text-align: center;
        }
        .link {
            color: #60a3bc;
        }
        .email {
            color: white;
        }
        .footer-top {
            margin-top: 30px;
            padding-top: 30px;
            padding-bottom: 30px;
            background-color: rgba(0, 0, 0, 0.9);
        }
        .footer-info {
            margin-bottom: 30px;
        }
        .footer-info p {
            font-size: 13px;
            line-height: 24px;
            margin-bottom: 0;
            color: #eef5ff;
        }
        .footer-top h4 {
            font-size: 14px;
            font-weight: bold;
            color: white;
            text-transform: uppercase;
            position: center;
            padding-bottom: 10px;
        }
        .footer-contact {
            margin-bottom: 30px;
        }
        .footer-contact p {
            line-height: 26px;
        }
        .credits {
            text-align: center;
            font-size: 13px;
            color: #bfddfe;
        }
        .credits a:hover {
            color: #f1f7ff;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div>
            <div class="box">
                <h1 class="centered">BIBLIOC</h1>
                <br>
                <p>Bonjour ${prenom}</p>
                <p>
                    Le livre <span style="color: #60a3bc;">${titre}</span> provenant de la bibliotheque <span style="color: #60a3bc;">${bibliotheque}</span>, est disponible pour prêt. <br>
                    Vous pouvez vous rendre sur votre espace biblioc pour valider l'emprunt de cet ouvrage. <br>
                    Si dans 48 heures aucune réponse a été donnée, la réservation sera considérée comme close.
                </p>
                <br>
                <p>L'équipe de biblioc</p>
            </div>
        </div>
    </div>
</div>

<!--FOOTER-->
<footer>
    <div class="footer-top">
        <div class="container">
            <div class="row">
                <div class="centered footer-info">
                    <p>Site du groupement des bibliotheque de la CA2M.</p>
                </div>
                <div class="centered footer-contact">
                    <h4>Contact</h4>
                    <p class="email">
                        <strong>Email : </strong><a class="link" href="#">Contact@biblioc.com</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="centered credits">
                <p><a class="link" href="#"><strong>Banane Design</strong></a> 2019</p>
                <p> Designé par la banane</p>
            </div>
        </div>
    </div>
</footer>
</body>
