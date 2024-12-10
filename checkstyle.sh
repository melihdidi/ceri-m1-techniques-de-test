#!/bin/bash
pwd
# Exécuter la commande Checkstyle et stocker la sortie dans un fichier
mvn checkstyle:check > checkstyle_output.txt
echo "Badge updated with $error_count 1."

# Compter le nombre d'erreurs dans la sortie (les lignes contenant "error")
error_count=$(grep -c "error" checkstyle_output.txt)
echo "Badge updated with $error_count 2."

# Mettre à jour le badge avec le nombre d'erreurs
badge_url="https://img.shields.io/badge/Checkstyle%20Errors-${error_count}-red"
echo "Generated badge URL: $badge_url"

# Vérifier le contenu actuel du README.md avant modification
echo "Content of README.md before modification:"
cat README.md

# Remplacer le badge dans le fichier README.md
# Utilisation de sed pour remplacer l'ancienne URL par la nouvelle
# Nous ajoutons un "echo" pour vérifier que la commande sed s'exécute bien
sed -i "s|https://img.shields.io/badge/Checkstyle%20Errors-[0-9]*-red|$badge_url|" README.md

# Vérifier le contenu du README.md après modification
echo "Content of README.md after modification:"
cat README.md

# Confirmer la mise à jour du badge
echo "Badge updated with $error_count errors."

# Pause de 5 secondes
echo "Pausing for 5 seconds..."
sleep 5
