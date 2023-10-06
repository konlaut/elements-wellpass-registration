# elements-wellpass-registration

Inspired by https://github.com/alican/elements-wellpass-registration but written in Java ;) 

HOW-TO:
1. Fork this repository (Use the fork button)
2. Navigate to the forked repository (probably https://github.com/ YOUR USERNAME /elements-wellpass-registration)
3. Click on the "Settings" tab located in the repository's navigation menu.
4. Click on "Secrets and variables" in the left side menu and then click on "Actions"
5. Add following 3 secrets by clicking on "New repository secret"

| Name | Secret | Type |
| --- | --- | --- |
|ELEMENTS_EMAIL | yourElementsEmail | secret |
|ELEMENTS_PASSWORD | yourElementsPassword | secret |
|ELEMENTS_STUDIOS| one or several studio IDs separated by commas e.g. 64,65 or only 65 etc. | secret |

| Studio | ID | 
| --- | --- |
| Balanstraße | 65 | 
| Donnersbergerbrücke | 64 | 
| Eschborn | 60 | 
| Eschenheimer Turm | 61 | 
| Henninger Turm | 62 | 
| Paulinenbrücke | 66 | 
| Siemensallee | 63 | 

6. Navigate back to your forked repository.
7. Click on the "Actions" tab located in the repository's navigation menu.
8. Click on "I understand my workflows, go ahead and enable them" ;)
9. You can change the shedule of the registration in [your maven.yml](/.github/workflows/maven.yml) by changing the value of "- cron:" 
10. Have fun
