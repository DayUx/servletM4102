
function calculer() {
    $.ajax({ // Requête AJAX
        url: "http://localhost:8040/M4102C/action",  // URL du service
        type: "GET", // Type de la requête HTTP
        dataType: "json", // Type de données de retour
        data: {
            "entier": $("#calcul").val() // Données à envoyer au service
        },
        success: function (data) { // Fonction à exécuter en cas de succès
            console.log(data); // Affiche les données reçues dans la console
            $("#resultat").html(data.entier); // Affichage du résultat
        }
    })
}